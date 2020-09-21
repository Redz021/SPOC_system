package com.example.demo.dao;

import com.example.demo.model.Entity.AllTestQuestionsEntity;
import com.example.demo.model.Entity.collectionEntity;
import com.example.demo.model.ValueObject.collections;
import com.example.demo.model.ValueObject.testQuestion;
import com.example.demo.model.ValueObject.testQuestionOptions;
import com.example.demo.model.service.collectionService;
import com.example.demo.model.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class collectionDao {
    @Autowired
    private collectionService collectionService;
    @Autowired
    private com.example.demo.model.service.courseService courseService;
    //获取课程下的所有收藏的试题号
    public List<collections> GetCourseCollectionsList(String Cno, String stuId)
    {
        return collectionService.getCourseCollection(Cno, stuId);
    }
    //获取所有收藏的试题号
    public List<collections> GetAllCollections(String stuId)
    {
        return collectionService.getAllCollections(stuId);
    }
    //根据TqNO来获取试题详细信息
    public AllTestQuestionsEntity getTestQuestionsByTqNos(List<collections> collections)
    {
        AllTestQuestionsEntity allTestQuestionsEntity=  new AllTestQuestionsEntity();
        List<String>TqNos = new LinkedList<>();
        for(collections c: collections)
        {
            TqNos.add(c.getTqNo());
        }
        allTestQuestionsEntity.setTestQuestions(collectionService.getTestQuestions(TqNos));//除了选项都获取了
        List<testQuestionOptions> options = courseService.TestQuestionsOptions(TqNos);//获取所有选项

        //无选择题
        if(options==null)
        {
            return allTestQuestionsEntity;
        }
        //有选择题
        else{
            //在所有试题中寻找
            for(testQuestion t:allTestQuestionsEntity.getTestQuestions())
            {
                //若为选择题
                if(t.getType().equals("SingleSelection")||t.getType().equals("MultipleSelection")) {
                    for (testQuestionOptions s : options) {
                        //如果选项与题目对应
                        if (String.valueOf(t.getTqNo()).equals(s.getTqNo())) {

                            t.getOptions().add(s);
                        }
                    }

                }
            }

        }
        allTestQuestionsEntity.setState("success");
        return allTestQuestionsEntity;
    }
    //收藏一道试题
    public void collectQuestion(String Cno,String TqNo,String stuId)
    {
        collections collections = new collections();
        collections.setTqNo(TqNo);
        collections.setStuId(stuId);
        collections.setCno(Cno);
        collectionService.collectQuestion(collections);
    }
    //删除一个收藏
    public void deleteCollection(String TqNo,String stuId)
    {
        collectionService.deleteCollection(TqNo, stuId);
    }
}
