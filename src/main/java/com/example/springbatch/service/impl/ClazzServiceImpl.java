package com.example.springbatch.service.impl;

import com.example.springbatch.dao.ClazzDao;
import com.example.springbatch.pojo.Clazz;
import com.example.springbatch.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author fr
 * @Date 2019-12-30 17:21
 */
@Service("clazzService")
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzDao clazzDao;

    @Override
    public void save(Clazz clazz) {
    clazzDao.save(clazz);
    }
}
