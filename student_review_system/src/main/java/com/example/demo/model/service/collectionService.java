package com.example.demo.model.service;

import com.example.demo.model.ValueObject.collections;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.mapper.collectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class collectionService {
    @Autowired
    private collectionMapper collectionMapper;
    //查询某课程下的所有收藏的试题号
    public List<collections> getCourseCollection(String Cno, String stuId)
    {
        return collectionMapper.getCourseCollection(Cno, stuId);
    }
    //查询所有收藏的试题号
    public List<collections> getAllCollections(String stuId)
    {
        return collectionMapper.getAllCollections(stuId);
    }
    //根据题号获取试题详细信息
    public List<testQuestion> getTestQuestions(List<String> TqNos)
    {
        return collectionMapper.getTestQuestions(TqNos);
    }
    //收藏一道试题
    public void collectQuestion(collections collections)
    {
        collectionMapper.collectQuestion(collections);
    }
    //删除一道收藏的题
    public void deleteCollection(String TqNo,String stuId)
    {
        collectionMapper.deleteCollection(TqNo, stuId);
    }
}
