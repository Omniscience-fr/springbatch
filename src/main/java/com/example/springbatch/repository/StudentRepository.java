package com.example.springbatch.repository;

import com.example.springbatch.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author fr
 * @Date 2019-12-30 14:39
 */
public interface StudentRepository extends JpaRepository<Student,Long> {

   public Student findStudentById(Long id);
}
