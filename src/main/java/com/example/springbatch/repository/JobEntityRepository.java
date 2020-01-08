package com.example.springbatch.repository;

import com.example.springbatch.pojo.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author fr
 * @Date 2019-12-26 15:34
 */
public interface JobEntityRepository extends JpaRepository<JobEntity, Long> {

    JobEntity getById(Integer id);
}
