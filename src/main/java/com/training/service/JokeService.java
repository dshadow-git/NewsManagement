package com.training.service;

import com.training.Callback;
import com.training.dao.JokeDao;

import java.util.List;

public interface JokeService {

    public Callback<JokeDao> saveJoke(JokeDao joke);

    public Callback<List<JokeDao>> selectJokeByAssort(String assortId);

    public Callback<List<JokeDao>> selectJokeBySearch(String search);

}
