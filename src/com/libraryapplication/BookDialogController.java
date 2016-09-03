/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Controller.BookController;
import Controller.TopicController;
import Utils.Item;
import com.libraryapplication.tableclasses.BookTableClass;
import com.libraryapplication.tableclasses.TopicTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Adegbulugbe
 */
public class BookDialogController {
    @FXML
    private TextField bookNameField;
    @FXML
    private ComboBox topicBox;
    
    public boolean okClicked;
    public Stage dialogStage;
    public BookTableClass bookTableClass;
    public int bookId = -1;
    private final BookController bookController = new BookController();
    
    public void setTopicItems() {
        TopicController topicController = new TopicController();
        
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
    
    public void setBookTableClass(BookTableClass bookTableClass) {
        setTopicItems();
        if (bookTableClass != null) {
            bookId = bookTableClass.getBookId();
            bookNameField.setText(bookTableClass.getBookName());
        }
    }
    
    @FXML
    private void handleSubmit() {
        if (inputIsValid()) {
            String bookName = bookNameField.getText();
            Item topicItem= (Item) topicBox.getSelectionModel().getSelectedItem();
            int topicId = topicItem.getID();
            if (bookId != -1) {
                
                if (bookController.updateBook(bookName, topicId, bookId) == 1) {
                    okClicked = true;
                    dialogStage.close();
                }
                else {
                    setError("Unable to update book");
                }
            }
            else {
                if (bookController.addBook(topicId, bookName) == 1) {
                    okClicked = true;
                    bookNameField.setText("");
                }
                else {
                    setError("Unable to create book");
                }
            }
        }
    }
    
    @FXML
    private void handleExit() {
        dialogStage.close();
    }
    
    private boolean inputIsValid() {
        String errorString = "";
        if (bookNameField.getText().equals("") || 
                bookNameField.getText().length() == 0) {
            errorString += "\nPlease set the book name field";
        }
        
        if (!errorString.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please Correct Invalid fields");
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
            alert.setHeaderText("Book Error");
            alert.setContentText(errorString);
            
            alert.showAndWait();
    }
}
