package com.JanCode.SKPplus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String shortName;
    private String tag;
    private LocalDateTime timeCreated;
    @ManyToOne
    @JoinColumn(name="creator_username",nullable = false,referencedColumnName = "userName")
    private User userCreator;
    @OneToOne
    @JoinColumn(name="item_id",nullable = false)
    private ItemStorage itemStorage;
    @Lob
    private byte[] image;

    public Item() {
    }

    public Item(String name, String shortName, String tag, LocalDateTime timeCreated, User userCreator, byte[] image) {
        this.name = name;
        this.shortName = shortName;
        this.tag = tag;
        this.timeCreated = timeCreated;
        this.userCreator = userCreator;
        this.image = image;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public User getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(User userCreator) {
        this.userCreator = userCreator;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
