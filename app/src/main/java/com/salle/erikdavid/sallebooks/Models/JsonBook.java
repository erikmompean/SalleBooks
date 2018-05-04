package com.salle.erikdavid.sallebooks.Models;

import com.orm.SugarRecord;
import com.salle.erikdavid.sallebooks.Models.VolumeInfo;

import java.io.Serializable;


public class JsonBook implements Serializable {
    private String id;
    private VolumeInfo volumeInfo;
    private int type;
    public String getmId() {
        return id;
    }

    public void setmId(String id) {
        this.id = id;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
    public enum state {
        READED, WISHED
    }

    public Book toBook(){
        return new Book(id, volumeInfo.getTitle(), volumeInfo.getPublishedDate(),volumeInfo.getDescription(),volumeInfo.getAuthors(), volumeInfo.getImageLinks().getSmallThumbnail());
    }
}
