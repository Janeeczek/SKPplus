package com.JanCode.SKPplus.web.dto;
import com.JanCode.SKPplus.constraint.FieldMatch;
import com.JanCode.SKPplus.constraint.MyNotNull;
import com.JanCode.SKPplus.constraint.Password;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Hasła się nie zgadzają!"),
        //@FieldMatch(first = "email", second = "confirmEmail", message = "Adres email się nie zgadza!")
})

public class UserRegistrationDto {
    @MyNotNull(message = "Nazwa użytkownika musi mieć conajmniej 4 znaki!")
    @NotNull
    @NotEmpty(message = "Nazwa użytkownika jest wymagana!")
    private String username;

    @NotEmpty(message = "Imię jest wymagane!")
    private String firstName;

    @NotEmpty(message = "Nazwisko jest wymagane!")
    private String lastName;
    @Password(message = "Hasło musi zawierać minimum jeden znak specjalny oraz jedną małą i duzą litere oraz cyfrę. Długość od 8 do 15 znaków!")
    private String password;

    @NotEmpty(message = "Wpisz ponownie hasło!")
    private String confirmPassword;

    @Email(message = "Nieprawidłowy e-mail!")
    @NotEmpty(message = "Email jest wymagany!")
    private String email;


    @AssertTrue(message = "Zaakceptuj warunki użytkownika!")
    private Boolean terms;

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

   /* public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }


    */
    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }
}
