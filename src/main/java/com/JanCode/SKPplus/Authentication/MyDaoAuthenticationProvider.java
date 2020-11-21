package com.JanCode.SKPplus.Authentication;


import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import com.JanCode.SKPplus.repository.UserRepository;
import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {


    @Autowired
    private UserService userService;
    @Autowired
    private ActiveUserService activeUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        User user = new User (userService.findByUsername(authentication.getPrincipal().toString()));
        System.out.println("ZALOGOWANO! email: " + user.getEmail());
        if(activeUserService.findByEmail(user.getEmail())==null) {
            System.out.println("Dodano email: " + user.getEmail() + " do bazy danych aktywnych użytkowników!");
            activeUserService.save(user);
        }
        return super.authenticate(authentication);
    }
}