package com.JanCode.SKPplus.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String text;
    private LocalDateTime creationDate;
    private IconType iconType;
    private boolean viewed;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Alert() {
    }

    public Alert(String title, String text, IconType iconType, User user) {
        this.title = title;
        this.text = text;
        this.creationDate = LocalDateTime.now();
        this.iconType = iconType;
        this.viewed = false;
        this.user = user;
    }
}
