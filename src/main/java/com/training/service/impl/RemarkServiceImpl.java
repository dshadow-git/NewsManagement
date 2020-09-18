package com.training.service.impl;

import com.training.Callback;
import com.training.bean.RemarkBean;
import com.training.mapper.RemarkMapper;
import com.training.service.RemarkService;
import com.training.utils.IntactUtils;
import com.training.utils.SpareData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RemarkServiceImpl implements RemarkService {

    @Autowired
    RemarkMapper mapper;

    SpareData<RemarkBean> spare = new SpareData<>();
    SpareData<List<RemarkBean>> spares = new SpareData<>();

    /**
     * 插入评论数据处理
     * @param remark
     * @return
     */
    @Override
    public Callback<RemarkBean> saveRemark(RemarkBean remark) {

        if (remark == null || !IntactUtils.isIntact(remark.getContent(), remark.getJokeId(), remark.getPostTime(), remark.getUserId())){
            return spare.failedByParameter();
        }

        int count = mapper.selectCountByJokeId(remark.getJokeId());
        String remarkId = remark.getJokeId() + String.format("%02s", count);
        remark.setRemarkId(remarkId);
        mapper.save(remark);

        if (mapper.selectById(remarkId) == null){
            return spare.failedByBackstage();
        }
        return spare.successByInsert();
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