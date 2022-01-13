/**
 * 
 */
package com.prasad.nithin.xmas.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasad.nithin.xmas.entity.UserAuth;

/**
 * @author 982168
 *
 */
@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, String>{

	public UserAuth findByUserIdUserId(String userId);
}
