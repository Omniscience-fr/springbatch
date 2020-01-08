package com.example.springbatch.execture;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author fr
 * @Date 2020-01-06 17:51
 */
public abstract class JobExecture implements Job {

    private Logger looger = LoggerFactory.getLogger(JobExecture.class);

    public abstract JobExecution doJob();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobExecution jobExecution = doJob();
        Date endTime = jobExecution.getEndTime();
        Date startTime = jobExecution.getStartTime();
        BatchStatus status = jobExecution.getStatus();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd ss:mm");
        if(startTime!=null&&endTime!=null){
            looger.info("开始时间为{},结束时间为{},状态为{}",dateFormat.format(startTime),dateFormat.format(endTime),status);
        }else {
            looger.info("任务已经执行过了！");
        }

    }
}
