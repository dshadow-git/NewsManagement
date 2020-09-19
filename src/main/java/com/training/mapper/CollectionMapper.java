package com.training.mapper;

import java.util.List;

public interface CollectionMapper {

    //新增用户收藏的新闻
    public void save(String userId, String jokeId);

    //根据用户id获取收藏id
    public List<String> selectJokeIdByUserId(String userId);

    /**删除用户收藏*/
    void delete(String userId, String jokeId);

    /**根据userId和jokeId获取收藏关系*/
    String selectByUserIdJokeId(String userId, String jokeId);

}
