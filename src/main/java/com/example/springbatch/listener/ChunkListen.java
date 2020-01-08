package com.example.springbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.ChunkListener;
import java.text.SimpleDateFormat;

/**
 * @Author fr
 * @Date 2020-01-02 15:29
 */
@Component
@StepScope
public class ChunkListen implements ChunkListener {

    private Logger logger = LoggerFactory.getLogger(ChunkListen.class);


    @Override
    public void beforeChunk() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        logger.info("{}开始执行",format.format(System.currentTimeMillis()));
    }

    @Override
    public void onError(Exception ex) throws Exception {
        logger.info("执行报错了");
    }

    @Override
    public void afterChunk() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        logger.info("{}执行结束",format.format(System.currentTimeMillis()));
    }
}
