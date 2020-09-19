package com.training.bean;

import java.util.Date;

public class RemarkBean {

    private String remarkId;
    private String userId;
    private String jokeId;
    private String content;
    private Date postTime;

    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "RemarkBean{" +
                "remarkId='" + remarkId + '\'' +
                ", userId='" + userId + '\'' +
                ", jokeId='" + jokeId + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                '}';
    }
}
