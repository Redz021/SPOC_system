package com.example.demo.model.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface testDeleteMapper {
    //删除选择题的所有选项
    void deleteAllOptions(String TqNo);
    //删除试题
    void deleteQuestion(String TqNo);
    //删除试卷
    void deletePaper(String TqNo);

}
