/**
 * 
 */
package com.prasad.nithin.xmas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class UserAuth implements Serializable{

	@Id
	String id;
	
	@OneToOne
	private User userId;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<UserRoles> userRoles;
}
