package com.example.demo.service.impl;

import com.example.demo.bean.Course;
import com.example.demo.bean.JqBean;
import com.example.demo.bean.Student;
import com.example.demo.dao.StudentDao;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao dao;

    @Override
    public List<Student> getStudentAll() {
        return dao.getStudentAll();
    }

    @Override
    public List<Course> getCourseAll() {
        return dao.getCourseAll();
    }

    @Override
    public Student getStudent(String str) {
        return dao.getStudent(str);
    }

    @Override
    public Map insertData(Map map, String table) {
        return dao.insertData(map, table);
    }

    @Override
    public int insertDatas(Map map, String table) {
        return dao.insertDatas(map, table);
    }

    @Override
    public int insertData1(Map map, String table, String del) {
        return dao.insertData1(map, table, del);
    }

    @Override
    public List<Student> getStudents(String str) {
        return dao.getStudents(str);
    }

    @Override
    public int insertErrorData(String table, String error) {
        return dao.insertErrorData(table, error);
    }

    @Override
    public String selectError(String tableName) {
        return dao.selectError(tableName);
    }

    @Override
    public int insertJq(List<JqBean> jqList) {
        return dao.insertJq(jqList);
    }

    @Async
    @Override
    public List<Student> test(String str1, String str2) {
        List<Student> studentAll = dao.getStudentAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime tjrq_s = LocalDateTime.parse(str1 + "000000", formatter);
        LocalDateTime tjrq_e = LocalDateTime.parse(str2 + "235959", formatter);
        int day = tjrq_e.getDayOfMonth() - tjrq_s.getDayOfMonth();
        for (int i = 0; i <= day; i++) {
            tjrq_e = tjrq_s.plusSeconds(86399);
            System.out.println(formatter.format(tjrq_s));
            System.out.println(formatter.format(tjrq_e));
            tjrq_s = tjrq_s.plusSeconds(86400);
        }
        System.out.println(studentAll.toString());
        return studentAll;
    }

    @Async
    @Override
    public void test2(Map map) {
        map.put("name", "张三");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("Calss1:" + map.getClass());
        Student student = dao.getStudent((String) map.get("name"));
        System.out.println(student);
    }

    @Async
    @Override
    public void test3(Map map) {
        map.put("name", "李四");
        System.out.println(Thread.currentThread().getName());
        System.out.println("Calss2:" + map.getClass());
        Student student = dao.getStudent((String) map.get("name"));
        System.out.println(student);
    }
}
