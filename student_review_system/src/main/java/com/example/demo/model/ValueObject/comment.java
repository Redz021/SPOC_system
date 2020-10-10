package com.example.demo.model.ValueObject;

import lombok.Data;

@Data
public class comment {
    private String TqNo;//试题号
    private String comment;//发言
    private String userId;//发言人的id
    private String stuName;//发言人姓名
    private String time;//发言时间
}
