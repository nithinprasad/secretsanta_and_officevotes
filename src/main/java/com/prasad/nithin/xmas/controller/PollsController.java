/**
 * 
 */
package com.prasad.nithin.xmas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.nithin.xmas.controller.service.PollsService;
import com.prasad.nithin.xmas.entity.Question;
import com.prasad.nithin.xmas.entity.QuestionAnswer;
import com.prasad.nithin.xmas.entity.Results;
import com.prasad.nithin.xmas.entity.TEAM;

/**
 * @author 982168
 *
 */
@RestController
@RequestMapping("/polls")
public class PollsController {

	@Autowired
	PollsService pollsService;

	@GetMapping("question")
	public List<Question> getQuestion(HttpServletRequest request, @RequestParam(required = false) TEAM team) {
		return pollsService.getQuestion(getUniqueId(request), team);
	}

	@PostMapping("question")
	public Question addQuestion(@RequestBody Question question) {
		return pollsService.addQuestion(question);
	}

	@PostMapping("questions")
	public List<Question> addQuestions(@RequestBody List<Question> question) {
		return pollsService.addQuestion(question);
	}

	@PostMapping("vote")
	public QuestionAnswer addQuestionAnswer(HttpServletRequest request, @RequestBody QuestionAnswer questionAnswer) {
		questionAnswer.setTrackingId(getUniqueId(request));
		return pollsService.addQuestionAnswer(questionAnswer);
	}

	@GetMapping("results")
	public List<Results> getResults(@RequestParam(required = false) TEAM team) {
		return pollsService.getResults(team);
	}

	@DeleteMapping("vote")
	public void flush() {
		pollsService.flush();
	}

	private String getUniqueId(HttpServletRequest request) {

		String uniqueId = null;

		if (request.getHeader("XMAS-UNIQUE") != null) {
			uniqueId = request.getHeader("XMAS-UNIQUE");
		}
		
		if (uniqueId == null) {
			uniqueId = request.getHeader("x-forwarded-for");
		}
		if (uniqueId == null)
			uniqueId = request.getRemoteHost();
		if (uniqueId == null)
			uniqueId = request.getHeader("user-agent");
		System.out.println(uniqueId);
		return uniqueId;

	}

}
