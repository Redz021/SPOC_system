package com.example.demo.model.ValueObject;

import lombok.Data;

@Data
public class TestPaperDoneDetails {
    private String Dno;
    private String answer;//作答答案
    private String TqNo;//试题号
    private String ifwrong;//是否错误
}
