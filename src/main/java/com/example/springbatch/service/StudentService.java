package com.example.springbatch.service;

import com.example.springbatch.pojo.Student;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-30 14:37
 */
public interface StudentService {
    public List<Student> findAll();

    public Student findByid(Long id);

}
