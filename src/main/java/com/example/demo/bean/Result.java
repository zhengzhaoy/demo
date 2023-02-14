package com.example.demo.bean;

import java.util.List;

public class Result {
    private List<Student> students;
    private List<Course> courses;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Result{" +
                "students=" + students +
                ", courses=" + courses +
                '}';
    }
}
