/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication.tableclasses;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Adegbulugbe
 */
public class TopicTableClass {
    private IntegerProperty topicId;
    private StringProperty topicName;
    
    public TopicTableClass(int topicId, String topicName) {
        this.topicId = new SimpleIntegerProperty(topicId);
        this.topicName = new SimpleStringProperty(topicName);
    }
    
    public void setTopicId(int tid) {
        this.topicId.set(tid);
    }
    
    public int getTopicId() {
        return this.topicId.get();
    }
    
    public IntegerProperty getTopicIdProperty() {
        return this.topicId;
    }
    
    public void setTopicName(String topicName) {
        this.topicName.set(topicName);
    }
    
    public String getTopicName() {
        return this.topicName.get();
    }
    
    public StringProperty getTopicNameProperty() {
        return this.topicName;
    }
}
