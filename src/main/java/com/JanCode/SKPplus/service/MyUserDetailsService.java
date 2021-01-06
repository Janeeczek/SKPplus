package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
            return new MyUserPrincipal(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}