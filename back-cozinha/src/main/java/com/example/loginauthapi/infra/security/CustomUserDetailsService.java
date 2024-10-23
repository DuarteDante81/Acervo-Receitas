package com.example.loginauthapi.infra.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), 
				user.getPassword(), new ArrayList<>());
	}

}
