package com.example.demo.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MyRunner implements ApplicationRunner, CommandLineRunner, Ordered {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("_____________启动时进行的操作");
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public int getOrder() {
        //数值越小越靠前
        return 0;
    }
}
