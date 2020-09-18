package com.training.service;

import com.training.Callback;
import com.training.bean.RemarkBean;

import java.util.List;

public interface RemarkService {

    //新增评论接口
    Callback<RemarkBean> saveRemark(RemarkBean remark);

    //根据新闻id获取评论
    Callback<List<RemarkBean>> selectByJokeId(String jokeId);

    //根据评论id删除评论
    Callback<RemarkBean> deleteById(String id);

}
