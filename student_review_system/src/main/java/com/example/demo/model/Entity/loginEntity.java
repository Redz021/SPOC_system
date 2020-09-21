package com.example.demo.model.Entity;

import lombok.Data;

@Data
public class loginEntity {
    private String errorInfo;//错误信息
    private String state;//状态
    private String authority;//用户权限
    private String name;//用户姓名
}
