package com.example.springbatch.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author fr
 * @Date 2019-12-26 14:52
 */
@Entity
@Table(name = "JOB_ENTITY")
@Data
public class JobEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String JobName;
    @Column(name = "group")
    private String JobGroup;
    @Column(name = "cron")
    private String JobCron;
    private String parameter;
    @Column(name = "vm_param")
    private String vmPatameter;
    private String status;
    private String description;


    @Override
    public String toString() {
        return "JobEntity{" +
                "id=" + id +
                ", JobName='" + JobName + '\'' +
                ", JobGroup='" + JobGroup + '\'' +
                ", JobCron='" + JobCron + '\'' +
                ", parameter='" + parameter + '\'' +
                ", vmPatameter='" + vmPatameter + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
