package com.JanCode.SKPplus.authentication;

import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.UserRepository;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(String...args) throws Exception {
        if(userService.findByUsername("admin")== null) {
            User admin = new User("admin","adminName","adminLastName","admin@admin.pl",passwordEncoder.encode("123456"));
            admin.setLastActiveDate(LocalDateTime.now());
            admin.setRegistrationDate(LocalDateTime.now());
            admin.setEnabled(true);
            admin.setRoles(Arrays.asList(new Role("ROLE_"+"ADMIN")));
            try{
                Resource resource = new ClassPathResource("avatar1.png");
                InputStream input = resource.getInputStream();
                File file = resource.getFile();
                admin.setImage(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                System.out.println("BLAD! NIE ZNALEZIONO PLIKU" + e);
            }
            User user = userRepository.save(admin);
            if(user != null){
                System.out.println("UTWORZONO KONTO ADMINISTRATORA:");
                System.out.println("Login: "+ "admin");
                System.out.println("Hasło: "+ "123456");
            } else {
                System.out.println("NIE UTWORZONO KONTA ADMINISTRATORA!");
                System.out.println("Bład bazy danych!");
            }

        }else {
            System.out.println("NIE UTWORZONO KONTA ADMINISTRATORA!");
            System.out.println("Takie konto już istnieje");
        }

    }
}