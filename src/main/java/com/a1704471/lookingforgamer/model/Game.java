package com.a1704471.lookingforgamer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Game {


    @Id
    private long id;

    private String name;
    private String coverUrl;


    public Game() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @SuppressWarnings("unchecked")
    @JsonProperty("cover")
    private void unpackNested(Map<String, Object> cover){
        this.coverUrl = (String)cover.get("url");
    }
}
