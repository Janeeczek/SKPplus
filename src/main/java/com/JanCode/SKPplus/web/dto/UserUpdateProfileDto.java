package com.JanCode.SKPplus.web.dto;

import com.JanCode.SKPplus.constraint.FieldMatch;
import com.JanCode.SKPplus.constraint.ImageSize;
import com.JanCode.SKPplus.constraint.MyNotNull;
import com.JanCode.SKPplus.model.MyUserPrincipal;
import com.JanCode.SKPplus.model.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@FieldMatch.List({
        @FieldMatch(first = "newPassword", second = "repeatNewPassword", message = "Hasła się nie zgadzają!"),
        //@FieldMatch(first = "email", second = "confirmEmail", message = "Adres email się nie zgadza!")
})
public class UserUpdateProfileDto {
    @MyNotNull(message = "Nazwa użytkownika musi mieć conajmniej 4 znaki!")
    private String username;
    @NotEmpty(message = "Nazwa użytkownika musi mieć conajmniej 4 znaki!")
    private String firstName;
    @NotBlank(message = "Nazwa użytkownika musi mieć conajmniej 4 znaki!")
    private String lastName;
    @Email(message = "Nieprawidłowy e-mail!")
    private String email;
    private String currentPassword;
    private String newPassword;
    private String repeatNewPassword;
    @ImageSize(message = "Nieprawidłowy rozmiar zdjęcia!")
    private MultipartFile image;
    private String telNumber;
   //@DateTimeFormat(pattern = "dd/mm/yyyy")
    private String birthDate;

    public UserUpdateProfileDto() {

    }
    public UserUpdateProfileDto(MyUserPrincipal principal) {
        this.username = principal.getUsername();
        this.firstName = principal.getFirstname();
        this.lastName = principal.getLastname();
        this.email = principal.getEmail();
        this.image = principal.getMultiPartImage();
        this.telNumber = principal.getTelNumber();
        this.birthDate = principal.getBirthDate();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRepeatNewPassword() {
        return repeatNewPassword;
    }

    public void setRepeatNewPassword(String repeatNewPassword) {
        this.repeatNewPassword = repeatNewPassword;
    }

    public MultipartFile getImage() {
        return image;
    }
    public byte[] getByteImage() {
        byte[] bity = null;
        try {
            bity = this.image.getBytes();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return bity;
    }
    public String getByte64Image() {
        if (this.image == null)
            return null;
        else {
            String encodedString = new String(java.util.Base64.getEncoder().encodeToString(this.getByteImage()));
            //System.out.println("Przetworzylem obraz w DTO na byte64: ");
            return encodedString;
        }
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
