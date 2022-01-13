/**
 * 
 */
package com.prasad.nithin.xmas.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prasad.nithin.xmas.entity.UserGiftPreference;

/**
 * @author 982168
 *
 */
public interface UserGiftRepository extends JpaRepository<UserGiftPreference,String>{

	public List<UserGiftPreference> findByUserUserId(String santaUserId); 
}
