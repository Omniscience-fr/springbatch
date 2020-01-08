package com.example.springbatch.service.impl;

import com.example.springbatch.execture.JobExecture;
import com.example.springbatch.pojo.JobEntity;
import com.example.springbatch.repository.JobEntityRepository;
import com.example.springbatch.service.JobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-26 16:50
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobEntityRepository jobEntityRepository;

    @Override
    public JobEntity getJobEntityById(Integer id) {
        return jobEntityRepository.getById(id);
    }

    @Override
    public List<JobEntity> getAllJobEntity() {
        return jobEntityRepository.findAll();
    }

    /**
     * 获取JobDataMap.(Job参数对象)
     * @param jobEntity
     * @return
     */
    public JobDataMap getJobDataMap(JobEntity jobEntity){
        JobDataMap map = new JobDataMap();
        map.put("name",jobEntity.getJobName());
        map.put("group",jobEntity.getJobGroup());
        map.put("cron",jobEntity.getJobCron());
        map.put("parameter",jobEntity.getParameter());
        map.put("description",jobEntity.getDescription());
        map.put("status",jobEntity.getStatus());
        map.put("vmparameter",jobEntity.getVmPatameter());
        return map;
    }

    /**
     * 获取JobDetail,JobDetail是任务的定义,而Job是任务的执行逻辑,JobDetail里会引用一个Job Class来定义
     * @param jobKey
     * @param description
     * @param map
     * @return
     */
    public JobDetail getJobDetail(JobKey jobKey, String description, JobDataMap map){
        return JobBuilder.newJob(JobExecture.class)
                .withDescription(description)
                .withIdentity(jobKey)
                .setJobData(map)
                .storeDurably()
                .build();
    }

    /**
     * 获取Trigger (Job的触发器,执行规则)
     * @param jobEntity
     * @return
     */
    public Trigger getTrigger(JobEntity jobEntity){
        return TriggerBuilder.newTrigger()
                .withIdentity(jobEntity.getJobName())
                .withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getJobCron()))
                .build();
    }

    /**
     * 获取JobKey,包含Name和Group
     * @param jobEntity
     * @return
     */
    public JobKey getJobKey(JobEntity jobEntity){
        return JobKey.jobKey(jobEntity.getJobName());
    }
}
