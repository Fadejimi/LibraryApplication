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
public class UserBookTableClass {
    private IntegerProperty userBookId;
    private StringProperty bookName;
    private StringProperty topicName;
    
    
    public UserBookTableClass(int userBookId, String topicName, String bookName) {
        this.userBookId = new SimpleIntegerProperty(userBookId);
        this.topicName = new SimpleStringProperty(topicName);
        this.bookName = new SimpleStringProperty(bookName);
    }
    
    public void setUserBookId(int id) {
        this.userBookId.set(id);
    }
    
    public int getUserBookId() {
        return this.userBookId.get();
    }
    
    public IntegerProperty getUserBookIdProperty() {
        return this.userBookId;
    }
    
    public void setTopicName(String topic) {
        this.topicName.set(topic);
    }
    
    public String getTopicName() {
        return this.topicName.get();
    }
    
    public StringProperty getTopicNameProperty() {
        return this.topicName;
    }
    
    public void setBookName(String bookName) {
        this.bookName.set(bookName);
    }
    
    public String getBookName() {
        return this.bookName.get();
    }
    
    public StringProperty getBookNameProperty() {
        return this.bookName;
    }
}
