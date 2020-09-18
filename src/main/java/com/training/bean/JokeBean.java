package com.training.bean;

import java.sql.Timestamp;

public class JokeBean {

    private String jokeId;
    private String userId;
    private String title;
    private String coverImg;
    private Timestamp postTime;
    private String content;
    private String source;
    private int assortId;

    public int getAssortId() {
        return assortId;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Timestamp getPostTime() {
        return postTime;
    }
}
