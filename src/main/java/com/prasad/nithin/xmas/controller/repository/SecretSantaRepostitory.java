/**
 * 
 */
package com.prasad.nithin.xmas.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.nithin.xmas.entity.SecretSanta;

/**
 * @author 982168
 *
 */
public interface SecretSantaRepostitory extends JpaRepository<SecretSanta,String> {

	public List<SecretSanta> findBySantaUserId(String santaUserId); 
}
