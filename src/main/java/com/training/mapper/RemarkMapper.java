package com.training.mapper;

import com.training.bean.RemarkBean;

public interface RemarkMapper {

    //插入新闻数据
    void save(RemarkBean remark);

    RemarkBean selectByJokeId(String jokeId);

    void deleteById(String id);

}
