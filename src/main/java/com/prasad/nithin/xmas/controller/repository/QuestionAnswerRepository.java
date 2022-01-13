/**
 * 
 */
package com.prasad.nithin.xmas.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.nithin.xmas.entity.QuestionAnswer;
import com.prasad.nithin.xmas.entity.TEAM;

/**
 * @author 982168
 *
 */
@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, String>{

	public List<QuestionAnswer> getQuestionIdByTrackingId(String trackingId);
	
	public List<QuestionAnswer> findByTeam(TEAM team);

	public List<QuestionAnswer> findByTeamAndTrackingId(TEAM team, String identifier);
	
}
