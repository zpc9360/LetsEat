package com.letseat.controller;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//
//import java.util.HashSet;
//import java.util.Set;
//
//public class test {
//
//
//    public static void main(String[] args) {
//        int userType = 34;
//        String age = "36";
//        Set<String> set = new HashSet<String>();
//        set.add("'1'");
//        String type = Integer.toString(userType);
//        // 若为外省（2）推送4、异网（3）推送3、34岁无身份青年推送5、重点客户（4）推送6、既是重点用户又是异网（36）推送3、6
//        for (int i = 0; i < type.length(); i++) {
//            char ch = type.charAt(i);
//            if (ch == '2') {
//                set.add("'4'");
//            } else if (ch == '3') {
//                set.add("'3'");
//            } else if (ch == '4') {
//                set.add("'6'");
//            }
//        }
//        if ("34".equals(age)) {
//            set.add("'5'");
//        }
//        System.out.println(set.toString());
//    }
//
//}
public class test {
    static int value = 33;

    public static void main(String[] args) throws Exception {
        System.out.println(new test().isOtherNet("11"));

    }

    public String isOtherNet(String bill_no) {
        SimpleDateFormat simpleDateFormats = new SimpleDateFormat("yyyyMMdd");
        Boolean b = false;
        Date d = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, -1);
        Date y = calendar.getTime();
        String table = "ods.DW_COMP_CUST_DT_" + simpleDateFormats.format(y);

        return table;
    }
}
