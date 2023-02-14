package com.example.demo.bean;


public class Course {
    private int id;
    private String cou;

    public int getId() {
        return id;
    }

    public String getCou() {
        return cou;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCou(String cou) {
        this.cou = cou;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cou='" + cou + '\'' +
                '}';
    }
}
