package com.training.service;

import com.training.Callback;
import com.training.dao.AssortDao;

import java.util.List;

public interface AssortService {

    public Callback<List<AssortDao>> selectAssortAll();
}
