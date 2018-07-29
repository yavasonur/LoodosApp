package com.example.onur.loodosapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Film {

    private String title;
    private String year;
    private String imdbId;
    private String type;
    private String poster;

    public static List<Film> filmList = new ArrayList<Film>();

    public Film(String title,String year, String imdbId, String type, String poster){
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.type = type;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
