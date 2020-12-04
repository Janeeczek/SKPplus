package com.JanCode.SKPplus.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "FILES")
public class FileDB {
    private String name;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;



    private String type;
    private LocalDate dataPrzeslania;
    @Lob
    private byte[] data;
    @ManyToOne
    @JoinColumn(name="tworca_id", nullable=false)
    private User user;

    @OneToMany(mappedBy="files")
    private List<Raport> raports;

    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data,User user) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.user = user;
        this.dataPrzeslania = LocalDate.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Raport> getRaports() {
        return raports;
    }

    public void setRaports(List<Raport> raports) {
        this.raports = raports;
    }

    public LocalDate getDataPrzeslania() {
        return dataPrzeslania;
    }

    public void setDataPrzeslania(LocalDate dataPrzeslania) {
        this.dataPrzeslania = dataPrzeslania;
    }
}