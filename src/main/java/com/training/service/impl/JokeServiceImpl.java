package com.training.service.impl;

import com.training.Callback;
import com.training.bean.JokeBean;
import com.training.bean.RemarkBean;
import com.training.bean.UserBean;
import com.training.mapper.CollectionMapper;
import com.training.mapper.JokeMapper;
import com.training.mapper.RemarkMapper;
import com.training.mapper.UserMapper;
import com.training.service.JokeService;
import com.training.service.RemarkService;
import com.training.utils.IntactUtils;
import com.training.utils.SpareData;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JokeServiceImpl implements JokeService {

    @Autowired
    JokeMapper mapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RemarkMapper remarkMapper;

    @Autowired
    CollectionMapper collectionMapper;

    @Autowired
    RemarkService remarkService;

    SpareData<JokeBean> spare = new SpareData<>();

    SpareData<List<JokeBean>> spares = new SpareData<>();

    @Override
    public Callback<JokeBean> saveJoke(JokeBean joke) {

        if (joke == null || !IntactUtils.isIntact(joke.getContent(), joke.getPostTime(),
                joke.getUserId(), joke.getAssortId())){
            return spare.failedByParameter();
        }

        //随机生成用户id，并判断该id是否已被用，若被用则重新生成
        String id;
        JokeBean jokeBean;
        int count = 0;
        do{
            //若1000次生成id后还未成功则判断插入失败
            if (count >= 1000){
                return spare.failedByBackstage();
            }
            count ++;

            //把随机生成的整形数据格式化成字符串
            id = 2 + String.format("%06d", new Random().nextInt(99999));

            //根据获取id获取joke实例，若为空则未被占用，
            jokeBean = mapper.selectById(id);
        }while (jokeBean != null);

        joke.setJokeId(id);

        mapper.save(joke);
        if (mapper.selectById(joke.getJokeId()) == null){
            return spare.failedByBackstage();
        } else {
            //判断上传的数据中cover和source是否为空，若不为空则保存到数据库中
            if (joke.getJokeId() != null){
                mapper.updateCoverById(joke.getJokeId(), joke.getCoverImg());
            }

            if (joke.getSource() != null){
                mapper.updateSourceById(joke.getJokeId(), joke.getSource());
            }

            return spare.successBySelect(joke);
        }
    }

    @Override
    public Callback<List<JokeBean>> selectJokeByAssort(String assortId) {

        if (assortId == null){
            return spares.failedByParameter();
        }

        int aId = Integer.parseInt(assortId);

        List<JokeBean> jokes;

        if (aId == 0){
            jokes = mapper.selectAll();
        } else {
            jokes = mapper.selectByAssortId(aId);
        }

        if (jokes == null){
            return spares.failedByBackstage();
        }
        return spares.successBySelect(jokes);
    }

    @Override
    public Callback<List<JokeBean>> selectJokeBySearch(String search) {
        if (search == null){
            return spares.failedByParameter();
        }

        List<JokeBean> jokes;
        jokes = mapper.selectBySearch("%" + search + "%");
        if (jokes == null){
            return spares.failedByBackstage();
        }
        return spares.successBySelect(jokes);
    }

    @Override
    public Callback<JokeBean> deleteJokeById(String userId, String jokeId) {
        if (!IntactUtils.isIntact(userId, jokeId)){
            return spare.failedByParameter();
        }

        UserBean userBean = userMapper.selectById(userId);
        JokeBean jokeBean = mapper.selectById(jokeId);

        if (!IntactUtils.isIntact(userBean, jokeBean)){
            return spare.failedByParameter();
        }

        if (!userBean.isType() && !jokeBean.getUserId().equals(userId)){
            return spare.failedByJurisdiction();
        }

        try {
            mapper.deleteById(jokeId);
        }catch (SqlSessionException e){
            e.printStackTrace();
            return spare.failed(e.getMessage());
        }
        return spare.successByInsert();
    }

    @Override
    public Callback<JokeBean> selectJokeById(String jokeId, String userId) {
        if (jokeId == null){
            return spare.failedByParameter();
        }

        JokeBean joke = mapper.selectById(jokeId);

        if (joke == null){
            return spare.failedByParameter();
        }

        if (collectionMapper.selectByUserIdJokeId(userId, jokeId) != null){
            joke.setCollete(true);
        }

        UserBean user = userMapper.selectById(joke.getUserId());
        if (user == null){
            user = new UserBean();
        }

        joke.setUser(user);

        List<RemarkBean> remarks;
        Callback<List<RemarkBean>> callback = remarkService.selectByJokeId(jokeId);
        if (callback.getCode() == SpareData.CALL_SUCCESS){
            remarks = callback.getData();
        } else {
            remarks = new ArrayList<>();
        }
        joke.setRemarks(remarks);
        return spare.successBySelect(joke);
    }

    @Override
    public Callback<List<JokeBean>> selectJokeByCollection(String userId) {

        if (userId == null){
            return spares.failedByParameter();
        }

        if (userMapper.selectById(userId) == null){
            return spares.failedByParameter();
        }

        List<JokeBean> jokes = mapper.selectByCollectionUserId(userId);
        if (jokes == null){
            jokes = new ArrayList<>();
        }

        return spares.successBySelect(jokes);
    }
}