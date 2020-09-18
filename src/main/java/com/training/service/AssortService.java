package com.training.service;

import com.training.Callback;
import com.training.bean.AssortBean;

import java.util.List;

public interface AssortService {

    public Callback<List<AssortBean>> selectAssortAll();
}
