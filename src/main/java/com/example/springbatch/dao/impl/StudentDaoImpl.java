package com.example.springbatch.dao.impl;

import com.example.springbatch.dao.StudentDao;
import com.example.springbatch.pojo.Student;
import com.example.springbatch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author fr
 * @Date 2019-12-30 14:41
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {

        return studentRepository.findAll();
    }

    @Override
    public Student findByid(Long id) {
        return studentRepository.findStudentById(id);
    }
}
