package com.JanCode.SKPplus.service;


import com.JanCode.SKPplus.exeception.UserAlreadyExistException;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.token.VerificationToken;
import com.JanCode.SKPplus.web.dto.AdminRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserRegistrationDto;
import com.JanCode.SKPplus.web.dto.UserUpdateProfileDto;

import java.util.List;
import java.util.Optional;

public interface UserService{

    User findByEmail(String email);
    User myFindById(long id);
    User findByUsername(String username);
    List<User> findAllUsersNotActivated();
    User registerNewUserAccount(UserRegistrationDto registration) throws UserAlreadyExistException;
    User registerNewUserAccount(AdminRegistrationDto registration) throws UserAlreadyExistException;
    User saveUpdatedUser( UserUpdateProfileDto userdto, String username);
    User updateLastActiveTime(String username);
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String VerificationToken);
    User getUser(String verificationToken);
    void saveRegisteredUser(User user);
    void saveRegisteredUser(String username);
    List<User> findAllUsers();
    void delete(String username);

}