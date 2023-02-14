//package com.example.demo.config;
//
//import com.example.demo.bean.Student;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StudentConfig implements BeanPostProcessor {
//    //postProcessAfterInitialization方法在Bean初始化之后进行的操作
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        if (bean instanceof Student){
//            ((Student) bean).setName("张三");
//            ((Student) bean).setAge(20);
//            ((Student) bean).setRanking(1);
//        }
////        System.out.println("实例化的bean:"+bean.toString());
//        return bean;
//    }
//    //postProcessBeforeInitialization方法在Bean初始化之前进行的操作
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
////        System.out.println("实例化bean之前:"+bean.toString());
//        return bean;
//    }
//}
