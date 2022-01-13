/**
 * 
 */
package com.prasad.nithin.xmas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@Entity
public class User implements Serializable{

	@Id
	private String userId;
	private String name;
	@ElementCollection
	private List<TEAM> team;
	private String emailId;
	private String mobileNumber;
	
}
