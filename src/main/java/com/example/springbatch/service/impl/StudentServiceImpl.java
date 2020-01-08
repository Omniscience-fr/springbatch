package com.example.springbatch.service.impl;

import com.example.springbatch.dao.StudentDao;
import com.example.springbatch.pojo.Student;
import com.example.springbatch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-30 14:38
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public List<Student> findAll() {
       return studentDao.findAll();
    }

    @Override
    public Student findByid(Long id) {
        return studentDao.findByid(id);
    }
}
