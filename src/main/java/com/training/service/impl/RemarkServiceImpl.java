package com.training.service.impl;

import com.training.Callback;
import com.training.bean.RemarkBean;
import com.training.bean.UserBean;
import com.training.mapper.RemarkMapper;
import com.training.mapper.UserMapper;
import com.training.service.RemarkService;
import com.training.utils.IntactUtils;
import com.training.utils.SpareData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemarkServiceImpl implements RemarkService {

    @Autowired
    RemarkMapper mapper;

    @Autowired
    UserMapper userMapper;

    SpareData<RemarkBean> spare = new SpareData<>();
    SpareData<List<RemarkBean>> spares = new SpareData<>();

    /**
     * 插入评论数据处理
     * @param remark
     * @return
     */
    @Override
    public Callback<RemarkBean> saveRemark(RemarkBean remark) {

        if (remark == null || !IntactUtils.isIntact(remark.getContent(), remark.getJokeId(), remark.getUserId())){
            return spare.failedByParameter();
        }

        List<RemarkBean> remarks = mapper.selectByJokeId(remark.getJokeId());
        String remarkId;
        System.out.println("size:" + remarks.size());
        if (remarks.size() == 0){
            remarkId = remark.getJokeId() + "00";
        } else {
            remarkId = Long.parseLong(remarks.get(0).getRemarkId())+1 + "";
            System.out.println("remarks:" + remarks.get(0).toString());
        }
        System.out.println("id:" + remarkId);
        remark.setRemarkId(remarkId);
        System.out.println(remark.toString());
        mapper.save(remark);

        if (mapper.selectById(remarkId) == null){
            return spare.failedByBackstage();
        }
        return spare.successByInsert();
    }

    @Override
    public Callback<List<RemarkBean>> selectByJokeId(String jokeId) {

        if (jokeId == null){
            return spares.failedByParameter();
        }

        List<RemarkBean> remarks = mapper.selectByJokeId(jokeId);
        if (remarks == null || remarks.size() == 0){
            return spares.failedByParameter();
        }
        for (RemarkBean remark : remarks){
            UserBean user = userMapper.selectById(remark.getUserId());
            if (user != null){
                remark.setUser(user);
            }
        }
        return spares.successBySelect(remarks);
    }

    @Override
    public Callback<RemarkBean> deleteById(String id, String userId) {

        if (!IntactUtils.isIntact(id, userId)){
            return spare.failedByParameter();
        }

        RemarkBean remark = mapper.selectById(id);
        if (remark == null || (!remark.getUserId().equals(userId) && !remark.getJokeId().equals(userId))){
            return spare.failedByParameter();
        }

        mapper.deleteById(id);

        return spare.successByInsert();
    }
}