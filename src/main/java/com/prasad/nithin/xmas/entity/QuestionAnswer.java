package com.prasad.nithin.xmas.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "question", referencedColumnName = "questionId")
	private Question question;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "answer", referencedColumnName = "userId")
	
	Set<User> answer;
	
	TEAM team;

	private String trackingId;

}
