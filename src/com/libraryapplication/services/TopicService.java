/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication.services;

import Database.TopicQueries;
import Models.Topic;
import com.libraryapplication.tableclasses.TopicTableClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adegbulugbe
 */
public class TopicService {
    private TopicQueries topicQuery;
    
    public TopicService() {
        topicQuery = new TopicQueries();
    }
    
    public int addTopic(String topicName) {
        return topicQuery.insertTopic(topicName);
    }
    
    public int deleteTopic(int tid) {
        return topicQuery.deleteTopic(tid);
    }
    
    public List<TopicTableClass> getTopicTableClass() {
        List<Topic> topicList = new ArrayList<>();
        List<TopicTableClass> topicTableList = null;
        
        topicList = topicQuery.getAllTopic();
        
        if (!topicList.isEmpty()) {
            topicTableList = new ArrayList<>();
            for (Topic topic : topicList) {
                int topicId = topic.topicId;
                String topicName = topic.topicName;
                
                topicTableList.add(new TopicTableClass(
                    topicId, topicName));
            }
        }
        
        return topicTableList;
    }
    
    public Topic getTopic(int tid) {
        return topicQuery.getTopic(tid).get(0);
    }
}
