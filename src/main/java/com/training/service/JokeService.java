package com.training.service;

import com.training.Callback;
import com.training.bean.JokeBean;

import java.util.List;

public interface JokeService {

    public Callback<JokeBean> saveJoke(JokeBean joke);

    public Callback<List<JokeBean>> selectJokeByAssort(String assortId);

    public Callback<List<JokeBean>> selectJokeBySearch(String search);

    //删除service新闻接口
    Callback<JokeBean> deleteJokeById(String userId, String jokeId);

    //根据新闻id获取新闻，需附带评论数据
    Callback<JokeBean> selectJokeById(String jokeId, String userId);

    Callback<List<JokeBean>> selectJokeByCollection(String userId);

}
