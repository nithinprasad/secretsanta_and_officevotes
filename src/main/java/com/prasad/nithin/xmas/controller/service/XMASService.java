/**
 * 
 */
package com.prasad.nithin.xmas.controller.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasad.nithin.xmas.controller.repository.SecretSantaRepostitory;
import com.prasad.nithin.xmas.controller.repository.UserGiftRepository;
import com.prasad.nithin.xmas.controller.repository.UserRepository;
import com.prasad.nithin.xmas.entity.SecretSanta;
import com.prasad.nithin.xmas.entity.User;

/**
 * @author 982168
 *
 */
@Service
public class XMASService {

	@Autowired
	UserRepository repository;

	@Autowired
	UserGiftRepository userGiftRepo;
	
	@Autowired
	SecretSantaRepostitory santaRepostitory;

	public void generatePair() {

		santaRepostitory.findAll()
			.stream()
			.map(SecretSanta::getId)
			.forEach(santaRepostitory::deleteById);

		List<User> list = repository.findAll();

		List<SecretSanta> pairs = generatePair(list);

		santaRepostitory.saveAll(pairs);

	}

	private List<SecretSanta> generatePair(List<User> list) {
		List<String> pairedList = new ArrayList<>();

		return list.stream().map(user -> this.findMatch(list, user, pairedList)).collect(Collectors.toList());

	}

	private SecretSanta findMatch(List<User> list, User currentUser, List<String> pairedList) {
		Optional<User> pair = list.parallelStream()
					.filter(user -> !user.getUserId().equals(currentUser.getUserId()))
					.filter(user -> !pairedList.contains(user.getUserId()))
					.findAny();

		if (pair.isPresent()) {
			pairedList.add(pair.get().getUserId());
			return new SecretSanta(null,currentUser, pair.get());

		}
		return new SecretSanta();
	}

	public List<SecretSanta> getPairs() {
		return santaRepostitory.findAll();

	}

}
