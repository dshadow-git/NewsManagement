package com.training.service.impl;

import com.training.Callback;
import com.training.dao.AssortDao;
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

    SpareData<AssortDao> spare = new SpareData<>();

    SpareData<List<AssortDao>> spares = new SpareData<>();

    @Override
    public Callback<List<AssortDao>> selectAssortAll() {
        List<AssortDao> assorts = mapper.selectAll();

        if (assorts == null){
            return spares.failedByBackstage();
        }
        return spares.successBySelect(assorts);
    }
}
