package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.RoleRepository;
import com.JanCode.SKPplus.repository.UserRepository;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserUpdateProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setLastActiveDate(LocalDateTime.now());
        user.setRegistrationDate(LocalDateTime.now());
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        try{
            Resource resource = new ClassPathResource("avatar1.png");
            InputStream input = resource.getInputStream();
            File file = resource.getFile();
            user.setImage(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.out.println("BLAD NIE ZNALEZIONO PLIKU" + e);
        }

        return userRepository.save(user);
    }
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet< >(roleRepository.findAll()));
        return userRepository.save(user);
    }

    @Override
    public User save(UserUpdateProfileDto userdto,String username) {
        User user = userRepository.findByUsername(username);
        user.setUsername(userdto.getUsername());
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        user.setEmail(userdto.getEmail());
        user.setBirthDate(userdto.getBirthDate());
        System.out.println(userdto.getBirthDate());
        user.setTelNumber(userdto.getTelNumber());
        user.setLastActiveDate(LocalDateTime.now());

        if (userdto.getImage() != null) {
            user.setImage(userdto.getByteImage());
        }
        if (userdto.getNewPassword() != null && userdto.getCurrentPassword() != null && userdto.getRepeatNewPassword() != null) {
            if (passwordEncoder.matches(userdto.getCurrentPassword(),user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userdto.getNewPassword()));
            }
        }
        return userRepository.save(user);
    }

    @Override
    public User updateLastActiveTime(String email) {
        User user = userRepository.findByEmail(email);
        user.setLastActiveDate(LocalDateTime.now());
        return userRepository.save(user);
    }



}