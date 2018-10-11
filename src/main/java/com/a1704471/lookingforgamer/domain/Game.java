package com.a1704471.lookingforgamer.domain;

import javax.persistence.Entity;

@Entity
public class Game {

    private String name;
    private String coverUrl;


    public Game() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
