package com.example.springbatch.scheduler;

import com.example.springbatch.config.QuartzConfig;
import com.example.springbatch.util.GetBeanUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author fr
 * @Date 2020-01-06 18:06
 */
@Component
public class SchedulerJob extends SchedulerJobExecture {
    private Logger logger = LoggerFactory.getLogger(SchedulerJob.class);


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("{}类开始执行",SchedulerJob.class.getName());
        super.executeInternal(context);
    }

}
