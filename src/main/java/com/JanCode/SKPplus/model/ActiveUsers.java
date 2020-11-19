package com.JanCode.SKPplus.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class ActiveUsers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String email;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    public String roles;
    public ActiveUsers() {
    }

    public ActiveUsers(String email, String username, String firstName, String lastName, Collection<Role> roles ) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList()).stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
    }

    public ActiveUsers(MyUserPrincipal principal) {
        this.email = principal.getEmail();
        this.username = principal.getUsername();
        this.firstName = principal.getFirstname();
        this.lastName = principal.getLastname();
        this.roles = principal.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()).stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
