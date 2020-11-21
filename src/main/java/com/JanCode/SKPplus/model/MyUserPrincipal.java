package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.service.ActiveUserService;
import com.JanCode.SKPplus.service.ActiveUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {

    @Autowired
    private ActiveUserService activeUserService;


    private User user;

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return mapRolesToAuthorities(user.getRoles());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }




    public String getEmail() {
        return user.getEmail();
    }
    public String getFirstname() {
        return user.getFirstName();
    }
    public String getLastname() {
        return user.getLastName();
    }
    public long getId() {
        return user.getId();
    }
    public Collection<Role> getRoles() {
        return user.getRoles();
    }
    public boolean isAdmin() {
        if (this.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        } else return false;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}