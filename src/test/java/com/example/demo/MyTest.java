package com.example.demo;

import com.example.demo.bean.Student;
import com.example.demo.bean.Teypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.yaml.snakeyaml.util.ArrayUtils;
import sun.security.util.ArrayUtil;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MyTest {
    @Test
    public void test1() {
        float f = 1.23f;
        float f1 = 0.00f;
        BigDecimal bigDecimal1 = new BigDecimal("1.63");
        BigDecimal bigDecimal2 = new BigDecimal("1.0");
        BigDecimal bigDecimal3 = new BigDecimal("0");
        System.out.println(bigDecimal1);
        String str = "1.23";
        System.out.println(Float.parseFloat(str) + Float.parseFloat(str));
        BigDecimal subtract = bigDecimal1.subtract(bigDecimal2);
        System.out.println(subtract);
        BigDecimal divide = bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_DOWN));
        System.out.println(bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_HALF_DOWN));
        System.out.println(bigDecimal1.divide(bigDecimal2, 0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(divide.floatValue() * 100);
        System.out.println(f + f);
        System.out.println(bigDecimal1.add(bigDecimal2).floatValue());
        System.out.println(bigDecimal3.floatValue());
        if (f1 == 0) {
            System.out.println("为零");
        } else {
            System.out.println("不为零");
        }
        if (f != 0) {
            System.out.println("不为零");
        } else {
            System.out.println("为零");
        }
    }

    @Test
    public void test2() {
        StringBuilder s = new StringBuilder("K00012021003205");
        s.delete(0, 5);
        s.insert(0, "K0002");
        String str = "320500000000";
        System.out.println(s);
        System.out.println(str.substring(0, 6));
    }

//    @Test
//    public void test3() {
//        List<Student> list = new ArrayList<>();
//        Student s1 = new Student();
//        s1.setName("zs");
//        s1.setAge(10);
//        list.add(s1);
//        Student s2 = new Student();
//        s2.setName("ls");
//        s2.setAge(10);
//        list.add(s2);
//        for (Student student : list) {
//            System.out.println(student);
//        }
//    }

    @Test
    public void test4() {
        LocalDate localDate1 = LocalDate.now();
//        LocalDate localDate1 = localDate.minusMonths(1);
//        System.out.println(localDate1);
//        DateTimeFormatter ym = DateTimeFormatter.ofPattern("yyyyMM");
//        String format = ym.format(localDate1);
////        System.out.println(format);
        String str = "abcd";
        String s = "a";
        System.out.println(str.contains(s));
    }

    @Test
    public void test5() {
        long start = System.currentTimeMillis();
        int[] arr = new int[]{12, 14, 9, 6, 13, 16, 90, 88, 32};
        sort(arr, 0, arr.length - 1);
        test6(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(Arrays.toString(arr));
    }

    public void test6(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int b = 0; b < arr.length - 1 - i; b++) {
                if (arr[b] < arr[b + 1]) {
                    int c = arr[b];
                    arr[b] = arr[b + 1];
                    arr[b + 1] = c;
                }
            }
        }
    }

    public void sort(int[] arr, int star, int end) {
        //当起始位大于等于结束位时结束(控制递归调用)
        if (star >= end) {
            return;
        }
        //先定义一个定义基准，一般是数组的起始位
        int base = arr[star];
        //定义低位指针
        int left = star;
        //定义高位指针
        int right = end;
        while (left < right) {
            //从右边向左找，找比基准位小的(也就是说只要指针指向的值比基准位的大，指针就向左移动)
            while (base <= arr[right] && left < right) {
                right--;
            }

            //从左边向右找，找比基准位大的(也就是说只要指针指向的值比基准位的小，指针就向右移动)
            while (base >= arr[left] && left < right) {
                left++;
            }
            if (left < right) {
                int tep1 = arr[left];
                arr[left] = arr[right];
                arr[right] = tep1;
            } else if (left == right) {
                //指针相遇时交换基准值和指针值
                arr[star] = arr[right];
                arr[right] = base;
            }
        }
//        递归调用处理基准值左边的值
        sort(arr, star, right - 1);
        //递归调用处理基准值右边的值
        sort(arr, right + 1, end);
    }

    @Test
    public void test8() {
        String str = "a";
        String s = "b";
        if (s.compareTo(str) != 1) {
            System.out.println(str.compareTo(s));
        }
    }

    @Test
    public void test9() {
        deleteFile("D:\\test");
    }

    public void deleteFile(String path) {
//        File file = new File("src\\main\\resources\\static");
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i <= files.length - 1; i++) {
            if (files[i].isDirectory()) {
//                File[] listFiles = files[i].listFiles();
                deleteFile(files[i].getPath());
                System.out.println(files[i].getPath());
            } else {
                long l = files[i].lastModified();
                Date date = new Date();
                long time = date.getTime();
                if (time - l > 180000) {
                    files[i].delete();
                }
            }
        }
    }

    @Test
    public void test10() {
//        float f = 1.3f;
        float f = 1.7f;
        int i = 0;
        i = (int) f;
//        i = Integer.valueOf((int) f);
        System.out.println(i);
    }

    @Test
    public void test11() {
        Random random = new Random();
        int i = random.nextInt(5000);
        System.out.println(Integer.toString(i));
    }

    @Test
    public void test12() {
        Teypes spring = Teypes.SPRING;
        Teypes[] values = Teypes.values();
//        for (Teypes teype : values){
//            System.out.println(teype);
//        }
        Teypes value = Teypes.values()[0];
        System.out.println(value);
    }

    //    @Test
    public void test13() {
        int i = 0;
        int a = 10;
        try {
            int c = a / i;
        } catch (Exception e) {
            e.printStackTrace();
//            StackTraceElement stackTraceElement = e.getStackTrace()[0];
//            System.out.println(stackTraceElement.getMethodName());
            StackTraceElement stackTraceElement1 = Thread.currentThread().getStackTrace()[3];
            String methodName = stackTraceElement1.getMethodName();
            System.out.println(methodName);
        } finally {
            System.out.println("hello");
//        }
        }
    }

    @Test
    public void test() {
        test13();
    }

    @Test
    public void test19() {
        test();
    }

    @Test
    public void test14() {
        String time1 = "20221212";
        String time2 = "20221220";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate tjrq_s = LocalDate.parse(time1, formatter);
        LocalDate tjrq_e = LocalDate.parse(time2, formatter);
        int day = tjrq_e.getDayOfMonth() - tjrq_s.getDayOfMonth();
//        for (int i=0;i<=day;i++){
//            System.out.println(tjrq_s);
//            tjrq_s = tjrq_s.plusDays(1);
//        }
        while (true) {
            System.out.println(tjrq_s);
            tjrq_s = tjrq_s.plusDays(1);
            if (tjrq_s.isAfter(tjrq_e)) {
                break;
            }
        }
    }

    @Test
    public void test15() {
        ArrayList<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("zs");
        student.setAge(10);
        student.setRanking(1);
        list.add(student);
        list.add(student);
        list.add(student);
        System.out.println(list.toString());
    }

    @Test
    public void test16() {
        List<Map> dataAjbh = new ArrayList<>();
        Map map = new HashMap();
        map.put("ajbh", "1001");
        dataAjbh.add(map);
        map.put("ajbh", "1002");
        dataAjbh.add(map);
        map.put("ajbh", "1003");
        dataAjbh.add(map);
        int size = dataAjbh.size();
        int index = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Map mapAjbh : dataAjbh) {
            if (index == 1) {
                stringBuilder.append("in(");
            }
            stringBuilder.append("'");
            stringBuilder.append(mapAjbh.get("ajbh"));
            stringBuilder.append("'");
            if (index != size) {
                stringBuilder.append(",");
            }
            if (index == size) {
                stringBuilder.append(")");
            }
            index++;
        }
        System.out.println(stringBuilder);
    }

    @Test
    public void test17() {
        Map map = new HashMap();
        map.put("ajbh", 1001);
        map.put("name", "zs");
        Map map1 = new HashMap();
        map1.put("ajbh", "1001");
        map.putAll(map1);
        String ajbh = (String) map.get("ajbh");
        System.out.println(ajbh);
        System.out.println(map);
    }

    @Test
    public void test18() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate time = LocalDate.now();
        LocalDate localDate = time.minusDays(2);
        System.out.println(formatter.format(localDate) + "000000");
        System.out.println(formatter.format(localDate) + "235959");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        String tjrq_s = format.format(calendar.getTime()) + "000000";
        String tjrq_e = format.format(calendar.getTime()) + "235959";
        System.out.println(tjrq_s);
        System.out.println(tjrq_e);
    }

    @Test
    public void test20() {
        HashSet set = new HashSet<>();

        Map map1 = new HashMap<>();
        map1.put("tjrq", "1");
        map1.put("methodName", "2");
        map1.put("identifie", "3");
        set.add(map1);
        Map map2 = new HashMap<>();
        map2.put("tjrq", 2);
        map2.put("methodName", "2");
        map2.put("identifie", "3");
        set.add(map2);
        Map map3 = new HashMap<>();
        map3.put("tjrq", "1");
        map3.put("methodName", "2");
        map3.put("identifie", "3");
        set.add(map3);
        Object aa = map2.get("aa");
//        for (int i=0;i<3;i++){
//            Map map1 = new HashMap<>();
//            map1.put("tjrq","1");
//            map1.put("methodName","2");
//            map1.put("identifie","3");
//            set.add(map1);
////            Student student = new Student();
////            student.setName("zs");
////            student.setAge(1);
////            student.setRanking(1);
////            set.add(student);
//        }
        System.out.println(set);
        System.out.println(set.size());
    }
}
