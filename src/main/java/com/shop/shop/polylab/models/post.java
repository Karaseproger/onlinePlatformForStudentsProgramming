package com.shop.shop.polylab.models;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class post {
    @Id
    @GeneratedValue()
    private Long id;

    private String title, date;

    @Column(columnDefinition = "LONGTEXT")
    private String full_text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public post(){

    }

    public post(String title, String date, String full_text){
        this.title = title;
        this.date = date;
        this.full_text = full_text;


    }
}
