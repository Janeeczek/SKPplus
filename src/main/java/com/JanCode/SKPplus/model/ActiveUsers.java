package com.JanCode.SKPplus.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    private LocalDateTime lastActiveDate;
    public ActiveUsers() {
    }
    public ActiveUsers(User user) {
        this.email = user.getEmail();
        this.username = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()).stream()
                .map(n -> String.valueOf(n).substring(5))
                .collect(Collectors.joining(" ", " ", " "));
        this.lastActiveDate = LocalDateTime.now();
    }

    public ActiveUsers(String email, String username, String firstName, String lastName, Collection<Role> roles ) {
        this.email = email;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toList()).stream()
                .map(n -> String.valueOf(n).substring(5))
                .collect(Collectors.joining(" ", " ", " "));
        this.lastActiveDate = LocalDateTime.now();
    }

    public ActiveUsers(MyUserPrincipal principal) {
        this.email = principal.getEmail();
        this.username = principal.getUsername();
        this.firstName = principal.getFirstname();
        this.lastName = principal.getLastname();
        this.roles = principal.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()).stream()
                .map(n -> String.valueOf(n).substring(5))
                .collect(Collectors.joining(" ", " ", " "));
        this.lastActiveDate = LocalDateTime.now();
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

    public LocalDateTime getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(LocalDateTime lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }
}
