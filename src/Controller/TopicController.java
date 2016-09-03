/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Models.Topic;
import com.libraryapplication.services.TopicService;
import com.libraryapplication.tableclasses.BookTableClass;
import com.libraryapplication.tableclasses.TopicTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adegbulugbe
 */
public class TopicController {
    private final TopicService topicService = new TopicService();
    
    private ObservableList<TopicTableClass> topicList = 
            FXCollections.observableArrayList();
    
    
    public int addTopic(String topicName) {
        return topicService.addTopic(topicName);
    }
    
    public Topic getTopic(int topicId) {
        return topicService.getTopic(topicId);
    }
    
    public ObservableList<TopicTableClass> getTopicList() {
        if (topicList != null) {
            topicList.clear();
        }
        topicList = FXCollections.observableArrayList(topicService.getTopicTableClass());
        return topicList;
    }
    
    public int deleteTopic(int tid) {
        return topicService.deleteTopic(tid);
    }
    
}
