package com.training.mapper;

import com.training.dao.JokeDao;

import java.util.List;

public interface JokeMapper {

    public void save(JokeDao joke);

    public List<JokeDao> selectAll();

    public List<JokeDao> selectByAssortId(int assortId);

    public List<JokeDao> selectBySearch(String search);

    public JokeDao selectById(String id);

}
