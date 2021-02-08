package com.JanCode.SKPplus.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Base64;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String name;
    private String description;
    @Column(unique = true)
    private String tag;
    private LocalDateTime timeCreated;
    @ManyToOne
    @JoinColumn(name="creator_username",nullable = false,referencedColumnName = "id")
    private User user;
    /*
    @OneToOne
    @JoinColumn(name="item_id",nullable = false)
    private ItemStorage itemStorage;

     */
    @Lob
    private byte[] image;
    private String contentType;

    public Item() {

    }

    public Item(String name, String description, String tag, LocalDateTime timeCreated, User user, byte[] image,String contentType) {
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.timeCreated = timeCreated;
        this.user = user;
        this.image = image;
        this.contentType = contentType;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getByte64Image() {
        //System.out.println("ZDJECIE " + getName());
        //System.out.println(Base64.getEncoder().encodeToString(this.getImage()));
        //System.out.println("================================");
            return "data:"+getContentType()+";base64,"+Base64.getEncoder().encodeToString(this.getImage());
    }
}
