package com.salle.erikdavid.sallebooks.Models;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;


public class VolumeInfo implements Serializable {
    private String title;
    private String publishedDate;
    private String description;
    private ArrayList<String> authors;
    private ImageLinks imageLinks;
    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
        StringBuilder allAuthors = new StringBuilder();
        if (authors != null) {
            for (int i = 0; i < authors.size(); i++) {
                int next = i + 1;
                if ( next == authors.size()) {
                    allAuthors.append(authors.get(i));
                } else {
                    allAuthors.append(authors.get(i)).append(", ");
                }
            }
        }
        return allAuthors.toString();
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }
}