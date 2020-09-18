package com.training.service.impl;

import com.training.Callback;
import com.training.bean.RemarkBean;
import com.training.service.RemarkService;

import java.util.List;

public class RemarkServiceImpl implements RemarkService {

    @Override
    public Callback<RemarkBean> saveRemark(RemarkBean remark) {
        return null;
    }

    @Override
    public Callback<List<RemarkBean>> selectByJokeId(String jokeId) {
        return null;
    }

    @Override
    public Callback<RemarkBean> deleteById(String id) {
        return null;
    }
}