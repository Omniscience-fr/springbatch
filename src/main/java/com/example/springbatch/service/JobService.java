package com.example.springbatch.service;



import com.example.springbatch.pojo.JobEntity;

import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-26 16:43
 */
public interface JobService {

    /**
     * 根据id获取job对象
     * @param id
     * @return
     */
    JobEntity getJobEntityById(Integer id);

    /**
     * 获取全部job对象
     * @return
     */
    List<JobEntity> getAllJobEntity();



}
