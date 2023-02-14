package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {
    //Quartz配置类
    /*
     * 1.创建job对象*/
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //关联我们的job类(要执行的任务的类)
//        JobDataMap jobDataMap=new JobDataMap();
//        jobDataMap.put("jobDate","jo");
        jobDetailFactoryBean.setJobClass(QuartzTest.class);
        return jobDetailFactoryBean;
    }

    /*
     * 2.创建Trigger对象*/
    @Bean
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        //关联job对象
        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        //配置任务的执行时间,setRepeatInterval(2000)该参数表示一个执行的毫秒数
        simpleTriggerFactoryBean.setRepeatInterval(2000);
        simpleTriggerFactoryBean.setRepeatCount(1);//设置重复的次数
        return simpleTriggerFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        //关联job对象
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        cronTriggerFactoryBean.setCronExpression("1 23 11 * * ?");
        return cronTriggerFactoryBean;
    }

    /*
     * 3.创建Scheduler对象*/
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean, MyAdaptableJobFactory myAdaptableJobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //关联Trigger对象
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
//        schedulerFactoryBean.start();//启动
//        schedulerFactoryBean.stop();//停止
        /*setJobFactory方法允许创建一个新的job工厂*/
        schedulerFactoryBean.setJobFactory(myAdaptableJobFactory);
        return schedulerFactoryBean;
    }
}
