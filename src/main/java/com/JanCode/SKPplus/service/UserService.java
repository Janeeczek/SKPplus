package com.JanCode.SKPplus.service;


import com.JanCode.SKPplus.exeception.UserAlreadyExistException;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.token.VerificationToken;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserUpdateProfileDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService{

    User findByEmail(String email);
    User findByUsername(String username);
    User registerNewUserAccount(UserRegistrationDto registration) throws UserAlreadyExistException;
    User saveUpdatedUser(UserUpdateProfileDto userdto,String username);
    User updateLastActiveTime(String email);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
    User getUser(String verificationToken);
    void saveRegisteredUser(User user);
}