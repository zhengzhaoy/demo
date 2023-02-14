package com.example.demo.util;

public class ErrorUtil {

    public static StringBuffer getExceptionMsg(Exception e) {
        //记录报错内容
        StackTraceElement[] elems = e.getStackTrace();
        String text = "\n";
        for (StackTraceElement elem : elems) {
            text += "\t" + elem.toString() + "\n";
        }
        String exTitle = e.toString();
        StringBuffer msg = new StringBuffer();
        msg.append("异常类型【");
        msg.append(exTitle);
        msg.append("】 \n\t >> ------------------ 异常详情 -----------------<< \n");
        msg.append(text);
        return msg;
    }
}
