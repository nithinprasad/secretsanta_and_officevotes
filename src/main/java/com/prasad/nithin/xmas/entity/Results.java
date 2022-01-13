/**
 * 
 */
package com.prasad.nithin.xmas.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 982168
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Results {

	private Question question;
	
	private List<UserResult> results;
	
	private int totalVotes;
	
	private int totalUniqueVotes;
	
}
