package com.example.demo.service;

import com.example.demo.bean.Course;
import com.example.demo.bean.JqBean;
import com.example.demo.bean.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> getStudentAll();

    List<Course> getCourseAll();

    Student getStudent(String str);

    Map insertData(Map map, String table);

    int insertDatas(Map map, String table);

    int insertData1(Map map, String table, String del);

    List<Student> getStudents(String str);

    int insertErrorData(String table, String error);

    String selectError(String tableName);

    int insertJq(List<JqBean> jqList);

    List<Student> test(String str1, String str2);

    void test2(Map map);

    void test3(Map map);
}
