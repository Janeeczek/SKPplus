package com.JanCode.SKPplus.service;

import com.JanCode.SKPplus.exeception.UserAlreadyExistException;
import com.JanCode.SKPplus.model.Role;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.UserRepository;
import com.JanCode.SKPplus.repository.VerificationTokenRepository;
import com.JanCode.SKPplus.token.VerificationToken;
import com.JanCode.SKPplus.web.dto.AdminRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserUpdateProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenRepository tokenRepository;



    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User myFindById(long id) {

        return userRepository.myfindById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsersNotActivated() {
        List<User> userList = new ArrayList<>();
        List<User> notActiveList = new ArrayList<>();
        userList = userRepository.findAll();
        for(User user : userList) {
            if (!user.isEnabled()) notActiveList.add(user);
        }
        return notActiveList;
    }


    //używane gdy user zostaje tworzony podczas rejestracji
    @Override
    public User registerNewUserAccount(UserRegistrationDto registration) throws UserAlreadyExistException {
        if (emailExist(registration.getEmail())) {
            throw new UserAlreadyExistException("Ten adres email jest już w użyciu!");
        }
        if (usernameExist(registration.getUsername())) {
            throw new UserAlreadyExistException("Ta nazwa użytkownika jest już w użyciu!");
        }
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
            File file = resource.getFile();
            user.setImage(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.out.println("BLAD! NIE ZNALEZIONO PLIKU" + e);
        }

        return userRepository.save(user);
    }

    //używane gdy user zostaje tworzony przez admina
    @Override
    public User registerNewUserAccount(AdminRegistrationDto registration) throws UserAlreadyExistException {
        if (emailExist(registration.getEmail())) {
            throw new UserAlreadyExistException("Ten adres email jest już w użyciu!");
        }
        if (usernameExist(registration.getUsername())) {
            throw new UserAlreadyExistException("Ta nazwa użytkownika jest już w użyciu!");
        }
        User user = new User();
        user.setUsername(registration.getUsername());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setLastActiveDate(LocalDateTime.now());
        user.setRegistrationDate(LocalDateTime.now());
        user.setEnabled(registration.isActive());
        user.setRoles(Arrays.asList(new Role("ROLE_"+registration.getRole())));
        try{
            Resource resource = new ClassPathResource("avatar1.png");
            InputStream input = resource.getInputStream();
            File file = resource.getFile();
            user.setImage(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            System.out.println("BLAD! NIE ZNALEZIONO PLIKU" + e);
        }

        return userRepository.save(user);
    }

    //używane gdy user aktualizuje swoje dane
    @Override
    public User saveUpdatedUser( UserUpdateProfileDto userdto, String username) {
        if(!userdto.getUsername().equals(username)) {
            if (usernameExist(userdto.getUsername())) {
                throw new UserAlreadyExistException("Ta nazwa użytkownika jest już w użyciu!");
            }
        }


        User user = userRepository.findByUsername(username);
        user.setUsername(userdto.getUsername());
        user.setFirstName(userdto.getFirstName());
        user.setLastName(userdto.getLastName());
        //user.setEmail(userdto.getEmail());
        user.setBirthDate(userdto.getBirthDate());
        System.out.println("DATA : " + userdto.getBirthDate());
        user.setTelNumber(userdto.getTelNumber());
        user.setLastActiveDate(LocalDateTime.now());

        if (userdto.getByteImage().length > 0) {
            //System.out.println("userdto.getByteImage() != null " + userdto.getByteImage().length);
            user.setImage(userdto.getByteImage());
        }
        if (userdto.getNewPassword() != null && userdto.getCurrentPassword() != null && userdto.getRepeatNewPassword() != null) {
            if (passwordEncoder.matches(userdto.getCurrentPassword(),user.getPassword())) {
                user.setPassword(passwordEncoder.encode(userdto.getNewPassword()));
            }
        }
        return userRepository.save(user);
    }
    //używane gdy potrzeba zaktualizowac czas ostatniej aktywnosci
    @Override
    public User updateLastActiveTime(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            user.setLastActiveDate(LocalDateTime.now());
            return userRepository.save(user);
        }
        System.out.println("Nie można zaktualizować czasu ostatniej aktywności dla użytkownika: "+username+ " ponieważ on nie istnieje w bazie danych!");
        return user;
    }

    private boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void saveRegisteredUser(User user) {
        VerificationToken myToken = tokenRepository.findByUser(user);
        myToken.setExpiryDate(LocalDateTime.now());
        tokenRepository.save(myToken);
        userRepository.save(user);
    }
    @Override
    public void saveRegisteredUser(String username) {
        User user = userRepository.findByUsername(username);
        user.setEnabled(true);
        if(user != null) {
            saveRegisteredUser(user);
        }
        else System.out.println("Nie można aktywować użytkownika: "+username+ " ponieważ on nie istnieje w bazie danych!");
    }

    @Override
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByUsername(username);
        VerificationToken myToken = tokenRepository.findByUser(user);
        if (myToken!= null) {
            tokenRepository.delete(myToken);
        }

        if (user != null) {
            userRepository.delete(user);
        }
        System.out.println("Nie można usunąć użytkownika: "+username+ " ponieważ on nie istnieje w bazie danych!");

    }
}