package com.example.demo.dao;

import com.example.demo.bean.Course;
import com.example.demo.bean.JqBean;
import com.example.demo.bean.Student;
import com.example.demo.bean.TestBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    List<Student> getStudentAll();

    List<Course> getCourseAll();

    Student getStudent(String str);

    List<Student> getStudents(String str);

    int getNum(String time);

    List<TestBean> selectTestBean(String time);

    int insertTestBean(List<TestBean> testBeans);

    Map insertData(@Param("map") Map map, @Param("tableName") String table);

    int insertDatas(@Param("map") Map map, @Param("tableName") String table);

    int insertData1(@Param("map") Map map, @Param("tableName") String table, @Param("del") String del);

    int insertErrorData(@Param("tableName") String table, @Param("error") String error);

    String selectError(String tableName);

    int insertJq(List<JqBean> jqList);
}
