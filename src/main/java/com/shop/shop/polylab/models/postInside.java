package com.shop.shop.polylab.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class postInside {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(columnDefinition = "LONGTEXT")
    private String full_text;
    private String title, date;

    private String fileNames;
    private String filePatph;
    private  String upladDate;


    public String getFilePatph() {
        return filePatph;
    }

    public void setFilePatph(String filePatph) {
        this.filePatph = filePatph;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public String getUpladDate() {
        return upladDate;
    }

    public void setUpladDate(String upladDate) {
        this.upladDate = upladDate;
    }

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

    public postInside(){

    }

    public postInside(String title, String date, String full_text){
        this.title = title;
        this.date = date;
        this.full_text = full_text;


    }

}
