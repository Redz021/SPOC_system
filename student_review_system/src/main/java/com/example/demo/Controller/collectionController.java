package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.dao.collectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class collectionController {
    private final static String selCollectionUrl = "/course/courseCollections";//查询课程下所有收藏的题目
    private final static String selAllCollectionsUrl = "/course/AllCourseCollections";//查询所有收藏的题目
    private final static String addNewCollectionUrl = "/course/addNewCollection";//收藏一个题目(不会重复插入)
    private final static String deleteCollectionUrl = "/course/deleteCollection";//删除一个收藏

    @Autowired
    private collectionDao collectionDao;

    @RequestMapping(value=selCollectionUrl,produces = "application/json; charset=utf-8")
    public String selCollection(@RequestParam String Cno,@RequestParam String stuId)
    {

        return JSON.toJSONString(collectionDao.getTestQuestionsByTqNos(collectionDao.GetCourseCollectionsList(Cno, stuId)));
    }

    @RequestMapping(value=selAllCollectionsUrl,produces = "application/json; charset=utf-8")
    public String selAllCollections(@RequestParam String stuId)
    {
        return JSON.toJSONString(collectionDao.getTestQuestionsByTqNos(collectionDao.GetAllCollections(stuId)));
    }

    @RequestMapping(value=addNewCollectionUrl,produces = "application/json; charset=utf-8")
    public String AddNewCollection(@RequestParam String Cno,@RequestParam String TqNo,@RequestParam String stuId)
    {
        collectionDao.collectQuestion(Cno, TqNo, stuId);
        return "{\"state\":\"success\"}";
    }
    @RequestMapping(value=deleteCollectionUrl,produces = "application/json; charset=utf-8")
    public String deleteCollection(@RequestParam String TqNo,@RequestParam String stuId)
    {
        collectionDao.deleteCollection(TqNo, stuId);
        return "{\"state\":\"success\"}";
    }
}
