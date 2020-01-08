package com.example.springbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @Author fr
 * @Date 2020-01-02 14:16
 */
@Component
public class StepListener implements StepExecutionListener {

    private Logger logger = LoggerFactory.getLogger(StepListener.class);
    @Override
    public void beforeStep(StepExecution stepExecution) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        logger.info("步骤{}于{}开始执行",stepExecution.getStepName(),dateFormat.format(stepExecution.getStartTime()));
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        logger.info("步骤{}，状态为:{},{}"+stepExecution.getStepName(),stepExecution.getStatus(),stepExecution.getExitStatus());
        return stepExecution.getExitStatus();
    }
}
