package com.example.springbatch.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author fr
 * @Date 2019-12-30 14:28
 */
@Entity
@Table(name = "student")
@Data
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String sex;
    private Integer age;
}
