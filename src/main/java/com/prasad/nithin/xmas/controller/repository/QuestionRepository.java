/**
 * 
 */
package com.prasad.nithin.xmas.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.nithin.xmas.entity.Question;
import com.prasad.nithin.xmas.entity.TEAM;

/**
 * @author 982168
 *
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, String>{

	public List<Question> findByQuestionIdNotIn(List<String> questionId);

	public List<Question> findByTeam(TEAM team);

	public List<Question> findByTeamAndQuestionIdNotIn(TEAM team, List<String> answeredQuestionId);
	
}
