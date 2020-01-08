package com.example.springbatch.process;

import com.example.springbatch.pojo.Clazz;
import com.example.springbatch.pojo.Student;
import com.example.springbatch.service.ClazzService;
import com.example.springbatch.service.impl.ClazzServiceImpl;
import com.example.springbatch.util.GetBeanUtil;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @Author fr
 * @Date 2019-12-30 17:07
 */
public class StudentProcess implements ItemProcessor<List<Student>,List<Student>>{
//    @Override
//    public Student process(Student item) throws Exception {
//        return item;
//    }

//    private ClazzService clazzService;
//
//    public StudentProcess(){
//        clazzService = (ClazzService) GetBeanUtil.getBean("clazzService");
//    }

    @Override
    public List<Student> process(List<Student> item) throws Exception {
        return item;
    }
}
