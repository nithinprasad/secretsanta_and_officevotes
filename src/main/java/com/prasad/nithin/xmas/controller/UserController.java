/**
 * 
 */
package com.prasad.nithin.xmas.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.nithin.xmas.controller.service.UserService;
import com.prasad.nithin.xmas.entity.TEAM;
import com.prasad.nithin.xmas.entity.User;
import com.prasad.nithin.xmas.entity.UserAuth;
import com.prasad.nithin.xmas.entity.UserGiftPreference;

/**
 * @author 982168
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@GetMapping("status")
	public  ResponseEntity<User> getStatus(Authentication authentication) {
		if(authentication!=null) {
			User user=new User();
			user.setUserId(authentication.getName());
			return new ResponseEntity<>(service.getSpecificUsers(authentication.getName()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("signup")
	public ResponseEntity<UserAuth> doSignup(@RequestBody UserGiftPreference userGiftPreference) {
		return new ResponseEntity<>(service.doSignUp(userGiftPreference), HttpStatus.OK);
	}

	@GetMapping
	
	public List<User> getUsers(@RequestParam(required = false) TEAM team) {
		return service.getAllUsers(team);
	}

	@GetMapping("{id}")
	public User getSpecificUser(@PathVariable String id) {
		return service.getSpecificUsers(id);
	}	
	
	@GetMapping("gift")
	public List<UserGiftPreference> getGifts() {
		return service.getAllGifts();
	}

	@GetMapping("teams")
	public List<TEAM> getTeams() {
		return Arrays.asList(TEAM.values());
	}
	
	@GetMapping("child")
	public ResponseEntity<UserGiftPreference> getChild(Authentication authentication) throws Exception {

		if(authentication!=null) {
			User user=new User();
			user.setUserId(authentication.getName());
			return new ResponseEntity<>(service.fetchChildForSanta(authentication.getName()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	
	}

}
