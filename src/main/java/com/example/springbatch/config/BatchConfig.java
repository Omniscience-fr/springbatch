package com.example.springbatch.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author fr
 * @Date 2019-12-30 14:16
 */
@Component
public class BatchConfig {
    @Autowired
    //注入 Job 创建工厂
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    //注入 SpringBatch 步骤创建工厂
    private StepBuilderFactory stepBuilderFactory;

    public StepBuilder setStep() {
        StepBuilder setStep = stepBuilderFactory.get("setStep");
        return setStep;
    }

    public JobBuilder setJob(){
        return jobBuilderFactory.get("setJob");
    }
}
