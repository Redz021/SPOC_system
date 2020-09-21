package com.example.demo.tools;


import java.text.SimpleDateFormat;
import java.util.Date;

//获取当前系统时间
public class getCurrentTime {
    public static String getTime()
    {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        return dateFormat.format( now ); //转换成String类型
    }
}
