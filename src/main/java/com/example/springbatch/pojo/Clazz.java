package com.example.springbatch.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author fr
 * @Date 2019-12-30 17:17
 */
@Entity
@Table(name = "clazz")
@Data
public class Clazz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ClassName;
    private String StudentName;
    private Long StudentID;
}
