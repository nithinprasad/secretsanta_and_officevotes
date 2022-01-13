/**
 * 
 */
package com.prasad.nithin.xmas.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.nithin.xmas.entity.TEAM;
import com.prasad.nithin.xmas.entity.User;

/**
 * @author 982168
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>{

	public List<User> findByEmailId(String emailId);
	
	public List<User> findAllByTeamIn(List<TEAM> team);
	
	public List<User> findByUserId(String userId);
}
