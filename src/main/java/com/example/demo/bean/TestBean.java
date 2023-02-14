package com.example.demo.bean;

import java.util.Date;

public class TestBean {
    private int id;
    private String name;
    private Date time;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                '}';
    }
}
