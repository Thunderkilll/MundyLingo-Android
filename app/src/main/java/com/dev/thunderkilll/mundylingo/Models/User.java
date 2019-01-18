package com.dev.thunderkilll.mundylingo.Models;

public class User {

    private String id ;
    private String username ;
    private String imgUrl ;
    private String scoreFr;
    private String scoreEng;
    private String scoreSpan;
    private String scoreGer;
    private String levelGer;
    private String levelSpan;
    private String levelEng;
    private String levelFr;
    private String idFG;
    private String email ;



    public String getImgUrl() {
        return imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    public String getLevelGer() {
        return levelGer;
    }

    public void setLevelGer(String levelGer) {
        this.levelGer = levelGer;
    }

    public String getLevelSpan() {
        return levelSpan;
    }

    public void setLevelSpan(String levelSpan) {
        this.levelSpan = levelSpan;
    }

    public String getLevelEng() {
        return levelEng;
    }

    public void setLevelEng(String levelEng) {
        this.levelEng = levelEng;
    }

    public String getLevelFr() {
        return levelFr;
    }

    public void setLevelFr(String levelFr) {
        this.levelFr = levelFr;
    }

    public String getIdFG() {
        return idFG;
    }

    public void setIdFG(String idFG) {
        this.idFG = idFG;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScoreFr() {
        return scoreFr;
    }

    public void setScoreFr(String scoreFr) {
        this.scoreFr = scoreFr;
    }

    public String getScoreEng() {
        return scoreEng;
    }

    public void setScoreEng(String scoreEng) {
        this.scoreEng = scoreEng;
    }

    public String getScoreSpan() {
        return scoreSpan;
    }

    public void setScoreSpan(String scoreSpan) {
        this.scoreSpan = scoreSpan;
    }

    public String getScoreGer() {
        return scoreGer;
    }

    public void setScoreGer(String scoreGer) {
        this.scoreGer = scoreGer;
    }

    public User() {
    }

    public User(String username, String imgUrl, String scoreFr, String scoreEng, String scoreSpan, String scoreGer, String levelGer, String levelSpan, String levelEng, String levelFr, String idFG) {
        this.username = username;
        this.imgUrl = imgUrl;
        this.scoreFr = scoreFr;
        this.scoreEng = scoreEng;
        this.scoreSpan = scoreSpan;
        this.scoreGer = scoreGer;
        this.levelGer = levelGer;
        this.levelSpan = levelSpan;
        this.levelEng = levelEng;
        this.levelFr = levelFr;
        this.idFG = idFG;
    }

    public User(String username, String imgUrl, String scoreEng) {
        this.username = username;
        this.imgUrl = imgUrl;
        this.scoreEng = scoreEng;
    }
    public User(String username, String imgUrl, String scoreEng , String email) {
        this.username = username;
        this.imgUrl = imgUrl;
        this.scoreEng = scoreEng;
        this.email = email ;
    }

    public User(String username, String imgUrl, String scoreFr, String scoreEng, String scoreSpan, String scoreGer, String levelGer, String levelSpan, String levelEng, String levelFr ) {
        this.username = username;
        this.imgUrl = imgUrl;
        this.scoreFr = scoreFr;
        this.scoreEng = scoreEng;
        this.scoreSpan = scoreSpan;
        this.scoreGer = scoreGer;
        this.levelGer = levelGer;
        this.levelSpan = levelSpan;
        this.levelEng = levelEng;
        this.levelFr = levelFr;

    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", scoreFr='" + scoreFr + '\'' +
                ", scoreEng='" + scoreEng + '\'' +
                ", scoreSpan='" + scoreSpan + '\'' +
                ", scoreGer='" + scoreGer + '\'' +
                ", levelGer='" + levelGer + '\'' +
                ", levelSpan='" + levelSpan + '\'' +
                ", levelEng='" + levelEng + '\'' +
                ", levelFr='" + levelFr + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
