package com.training.mapper;

import com.training.bean.RemarkBean;

import java.util.List;

public interface RemarkMapper {

    //插入新闻数据
    void save(RemarkBean remark);

    //根据新闻id获取评论列表
    List<RemarkBean> selectByJokeId(String jokeId);

    //根据新闻id获取评论数量
    int selectCountByJokeId(String jokeId);

    //根据评论id获取评论
    RemarkBean selectById(String id);

    //根据评论id删除评论
    void deleteById(String id);

}
