package com.example.springbatch.controller;

import com.example.springbatch.config.BatchConfig;
import com.example.springbatch.config.QuartzConfig;
import com.example.springbatch.listener.ChunkListen;
import com.example.springbatch.listener.JobListener;
import com.example.springbatch.listener.StepListener;
import com.example.springbatch.pojo.JobEntity;
import com.example.springbatch.pojo.Student;
import com.example.springbatch.process.StudentProcess;
import com.example.springbatch.reader.StudentReader;
import com.example.springbatch.service.JobService;
import com.example.springbatch.service.StudentService;
import com.example.springbatch.service.impl.JobServiceImpl;
import com.example.springbatch.util.GetBeanUtil;
import com.example.springbatch.util.QuartzUtil;
import com.example.springbatch.write.StudentWrite;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author fr
 * @Date 2019-12-31 09:43
 */
@RestController
@RequestMapping(value = "/Job")
public class JobController {

    private Logger logger = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private StudentService studentService;
    @Autowired
    private BatchConfig batchConfig;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Autowired
    private JobService jobService;



    @Bean
    public ItemReader getRead(){
        return new StudentReader();
    }
    @Bean
    public ItemProcessor getProcess(){
        return new StudentProcess();
    }
    @Bean
    public ItemWriter getWrite(){
        return new StudentWrite();
    }


    @GetMapping(value = "/starter")
    @ResponseBody
    public String startJob(){
        try {
            logger.info("开始执行job");
            StepBuilder stepBuilder = batchConfig.setStep();
            TaskletStep build = stepBuilder
                    .listener(new StepListener())
                    .chunk(2)
                    .listener(new ChunkListen())
                    .reader(getRead())
                    .processor(getProcess())
                    .writer(getWrite())
                    .faultTolerant()
                    .retryLimit(3)
                    .build();
            Job job = batchConfig.setJob()
                    .listener(new JobListener())
                    .start(build)
                    .build();

            jobLauncher.run(job,new JobParametersBuilder().toJobParameters());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "启动成功";
    }

    @GetMapping(value = "/getStudents")
    @ResponseBody
    public List<Student> getStudents(){
        Resource[] resources = new Resource[10];
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();

        return studentService.findAll();
    }
    @PostConstruct
    public void initialize(){
        try {
            startAllJobs();
            logger.info("INIT SUCCESS");
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void startAllJobs() throws SchedulerException, ClassNotFoundException {
        synchronized (logger) {
            for (JobEntity jobEntity : jobService.getAllJobEntity()) {
                logger.info("获取执行类{}", Class.forName(jobEntity.getJobName()));
                JobDetail jobDetail = JobBuilder.newJob((Class<? extends org.quartz.Job>) Class.forName(jobEntity.getJobName())).storeDurably().build();
                CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getJobCron())).build();
                Scheduler scheduler = schedulerFactoryBean.getScheduler();
                scheduler.scheduleJob(jobDetail,trigger);
                scheduler.start();
                String jobCron = jobEntity.getJobCron();
                logger.info("cron表达式{}",jobCron);

            }
        }
    }


}
