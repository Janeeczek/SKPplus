package com.JanCode.SKPplus.service;


import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{

    User findByEmail(String email);
    User findByUsername(String username);
    User save(UserRegistrationDto registration);
    User save(User user);
}