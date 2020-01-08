package com.example.springbatch.dao.impl;

import com.example.springbatch.dao.ClazzDao;
import com.example.springbatch.pojo.Clazz;
import com.example.springbatch.repository.ClazzRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author fr
 * @Date 2019-12-30 17:22
 */
@Repository("clazzDao")
public class ClazzDaoImpl implements ClazzDao {
    @Autowired
    private ClazzRepository clazzRepository;
    @Override
    public void save(Clazz clazz) {
        clazzRepository.save(clazz);
    }
}
