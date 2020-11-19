package com.JanCode.SKPplus.Authentication;


import com.JanCode.SKPplus.model.ActiveUsers;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import com.JanCode.SKPplus.repository.ActiveUsersRepository;
import com.JanCode.SKPplus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private ActiveUsersRepository activeUsersRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("ZALOGOWANO!");
        User user = new User (userRepository.findByUsername(authentication.getPrincipal().toString()));

        MyUserPrincipal principal = new MyUserPrincipal(user);
        ActiveUsers active = new ActiveUsers(principal);
        if(activeUsersRepository.findByEmail(active.getEmail())==null) {
            System.out.println("Dodano email: " + active.getEmail() + " do bazy danych aktywnych użytkowników!");
            activeUsersRepository.save(active);
        }
        return super.authenticate(authentication);
    }
}