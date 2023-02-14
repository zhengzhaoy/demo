package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.dao.StudentDao;
import com.example.demo.service.StudentService;
import com.example.demo.service.impl.ThreadService;
import com.example.demo.util.ErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

@RestController
public class TestController {
    //    static Result result = new Result();
    @Autowired
    private Person person;
    @Autowired
    private Student stu;
    @Autowired
    private RedisTemplate redisTemplate;
    //    @Autowired
//    private ThreadPoolTaskExecutor poolTaskExecutor;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentDao dao;
    @Autowired
    private ThreadService threadService;
    @Autowired
    private Executor executor;

    @RequestMapping("/test")
    public String test() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello!";
    }

    @RequestMapping("/testRedis")
    public String testRedis() {
        redisTemplate.opsForValue().set("name", "zhangsan");
        return (String) redisTemplate.opsForValue().get("name");
    }

    @RequestMapping("/gettest")
    public String gettest() {
        String uid = Integer.toString(new Random().nextInt(5000));
        doSecKill(uid, "1001");
        return "null";
    }

    //秒杀过程
    public void doSecKill(String uid, String pid) {
        //1.判空
        if (uid == null || pid == null) {
            return;
        }
        String spkcKey = "kc" + pid;
        String userKey = "us" + pid;
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.watch(spkcKey);
        Integer kc = (Integer) redisTemplate.opsForValue().get(spkcKey);
        if (kc == null) {
            System.out.println("秒杀还没有开始");
            return;
        }
        Boolean member = redisTemplate.opsForSet().isMember(userKey, uid);
        if (member) {
            System.out.println("你已经参与过了");
            return;
        }
        if (kc <= 0) {
            System.out.println("秒杀已结束");
            return;
        }
        //使用事务
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch(spkcKey);
                operations.multi();
                operations.opsForValue().decrement(spkcKey);
                operations.opsForSet().add(userKey, uid);
                return operations.exec();
            }
        });
        System.out.println("用户" + uid + "秒杀成功了");
    }

    @RequestMapping("/getStudent")
    public Result getStudent() {
        Result result = new Result();
        List<Course> courseAll = studentService.getCourseAll();
        List<Student> studentAll = studentService.getStudentAll();
        result.setCourses(courseAll);
        result.setStudents(studentAll);
        return result;
    }

    @RequestMapping("/getStudents")
    public Result getStudents() throws ExecutionException, InterruptedException {
        Result result = new Result();
        //异步执行
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            List<Course> courseAll = studentService.getCourseAll();
            result.setCourses(courseAll);
        }, executor);
//        List<Course> courseAll = studentService.getCourseAll();
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture.runAsync(() -> {
            List<Student> studentAll = studentService.getStudentAll();
            result.setStudents(studentAll);
        }, executor);
        CompletableFuture.allOf(voidCompletableFuture, voidCompletableFuture1).get();
        return result;
    }

    @RequestMapping("/getStuBean")
    public Student getStuBean() {
        return stu;
    }

    @RequestMapping("/getOneStudent")
    public Student getOneStudent(String str) {
        return studentService.getStudent(str);
    }

    @RequestMapping("/getPerson")
    public Person getPerson() {
        return person;
    }

    @RequestMapping("/insert")
    public String insert(String time1, String time2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate tjrq_s = LocalDate.parse(time1, formatter);
        LocalDate tjrq_e = LocalDate.parse(time2, formatter);
        while (true) {
            threadService.test(dao, formatter.format(tjrq_s));
            tjrq_s = tjrq_s.plusDays(1);
            if (tjrq_s.isAfter(tjrq_e)) {
                break;
            }
        }
        return "插入成功";
    }

    @RequestMapping("/test1")
    public String test1() {
        HashMap map = new HashMap();
        map.put("name", "ls");
        map.put("age", 22);
        map.put("ranking", 6);
//        Map map1 = studentService.insertData(map, "student");
//        System.out.println(map1.toString());
        int student = studentService.insertDatas(map, "student");
        System.out.println(student);
        System.out.println("hello");
        return "插入成功";
    }

    @RequestMapping("/test2")
    public String test2() {
        HashMap map = new HashMap();
        map.put("name", "aaaaaaaaaaaaaaaaaaaa");
        map.put("age", 22);
        map.put("ranking", 6);
        int student = -1;
        try {
            student = studentService.insertData1(map, "student", "name");
        } catch (Exception e) {
            studentService.insertErrorData("student", e.toString());
        }
        System.out.println(student);
        return "插入成功";
    }

    @RequestMapping("/test3")
    public String test3(String str) {
        List<Student> students = studentService.getStudents(str);
        Student student = studentService.getStudent(str);
        if (students != null) {
            System.out.println(students.toString());
            System.out.println(students.size());
        }
        if (students.size() == 0) {
            System.out.println("students集合没有元素");
        }
        if (student != null) {
            System.out.println("hello");
        }
        return "查询成功";
    }

    @RequestMapping("/test4")
    public String test4(String str) {
        return studentService.selectError(str);
    }

    @RequestMapping("/test5")
    public String test5() {
        List<JqBean> list = new ArrayList<>();
        studentService.insertJq(list);
        return "查询成功";
    }

    @RequestMapping("/test6")
    public String test6() {
        threadService.test1();
        System.out.println("查询成功");
        System.out.println("hello");
        System.out.println("查询成功");
        return "查询成功";
    }
}
