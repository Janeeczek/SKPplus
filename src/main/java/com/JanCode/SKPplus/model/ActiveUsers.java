package com.JanCode.SKPplus.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class ActiveUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String email;

    public ActiveUsers() {
    }

    public ActiveUsers(String email) {
        this.email = email;
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
}
