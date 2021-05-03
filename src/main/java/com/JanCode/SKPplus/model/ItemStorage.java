package com.JanCode.SKPplus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ItemStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name="item_id",nullable = false,referencedColumnName = "id")
    private Item item;
    private int quantity;
    private int actualQuantity;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
    private boolean archived;

    public ItemStorage() {
    }

    public ItemStorage(Item item, int quantity) {
        this.item = item;
        this.actualQuantity = quantity;
        this.quantity = quantity;
        this.timeUpdated = LocalDateTime.now();
        this.timeCreated = timeUpdated;
        this.archived = false;
    }

    public long getId() {
        return id;
    }
    public String getStringId() {

        return String.valueOf(id);
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(int actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    public String getItemName() {
        return item.getName();
    }


}
