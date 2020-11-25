package com.JanCode.SKPplus.model;

import com.JanCode.SKPplus.service.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
    public LocalDateTime getLastActiveDate() {
        return user.getLastActiveDate();
    }
    public LocalDateTime getRegistrationDate() {
        return user.getRegistrationDate();
    }
    public String getBirthDate() { return user.getBirthDate();}
    public String getTelNumber() { return user.getTelNumber();}
    public byte[] getImage() { return user.getImage();}
    public MultipartFile getMultiPartImage() {
        return (MultipartFile) new MockMultipartFile("Zdjecie", user.getImage());
    }
    public String getByte64Image() {
        if (user == null) {
            System.out.println("Error: USER == null");
            return null;
        }
        else {
            return new String(Base64.getEncoder().encodeToString(user.getImage()));
        }
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