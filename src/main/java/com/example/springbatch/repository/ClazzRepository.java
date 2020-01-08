package com.example.springbatch.repository;

import com.example.springbatch.pojo.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author fr
 * @Date 2019-12-30 17:22
 */
@Repository
public interface ClazzRepository extends JpaRepository<Clazz,Long> {
}
