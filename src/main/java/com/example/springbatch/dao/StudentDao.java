package com.example.springbatch.dao;

import com.example.springbatch.pojo.Student;

import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-30 14:41
 */
public interface StudentDao {
    public List<Student> findAll();
    public Student findByid(Long id);
}
