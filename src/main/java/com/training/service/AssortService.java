package com.training.service;

import com.training.Callback;
import com.training.bean.AssortBean;

import java.util.List;

public interface AssortService {

    //获取所有分类数据接口
    public Callback<List<AssortBean>> selectAssortAll();
}
