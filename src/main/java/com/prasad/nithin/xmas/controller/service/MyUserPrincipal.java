/**
 * 
 */
package com.prasad.nithin.xmas.controller.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.prasad.nithin.xmas.entity.UserAuth;
import com.prasad.nithin.xmas.entity.UserRoles;

/**
 * @author 982168
 *
 */
public class MyUserPrincipal implements UserDetails {

	
	String username;

	String password;

	List<GrantedAuthority> authority;

	MyUserPrincipal(UserAuth user) {
		this.username = user.getUserId().getUserId();
		this.password = user.getPassword();
		this.authority = user.getUserRoles().stream().map(UserRoles::name).map(name -> "ROLE_".concat(name))
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authority;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
