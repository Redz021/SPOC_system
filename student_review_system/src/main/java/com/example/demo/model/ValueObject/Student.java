package com.example.demo.model.ValueObject;

import lombok.*;

/**
 * 学生类
 */
@Data
@ToString
public class Student {
    private String stuId;//学号
    private String stuName;//姓名
    private String profession;//专业（计算机）
    private String year_admission;//入学时间（17)


}
