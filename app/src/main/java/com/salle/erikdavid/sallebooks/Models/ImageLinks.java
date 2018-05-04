package com.salle.erikdavid.sallebooks.Models;

import java.io.Serializable;


public class ImageLinks implements Serializable {
    private String bookId;
    private String smallThumbnail;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }
}
