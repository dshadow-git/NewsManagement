package com.training.service.impl;

import com.training.Callback;
import com.training.bean.AssortBean;
import com.training.mapper.AssortMapper;
import com.training.service.AssortService;
import com.training.utils.SpareData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssortServiceImpl implements AssortService {

    @Autowired
    AssortMapper mapper;

    SpareData<AssortBean> spare = new SpareData<>();

    SpareData<List<AssortBean>> spares = new SpareData<>();

    @Override
    public Callback<List<AssortBean>> selectAssortAll() {
        List<AssortBean> assorts = mapper.selectAll();

        if (assorts == null){
            return spares.failedByBackstage();
        }
        return spares.successBySelect(assorts);
    }
}
