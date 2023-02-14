package com.example.demo.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("myAdaptableJobFactory")
public class MyAdaptableJobFactory extends AdaptableJobFactory {
    /*
     *该方法需要将实例化的任务对象手动的添加到springIOC容器中并且完成对象的注入
     * AutowireCapableBeanFactory对象可以将一个对象添加到springIOC容器中，并完成该对象的注入*/
    @Resource
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);//调用父类的方法实例化任务类对象
        //将jobInstance对象添加到springIOC容器中，并完成注入
        beanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
