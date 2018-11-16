package com.a1704471.lookingforgamer.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Long gameId;

    String gameName;

    Date date;

    String title;
    String details;

    //PlaceHolder
    String user;

    //PlaceHolder
    String platform;

    int playerAmount;

    ArrayList<String> usersSigned;

    public Poster() {
    }


    public Poster(Long gameId, String gameName, String title, String details, String user, String platform, int playerAmount){
        this.gameId = gameId;
        this.gameName = gameName;
        this.title = title;
        this.details = details;
        this.user = user;
        this.platform = platform;
        this.playerAmount = playerAmount;
    }

    public Poster(Long gameId, String gameName, Date date, String title, String details, String user, String platform, int playerAmount) {
        this.gameId = gameId;
        this.date = date;
        this.title = title;
        this.details = details;
        this.user = user;
        this.platform = platform;
        this.playerAmount = playerAmount;
        this.gameName = gameName;
    }


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date dateLeft) {
        this.date = dateLeft;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public void setPlayerAmount(int playerAmount) {
        this.playerAmount = playerAmount;
    }

    public List<String> getUsersSigned() {
        return usersSigned;
    }

    public void setUsersSigned(ArrayList<String> usersSigned) {
        this.usersSigned = usersSigned;
    }

    @Override
    public String toString() {
        return "Poster{" +
                "id=" + id +
                ", gameId='" + gameId + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", user='" + user + '\'' +
                ", platform='" + platform + '\'' +
                ", playerAmount=" + playerAmount +
                ", usersSigned=" + usersSigned +
                '}';
    }
}