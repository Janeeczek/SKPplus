package com.JanCode.SKPplus.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ItemStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne()
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    private int quantity;
    private int actualQuantity;
    private LocalDateTime timeUpdated;

    public ItemStorage(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.actualQuantity = quantity;
        this.timeUpdated = LocalDateTime.now();
    }

    public long getId() {
        return id;
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
}
