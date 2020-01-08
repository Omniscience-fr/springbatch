package com.example.springbatch.scheduler;

import com.example.springbatch.config.BatchConfig;
import com.example.springbatch.config.QuartzConfig;
import com.example.springbatch.listener.ChunkListen;
import com.example.springbatch.listener.JobListener;
import com.example.springbatch.listener.StepListener;
import com.example.springbatch.process.StudentProcess;
import com.example.springbatch.reader.StudentReader;
import com.example.springbatch.service.StudentService;
import com.example.springbatch.util.GetBeanUtil;
import com.example.springbatch.write.StudentWrite;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author fr
 * @Date 2020-01-07 10:21
 */
@Component
public  class SchedulerJobExecture extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(SchedulerJobExecture.class);





    public ItemReader getRead(){
        return new StudentReader();
    }

    public ItemProcessor getProcess(){
        return new StudentProcess();
    }

    public ItemWriter getWrite(){
        return new StudentWrite();
    }

    public JobLauncher getJobLauncher(){
        return (JobLauncher) GetBeanUtil.getBean("jobLauncher");
    }

    public BatchConfig getBatchConfig(){
        return (BatchConfig) GetBeanUtil.getBean("batchConfig");
    }




    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        try {
            logger.info("开始执行job");
            StepBuilder stepBuilder = getBatchConfig().setStep();
            TaskletStep build = stepBuilder
                    .listener(new StepListener())
                    .chunk(2)
                    .listener(new ChunkListen())
                    .reader(getRead())
                    .processor(getProcess())
                    .writer(getWrite())
                    .faultTolerant()
                    .build();
            Job job = getBatchConfig().setJob()
                    .listener(new JobListener())
                    .start(build)
                    .build();
            JobExecution run = getJobLauncher().run(job, new JobParametersBuilder().toJobParameters());
            logger.info("开始时间{},结束时间{},状态{}",format.format(run.getStartTime()),format.format(run.getEndTime()),run.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
