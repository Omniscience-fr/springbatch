package com.example.springbatch.write;

import com.example.springbatch.pojo.Clazz;
import com.example.springbatch.pojo.Student;
import com.example.springbatch.service.ClazzService;
import com.example.springbatch.service.StudentService;
import com.example.springbatch.util.GetBeanUtil;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

/**
 * @Author fr
 * @Date 2019-12-30 17:14
 */
public class StudentWrite implements ItemWriter<List<Student>> {

    private ClazzService clazzService;


    public StudentWrite(){
        ClazzService clazzServices = (ClazzService) GetBeanUtil.getBean("clazzService");
        this.clazzService = clazzServices;

    }

//    @Override
//    public void write(List<? extends Student> items) throws Exception {
//        for (Student student:items) {
//                Clazz clazz = new Clazz();
//                Random random = new Random();
//                int s = random.nextInt(3);
//                clazz.setClassName(s+"班");
//                clazz.setStudentID(student.getId());
//                clazz.setStudentName(student.getName());
//                clazzService.save(clazz);
//        }
//    }


    @Override
    public void write(List<? extends List<Student>> items) throws Exception {
        for (List<Student> students:items){
            for (Student student:students) {
                Clazz clazz = new Clazz();
                Random random = new Random();
                int s = random.nextInt(3);
                clazz.setClassName(s+"班");
                clazz.setStudentID(student.getId());
                clazz.setStudentName(student.getName());
                clazzService.save(clazz);
            }

        }
    }
}
