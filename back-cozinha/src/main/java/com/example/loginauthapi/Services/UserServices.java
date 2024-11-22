package com.example.loginauthapi.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loginauthapi.model.User;
import com.example.loginauthapi.repositories.UserRepository;

@Service
public class UserServices {
	
    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        return userRepository.findByemail(email);
    }
}
