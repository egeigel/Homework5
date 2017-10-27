package com.example.edward.homework5;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edward on 10/23/17.
 */

public class Entry implements Serializable {
    private String title;
    private String summary;
    private String releaseDate;
    private String updatedDate;
    private String smallImage;
    private String largeImage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this.releaseDate.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date getUpdatedDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(this.updatedDate.trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public void setUpdatedDate(String updatedDate) {this.updatedDate = updatedDate;}

    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate;}

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }
}
