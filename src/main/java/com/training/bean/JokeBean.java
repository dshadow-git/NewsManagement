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

    public JokeBean(String userId, String title, Timestamp postTime, String content, String source, int assortId) {
        this.userId = userId;
        this.title = title;
        this.postTime = postTime;
        this.content = content;
        this.source = source;
        this.assortId = assortId;
    }

    public JokeBean() {
    }

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

    @Override
    public String toString() {
        return "JokeBean{" +
                "jokeId='" + jokeId + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", postTime=" + postTime +
                ", content='" + content + '\'' +
                ", source='" + source + '\'' +
                ", assortId=" + assortId +
                '}';
    }
}
