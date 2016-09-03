/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Controller.BookController;
import Controller.TopicController;
import Controller.UserBookController;
import Utils.Item;
import com.libraryapplication.tableclasses.BookTableClass;
import com.libraryapplication.tableclasses.TopicTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 *
 * @author Adegbulugbe
 */
public class BorrowBookController {
    @FXML
    private ComboBox topicBox;
    @FXML
    private ComboBox bookBox;
    
    public int userId;
    public Stage dialogStage;
    public boolean okClicked;
    private final TopicController topicController = new TopicController();
    private final BookController bookController = new BookController();
    
    private void setTopicItems() {
        ObservableList<Item> topicItems = FXCollections.observableArrayList();
        ObservableList<TopicTableClass> topicTableItems = FXCollections.observableArrayList();
        
        topicTableItems = topicController.getTopicList();
        for (TopicTableClass topicTableClass : topicTableItems) {
            int topicId = topicTableClass.getTopicId();
            String topicName = topicTableClass.getTopicName();
            
            Item item = new Item(topicId, topicName);
            topicItems.add(item);
        }
        
        topicBox.setItems(topicItems);
    }
    
    private void setBookItems(int topicId) {
        ObservableList<Item> bookItems = FXCollections.observableArrayList();
        ObservableList<BookTableClass> bookTableItems = FXCollections.observableArrayList();
        
        bookTableItems = bookController.getBookByTopicList(topicId);
        for (BookTableClass bookTableClass : bookTableItems) {
            int bookId = bookTableClass.getBookId();
            String bookName = bookTableClass.getBookName();
            
            Item item = new Item(bookId, bookName);
            bookItems.add(item);
        }
        
        bookBox.setItems(bookItems);
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
        setTopicItems();
        topicBox.getSelectionModel().selectedItemProperty().addListener(
                (Observable, oldValue, newValue) -> {
                    Item item = (Item) topicBox.getSelectionModel().getSelectedItem();
                    int topicId = item.getID();
                    setBookItems(topicId);
                }
        );
    }
    
    @FXML
    private void handleSubmit() {
        if (inputIsValid()) {
            Item bookItem = (Item) bookBox.getSelectionModel().getSelectedItem();
            
            int bookId = bookItem.getID();
            
            UserBookController controller = new UserBookController();
            int result = controller.addUserBook(userId, bookId);
            if (result == 1) {
                okClicked = true;
            }
            else {
                setError("Could not borrow book");
            }
        }
    }
    
    private boolean inputIsValid() {
        String errorString = "";
        if (bookBox.getValue() == null) {
            errorString += "Select a book/n";
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Fields");
            alert.setContentText(errorString);
            alert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }
    
    private void setError(String errorString) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Error");
            alert.setHeaderText("User Borrowing Book Error");
            alert.setContentText(errorString);
            
            alert.showAndWait();
    }
}
