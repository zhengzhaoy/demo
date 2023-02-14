package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my")
public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private int ranking;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ranking=" + ranking +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (o instanceof Student) {
            Student stu = (Student) o;
            if (this.age > stu.age) {
                return 1;
            } else if (this.age < stu.age) {
                return -1;
            } else {
                return 0;
            }
        }
        throw new RuntimeException("");
//        return this.age.compareTo(o.age);
    }


}
