/**
 * 
 */
package com.prasad.nithin.xmas.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 982168
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretSanta implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "santa", referencedColumnName = "userId")
	private User santa;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "child", referencedColumnName = "userId")
	private User child;
	
	
}
