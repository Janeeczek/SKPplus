package com.JanCode.SKPplus.web.dto;
import com.JanCode.SKPplus.constraint.FieldMatch;
import com.JanCode.SKPplus.constraint.MyNotNull;
import com.JanCode.SKPplus.constraint.OnlyLatin;
import com.JanCode.SKPplus.model.AccountType;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Hasła się nie zgadzają!"),
        //@FieldMatch(first = "email", second = "confirmEmail", message = "Adres email się nie zgadza!")
})

public class AdminRegistrationDto {
    @MyNotNull(message = "Nazwa użytkownika musi mieć co najmniej 4 znaki!")
    //@NotNull
    //@NotEmpty(message = "Nazwa użytkownika jest wymagana!")
    @OnlyLatin(message = "Nazwa użytkownika nie może zawierać polskich znaków!")
    private String username;

    @NotEmpty(message = "Imię jest wymagane!")
    private String firstName;

    @NotEmpty(message = "Nazwisko jest wymagane!")
    private String lastName;
    @OnlyLatin(message = "Hasło nie może zawierać polskich znaków!")
    @NotEmpty(message = "Hasło jest wymagane!")
    private String password;

    @NotEmpty(message = "Wpisz ponownie hasło!")
    private String confirmPassword;
    @Email(message = "Nieprawidłowy e-mail!")
    @NotEmpty(message = "Email jest wymagany!")
    private String email;

    private AccountType role;

    private boolean active;



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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getRole() {
        return role;
    }

    public void setRole(AccountType role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
