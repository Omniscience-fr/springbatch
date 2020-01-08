package com.example.springbatch.reader;

import com.example.springbatch.pojo.Student;
import com.example.springbatch.service.StudentService;
import com.example.springbatch.util.GetBeanUtil;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-30 14:22
 */
public class StudentReader implements ItemReader<List<Student>> {


    private StudentService studentService;

    private boolean flag = true;

    private Integer size = 0;

    public StudentReader(){
        StudentService studentService = (StudentService) GetBeanUtil.getBean("studentService");
        this.studentService = studentService;

    }

//    @Override
//    public Student read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        if(flag){
//            flag =false;
//           return studentService.findByid(Long.valueOf(57));
//        }
//        return null;
//
//    }

    @Override
    public List<Student> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        List<Student> all = studentService.findAll();
        if(size!=all.size()){
            size = all.size();
            return all;
        }
        return null;
    }

}
