package com.example.springbatch.execture;

import com.example.springbatch.config.BatchConfig;
import com.example.springbatch.listener.ChunkListen;
import com.example.springbatch.listener.JobListener;
import com.example.springbatch.listener.StepListener;
import com.example.springbatch.process.StudentProcess;
import com.example.springbatch.reader.StudentReader;
import com.example.springbatch.util.GetBeanUtil;
import com.example.springbatch.write.StudentWrite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

import java.text.SimpleDateFormat;

/**
 * @Author fr
 * @Date 2020-01-08 10:48
 */
public class DoJob extends JobExecture {

    private Logger logger = LoggerFactory.getLogger(DoJob.class);
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
    public JobExecution doJob() {
        logger.info("{}类开始执行",DoJob.class.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        JobExecution run = null;
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
            run = getJobLauncher().run(job, new JobParametersBuilder().toJobParameters());
            logger.info("开始时间{},结束时间{},状态{}",format.format(run.getStartTime()),format.format(run.getEndTime()),run.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return run;
    }
}
