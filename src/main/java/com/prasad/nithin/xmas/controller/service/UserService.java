/**
 * 
 */
package com.prasad.nithin.xmas.controller.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prasad.nithin.xmas.controller.repository.SecretSantaRepostitory;
import com.prasad.nithin.xmas.controller.repository.UserAuthRepository;
import com.prasad.nithin.xmas.controller.repository.UserGiftRepository;
import com.prasad.nithin.xmas.controller.repository.UserRepository;
import com.prasad.nithin.xmas.entity.SecretSanta;
import com.prasad.nithin.xmas.entity.TEAM;
import com.prasad.nithin.xmas.entity.User;
import com.prasad.nithin.xmas.entity.UserAuth;
import com.prasad.nithin.xmas.entity.UserGiftPreference;
import com.prasad.nithin.xmas.entity.UserRoles;

/**
 * @author 982168
 *
 */
@Service
public class UserService {

	@Value("${admins}")
	String[] admins;
	
	@Autowired
	UserGiftRepository userGiftRepository;

	@Autowired
	SecretSantaRepostitory santaRepo; 
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAuthRepository authRepo;

	public UserAuth doSignUp(UserGiftPreference pref) {
		if (pref != null && pref.getId() != null && pref.getId().isEmpty()) {
			pref.setId(null);
		}
		pref.setUser(userRepository.save(pref.getUser()));
		UserGiftPreference res = userGiftRepository.save(pref);
		UserAuth auth = new UserAuth();
		auth.setId(res.getUser().getUserId());
		auth.setUserId(res.getUser());
		auth.setPassword(generatePwd(res.getUser().getUserId()));
		if(Arrays.asList(admins).contains(res.getUser().getUserId())) {
			auth.setUserRoles(Arrays.asList(UserRoles.ADMIN,UserRoles.USER));
		}else {
			auth.setUserRoles(Arrays.asList(UserRoles.USER));
		}
		
		return authRepo.save(auth);
	}

	public List<User> getAllUsers(TEAM team) {
		
		return getAllUsersSorted(Optional.ofNullable(team)
					.map(e->userRepository.findAllByTeamIn(Arrays.asList(team))	)
					.orElse( userRepository.findAll()));
	}
	
	public List<User> getAllUsersSorted(List<User> users){
		return users.
		stream()
		.sorted(Comparator.comparing(User::getName))
		.collect(Collectors.toList());
	}
	

	private String generatePwd(String empId) {
		Random random = new Random();

		int random3Digit = random.nextInt(900) + 100;

		return empId + "_" + random3Digit;
	}

	public List<UserGiftPreference> getAllGifts() {
		return userGiftRepository.findAll();
	}

	public User getSpecificUsers(String id) {
		List<User> users = userRepository.findByUserId(id);
		if (!users.isEmpty()) {
			return users.stream().findAny().orElseThrow(() -> new UsernameNotFoundException(id));
		} else {
			throw new UsernameNotFoundException(id);
		}
	}

	public UserGiftPreference fetchChildForSanta(String santaId) throws Exception {
		List<SecretSanta> users=santaRepo.findBySantaUserId(santaId);
		if (!users.isEmpty()) {
			return users.stream().findAny().map(e->{
				try {
					return findChildGiftPref(e);
				} catch (Exception e1) {
					return null;
				}
			}).orElseThrow(() -> new Exception(santaId));
		} else {
			throw new Exception(santaId);
		}
		
	}

	private UserGiftPreference findChildGiftPref(SecretSanta e) throws Exception {
		return userGiftRepository.findByUserUserId(e.getChild().getUserId()).stream().findAny().orElseThrow(() -> new Exception(e.toString()));
	}

}
