package com.a1704471.lookingforgamer.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Poster {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    Long gameId;

    Date dateLeft;

    String title;
    String details;

    //PlaceHolder
    String user;

    //PlaceHolder
    String platform;

    int playerAmount;
    ArrayList<String> usersSigned;

    public Poster(){}

    public Poster(Long gameId, Date dateLeft, String title, String details, String user, String platform, int playerAmount) {
        this.gameId = gameId;
        this.dateLeft = dateLeft;
        this.title = title;
        this.details = details;
        this.user = user;
        this.platform = platform;
        this.playerAmount = playerAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getDateLeft() {
        return dateLeft;
    }

    public void setDateLeft(Date dateLeft) {
        this.dateLeft = dateLeft;
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
                ", version=" + version +
                ", gameId='" + gameId + '\'' +
                ", dateLeft=" + dateLeft +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", user='" + user + '\'' +
                ", platform='" + platform + '\'' +
                ", playerAmount=" + playerAmount +
                ", usersSigned=" + usersSigned +
                '}';
    }
}