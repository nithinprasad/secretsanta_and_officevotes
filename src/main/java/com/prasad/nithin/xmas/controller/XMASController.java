/**
 * 
 */
package com.prasad.nithin.xmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasad.nithin.xmas.controller.service.XMASService;
import com.prasad.nithin.xmas.entity.SecretSanta;

/**
 * @author 982168
 *
 */
@RestController
@RequestMapping("/xmas")
public class XMASController {

	@Autowired
	XMASService service;
	
	@PostMapping("makepair")
	public void makePair() {
		service.generatePair();
	}
	
	@GetMapping("pairs")
	public List<SecretSanta> getPaid() {
		return service.getPairs();
	}
	
}
