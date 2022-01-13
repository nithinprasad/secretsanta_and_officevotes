/**
 * 
 */
package com.prasad.nithin.xmas.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
public class UserGiftPreference implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	@ElementCollection
	private List<String> preference;
	private String deliveryAddress;
	private String postalCode;
}
