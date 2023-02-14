package com.example.demo.service.impl;

import com.example.demo.bean.Student;
import com.example.demo.bean.TestBean;
import com.example.demo.dao.StudentDao;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class ThreadService {
    @Autowired
    private StudentDao dao;
    @Autowired
    private Executor executor;
    @Autowired
    private StudentService service;

    @Async
    public void test(StudentDao dao, String time) {
        long l = System.currentTimeMillis();
        int num = getNum(dao, time);
        List<TestBean> testBeans = selectTestBean(dao, time);
        int insert = insert(dao, testBeans);
        if (num == insert) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }

    public List<TestBean> selectTestBean(StudentDao dao, String time) {
        return dao.selectTestBean(time);
    }

    public int getNum(StudentDao dao, String time) {
        return dao.getNum(time);
    }

    public int insert(StudentDao dao, List<TestBean> testBeans) {
        return dao.insertTestBean(testBeans);
    }

    @Async
    public void test1() {
        Map<String, String> map = new HashMap<>();
//        CompletableFuture.runAsync(()->{
//            map.put("name","张三");
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName());
//            System.out.println("Calss1:"+map.getClass());
//            Student student = dao.getStudent((String) map.get("name"));
//            System.out.println(student);
//        },executor);
//        CompletableFuture.runAsync(()->{
//            map.put("name","李四");
//            System.out.println(Thread.currentThread().getName());
//            System.out.println("Calss2:"+map.getClass());
//            Student student = dao.getStudent((String) map.get("name"));
//            System.out.println(student);
//        },executor);
        service.test2(map);
        service.test3(map);

    }
}
