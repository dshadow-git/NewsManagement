package com.training.service.impl;

import com.training.Callback;
import com.training.dao.JokeDao;
import com.training.mapper.JokeMapper;
import com.training.service.JokeService;
import com.training.utils.IntactUtils;
import com.training.utils.SpareData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class JokeServiceImpl implements JokeService {

    @Autowired
    JokeMapper mapper;

    SpareData<JokeDao> spare = new SpareData<>();

    SpareData<List<JokeDao>> spares = new SpareData<>();

    @Override
    public Callback<JokeDao> saveJoke(JokeDao joke) {

        if (joke == null || !IntactUtils.isIntact(new Object[]{joke.getContent(), joke.getPostTime(),
                joke.getUserId(), joke.getAssortId()})){
            return spare.failedByParameter();
        }

        //随机生成用户id，并判断该id是否已被用，若被用则重新生成
        String id;
        JokeDao jokeDao;
        int count = 0;
        do{
            //若1000次生成id后还未成功则判断插入失败
            if (count >= 1000){
                return spare.failedByBackstage();
            }
            count ++;

            //把随机生成的整形数据格式化成字符串
            id = 1 + String.format("%06d", new Random().nextInt(99999));

            //根据获取id获取joke实例，若为空则未被占用，
            jokeDao = mapper.selectById(id);
        }while (jokeDao != null);

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

            return spare.successByInsert();
        }
    }

    @Override
    public Callback<List<JokeDao>> selectJokeByAssort(String assortId) {

        if (assortId == null){
            return spares.failedByParameter();
        }

        int aId = Integer.parseInt(assortId);

        List<JokeDao> jokes;

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
    public Callback<List<JokeDao>> selectJokeBySearch(String search) {
        if (search == null){
            return spares.failedByParameter();
        }

        List<JokeDao> jokes;
        jokes = mapper.selectBySearch("%" + search + "%");
        if (jokes == null){
            return spares.failedByBackstage();
        }
        return spares.successBySelect(jokes);
    }
}