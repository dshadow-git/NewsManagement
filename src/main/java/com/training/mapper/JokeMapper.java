package com.training.mapper;

import com.training.bean.JokeBean;

import java.util.List;

public interface JokeMapper {

    public void save(JokeBean joke);

    public List<JokeBean> selectAll();

    public List<JokeBean> selectByAssortId(int assortId);

    public List<JokeBean> selectBySearch(String search);

    public JokeBean selectById(String id);

    public void updateCoverById(String id, String cover);

    public void updateSourceById(String id, String source);

    //删除新闻连接数据库接口
    void deleteById(String jokeId);
}
