package com.example.springbatch.util;

import com.example.springbatch.scheduler.SchedulerJob;
import org.quartz.JobDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author fr
 * @Date 2020-01-07 16:29
 */
@Component
public class QuartzUtil {

//    public CronTriggerFactoryBean createTrigger(JobDetail jobDetail, String expression) {
//
//        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//        cronTriggerFactoryBean.setJobDetail(jobDetail);
//        cronTriggerFactoryBean.setCronExpression(expression);
//        return cronTriggerFactoryBean;
//    }
//
//    public JobDetailFactoryBean createJobDetail(Class clazz) {
//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(clazz);
//        return jobDetailFactoryBean;
//    }


}
