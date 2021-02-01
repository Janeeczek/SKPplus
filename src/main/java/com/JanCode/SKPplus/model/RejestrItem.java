package com.JanCode.SKPplus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RejestrItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="itemstorage_id", nullable=false,referencedColumnName = "id")
    private ItemStorage itemStorage;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false,referencedColumnName = "id")
    private User user;

    private String message;

    private LocalDateTime createDateTime;

    private String type;

    private int value;

    public RejestrItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemStorage getItemStorage() {
        return itemStorage;
    }

    public void setItemStorage(ItemStorage itemStorage) {
        this.itemStorage = itemStorage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
