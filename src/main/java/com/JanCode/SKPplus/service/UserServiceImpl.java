package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.RoleRepository;
import com.JanCode.SKPplus.repository.UserRepository;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User save(UserRegistrationDto registration){
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet< >(roleRepository.findAll()));
        return userRepository.save(user);
    }
}