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
public class UserTableClass {
    private IntegerProperty userId;
    private StringProperty name;
    private StringProperty username;
    private StringProperty topic;
    
    public UserTableClass(int userId, String name, String username, String topic) {
        this.userId = new SimpleIntegerProperty(userId);
        this.name = new SimpleStringProperty(name);
        this.username = new SimpleStringProperty(username);
        this.topic = new SimpleStringProperty(topic);
    }
    
    public void setUserId(int userId) {
        this.userId.set(userId);
    }
    
    public int getUserId() {
        return this.userId.get();
    }
    
    public IntegerProperty getUserIdProperty() {
        return this.userId;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty getNameProperty() {
        return this.name;
    }
    
    public void setUsername(String name) {
        this.username.set(name);
    }
    
    public String getUsername() {
        return this.username.get();
    }
    
    public StringProperty getUsernameProperty() {
        return this.username;
    }
    
    public void setTopic(String topic) {
        this.topic.set(topic);
    }
    
    public String getTopic() {
        return this.topic.get();
    }
    
    public StringProperty getTopicProperty() {
        return this.topic;
    }
}
