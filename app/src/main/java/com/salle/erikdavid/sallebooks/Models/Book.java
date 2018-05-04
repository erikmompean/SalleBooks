package com.salle.erikdavid.sallebooks.Models;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Book extends SugarRecord implements Serializable{
    private String emailSaved;
    private String mId;
    private String title;
    private String publishedDate;
    private String description;
    private String authors;
    private String imageURL;
    public static final int READED = 0;
    public static final int WISHED = 1;
    private int type;

    public Book(){
    }

    public Book(String id, String title, String publishedDate, String description, String authors, String imageURL) {
        this.mId = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.authors = authors;
        this.imageURL = imageURL;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String id) {
        this.mId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getType() {
        return type;
    }

    public String getEmailSaved() {
        return emailSaved;
    }

    public void setEmailSaved(String emailSaved) {
        this.emailSaved = emailSaved;
    }

    public void setType(int type) {
        this.type = type;
    }

}

