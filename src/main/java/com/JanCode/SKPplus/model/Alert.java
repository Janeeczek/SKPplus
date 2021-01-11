package com.JanCode.SKPplus.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Alert {
    private enum IconType {
        WARNING, INFORMATION, TEXT;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String shortText;
    private String longText;
    private Date creationDate;
    private IconType iconType;
    private boolean viewed;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "alerts_users",
            joinColumns = @JoinColumn(
                    name = "alert_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private List<User> user;

    public Alert() {
    }

    public Alert(String title, String shortText, String longText, Date creationDate, IconType iconType, List<User> user) {
        this.title = title;
        this.shortText = shortText;
        this.longText = longText;
        this.creationDate = Date.from(Instant.from(LocalDate.now()));
        this.iconType = iconType;
        this.viewed = false;
        this.user = user;
    }
}
