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

    private String numerBadania;

    private LocalDateTime createDateTime;
    @Enumerated(EnumType.STRING)
    private StorageActionType type;

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

    public String getNumerBadania() {
        return numerBadania;
    }

    public void setNumerBadania(String numerBadania) {
        this.numerBadania = numerBadania;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public StorageActionType getType() {
        return type;
    }

    public void setType(StorageActionType type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
