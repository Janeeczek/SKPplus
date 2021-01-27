package com.JanCode.SKPplus.model;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private boolean locked;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Lob
    private byte[] image;
    private String telNumber;

    private LocalDateTime registrationDate;
    private LocalDateTime lastActiveDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String birthDate;

    @OneToMany(mappedBy="user")
    private List<FileDB> fileList;
    @OneToMany(mappedBy="user")
    private List<Raport> raportList;



    public User() {
        super();
        this.enabled=false;
    }

    public User(User user) {
        this.id = user.id;
        this.userName = user.userName;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.email = user.email;
        this.password = user.password;
        this.roles = user.roles;
    }

    public User(String userName, String firstName, String lastName, String email, String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public User(String userName, String firstName, String lastName, String email, String password, Collection<Role> roles, byte[] image, boolean enabled, boolean locked) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.image = image;
        this.enabled = enabled;
        this.locked = locked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }
    public String getFormattedRoles() {
        return this.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()).stream()
                .map(n -> String.valueOf(n).substring(5))
                .collect(Collectors.joining(" ", " ", " "));
    }
    public AccountType getAccountType() {
        String role =  getFormattedRoles();
        AccountType type;
        if (role.equals(" "+AccountType.ADMIN.name()+" ")) {

            type = AccountType.ADMIN;
        } else if (role.equals(" "+AccountType.KSIEGOWOSC.name()+" ")) {
            type = AccountType.KSIEGOWOSC;
        } else if (role.equals(" "+AccountType.DIAGNOSTYKA.name()+" ")) {
            type = AccountType.DIAGNOSTYKA;
        } else {
            type = AccountType.USER;
        }
        return type;

    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(LocalDateTime lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<FileDB> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileDB> fileList) {
        this.fileList = fileList;
    }

    public List<Raport> getRaportList() {
        return raportList;
    }

    public void setRaportList(List<Raport> raportList) {
        this.raportList = raportList;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    /*
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return mapRolesToAuthorities(this.getRoles());
    }
    public String getPrimaryRole() {
        String formattedAuthorities;
        List<GrantedAuthority> grantedAuthorities =new ArrayList<>(this.getAuthorities()) ;
        formattedAuthorities = grantedAuthorities.get(1).getAuthority();
        return formattedAuthorities;
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }


     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "*********" + '\'' +
                ", roles=" + roles +
                '}';
    }

}
