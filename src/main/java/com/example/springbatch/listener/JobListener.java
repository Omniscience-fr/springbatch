package com.example.springbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @Author fr
 * @Date 2020-01-02 14:05
 * JobExecutionListener Job执行监听器
 */
@Component
public class JobListener implements JobExecutionListener {

    private Logger logger = LoggerFactory.getLogger(JobListener.class);

    /**
     * Job 执行前执行
     * @param jobExecution
     */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        logger.info("任务{}于{}开始执行",jobExecution.getJobId(),dateFormat.format(jobExecution.getStartTime()));
    }
    /**
     * Job 执行后执行
     * @param jobExecution
     */
    @Override
    public void afterJob(JobExecution jobExecution) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        logger.info("任务{}于{}执行结束",jobExecution.getJobId(),dateFormat.format(jobExecution.getEndTime()));
    }
}
