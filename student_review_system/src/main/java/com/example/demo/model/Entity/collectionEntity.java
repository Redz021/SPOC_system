package com.example.demo.model.Entity;

import com.example.demo.model.ValueObject.collections;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class collectionEntity {
    private List<collections> collections = new LinkedList<>();
    private String state;
}
