package com.example.demo;

import com.example.demo.bean.Student;
//import com.example.demo.config.StudentConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
public class StudentTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
//    @Test
//    public void test(){
//        applicationContext.register(StudentConfig.class);
//        applicationContext.register(Student.class);
//        applicationContext.refresh();
//        Student bean = applicationContext.getBean(Student.class);
//        System.out.println(bean.toString());
//    }
}
