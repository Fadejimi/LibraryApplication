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
public class BookTableClass {
    private IntegerProperty bookId;
    private StringProperty bookName;
    private StringProperty topicName;
    
    
    public BookTableClass(int bookId, String topicName, String bookName) {
        this.bookId = new SimpleIntegerProperty(bookId);
        this.topicName = new SimpleStringProperty(topicName);
        this.bookName = new SimpleStringProperty(bookName);
    }
    
    public void setBookId(int bookId) {
        this.bookId.set(bookId);
    }
    
    public int getBookId() {
        return this.bookId.get();
    }
    
    public IntegerProperty getBookIdProperty() {
        return this.bookId;
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
