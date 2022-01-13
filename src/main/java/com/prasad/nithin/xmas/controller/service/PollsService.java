/**
 * 
 */
package com.prasad.nithin.xmas.controller.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.nithin.xmas.controller.repository.QuestionAnswerRepository;
import com.prasad.nithin.xmas.controller.repository.QuestionRepository;
import com.prasad.nithin.xmas.entity.Question;
import com.prasad.nithin.xmas.entity.QuestionAnswer;
import com.prasad.nithin.xmas.entity.Results;
import com.prasad.nithin.xmas.entity.TEAM;
import com.prasad.nithin.xmas.entity.User;
import com.prasad.nithin.xmas.entity.UserResult;

/**
 * @author 982168
 *
 */
@Service
public class PollsService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuestionAnswerRepository questionAnswerRepository;

	public List<Question> getQuestion(String identifier, TEAM team) {

		// List<QuestionAnswer>
		// answers=questionAnswerRepository.findByTeamAndTrackingId(team,identifier);
		List<String> answeredQuestionId =	 questionAnswerRepository.findByTeamAndTrackingId(team, identifier)
											.stream()
											.map(QuestionAnswer::getQuestion)
											.map(Question::getQuestionId)
											.collect(Collectors.toList());
		
		if (answeredQuestionId.isEmpty()) {
			return questionRepository.findByTeam(team);
		} else {
			return questionRepository.findByTeamAndQuestionIdNotIn(team,answeredQuestionId);
		}
	}

	public Question addQuestion(Question question) {
		return questionRepository.save(question);
	}

	public List<Question> addQuestion(List<Question> question) {
		return questionRepository.saveAll(question);
	}

	public QuestionAnswer addQuestionAnswer(QuestionAnswer questionAnswer) {
		
		return questionAnswerRepository.save(questionAnswer);
	}

	public List<Results> getResults(TEAM team) {
		List<QuestionAnswer> answers =  Optional.ofNullable(team).map(questionAnswerRepository::findByTeam)
												.orElseGet(questionAnswerRepository::findAll);

		Map<Question, List<QuestionAnswer>> question = answers.stream()
				.collect(groupingBy(QuestionAnswer::getQuestion));

		return question.entrySet().stream().map(this::buildResults).collect(toList());

	}

	private Results buildResults(Map.Entry<Question, List<QuestionAnswer>> questionAnswer) {

		Results results = new Results();
		results.setQuestion(questionAnswer.getKey());

		results.setTotalUniqueVotes(questionAnswer.getValue().size());
		List<User> users = questionAnswer.getValue().stream()
				.map(this::getFiltered).flatMap(Set::stream)
				.collect(toList());
		results.setTotalVotes(users.size());
		results.setResults(buildUserResult(users));
		return results;

	}
	
	private Set<User>  getFiltered(QuestionAnswer questionAnswer) {
		TEAM team=questionAnswer.getTeam();
		return questionAnswer.getAnswer().stream().filter(user->user.getTeam().contains(team)).collect(toSet());
		
	}

	private List<UserResult> buildUserResult(List<User> users) {

		Map<User, Long> results = users.stream().collect(groupingBy(Function.identity(), Collectors.counting()));
		return results.entrySet().stream().map(this::getUserResult)
				.sorted(Comparator.comparing(UserResult::getVote).reversed())
				.collect(toList());

	}

	private UserResult getUserResult(Map.Entry<User, Long> result) {
		return new UserResult(result.getKey(), result.getValue());
	}

	public void flush() {
		questionAnswerRepository.deleteAll();
	}

}
