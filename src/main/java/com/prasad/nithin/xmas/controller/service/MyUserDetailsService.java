package com.prasad.nithin.xmas.controller.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.prasad.nithin.xmas.controller.repository.UserAuthRepository;
import com.prasad.nithin.xmas.entity.UserAuth;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserAuth> user = userRepository.findById(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails details=new  MyUserPrincipal(user.get());
        System.out.println(details);
        return details;
    }
}