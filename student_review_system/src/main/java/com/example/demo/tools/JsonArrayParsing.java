package com.example.demo.tools;

import java.util.LinkedList;
import java.util.List;

//解析从数据库获取的json
public class JsonArrayParsing {
    public static List<String> parsing(String target)
    {
        String tmp = target.substring(1,target.length()-1);//将两端的"[]"去除
        String []split = tmp.split(",");//以","分割字符串
        List<String> result = new LinkedList<>();
        for(int i=0;i<split.length;i++)
        {
            split[i] = split[i].trim();
            result.add(split[i]);
        }
        return result;
    }

}
