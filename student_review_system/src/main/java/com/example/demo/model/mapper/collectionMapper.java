package com.example.demo.model.mapper;

import com.example.demo.model.ValueObject.collections;
import com.example.demo.model.ValueObject.testQuestion;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface collectionMapper {
    //查询某课程下的所有收藏的试题号
    List<collections> getCourseCollection(@Param("Cno") String Cno,@Param("stuId") String stuId);
    //查询所有收藏的试题号
    List<collections> getAllCollections(String stuId);
    //根据题号获取试题详细信息
    List<testQuestion> getTestQuestions(List<String> TqNos);
    //收藏一道试题
    void collectQuestion(collections collections);
    //删除一道收藏的题
    void deleteCollection(@Param("TqNo") String TqNo,@Param("stuId") String stuId);
}
