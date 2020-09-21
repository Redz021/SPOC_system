package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.comment;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class commentEntity {
    private List<comment> commentList= new LinkedList<>();
    private String state;
}
