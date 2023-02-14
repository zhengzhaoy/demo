package com.example.demo.config;


import com.example.demo.service.StudentService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@DisallowConcurrentExecution
public class QuartzTest implements Job {
    @Autowired
    private StudentService service;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始执行定时任务");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate time = LocalDate.now();
        LocalDate localDate = time.minusDays(3);
        String tjrq_s = formatter.format(localDate);
        String tjrq_e = formatter.format(localDate);
        service.test(tjrq_s, tjrq_e);
        System.out.println("定时任务执行结束");
//        CloseableHttpClient client = HttpClients.createDefault();
//        String url = "http://localhost:8080/test1";
//        HttpGet httpGet = new HttpGet(url);
//        CloseableHttpResponse execute= null;
//        try {
//            execute = client.execute(httpGet);
//        } catch (IOException e) {
//
//        }finally {
//            if (client != null){
//                try {
//                    client.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (execute != null){
//                try {
//                    execute.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
