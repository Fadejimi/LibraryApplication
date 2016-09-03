/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Controller.BookController;
import Controller.TopicController;
import Controller.UserController;
import com.libraryapplication.tableclasses.BookTableClass;
import com.libraryapplication.tableclasses.TopicTableClass;
import com.libraryapplication.tableclasses.UserTableClass;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Adegbulugbe
 */
public class AdminController {
    
    @FXML
    private TableView<UserTableClass> userTable;
    @FXML
    private TableColumn<UserTableClass, Integer> userIdColumn;
    @FXML
    private TableColumn<UserTableClass, String> userNameColumn;
    @FXML
    private TableColumn<UserTableClass, String> usernameColumn;
    @FXML 
    private TableColumn<UserTableClass, String> topicColumn;
    
    @FXML
    private TableView<BookTableClass> bookTable;
    @FXML
    private TableColumn<BookTableClass, Integer> bookIdColumn;
    @FXML
    private TableColumn<BookTableClass, String> bookTopicColumn;
    @FXML
    private TableColumn<BookTableClass, String> bookNameColumn;
    
    @FXML
    private TableView<TopicTableClass> topicTable;
    @FXML
    private TableColumn<TopicTableClass, Integer> topicIdColumn;
    @FXML
    private TableColumn<TopicTableClass, String> topicNameColumn;
    
    // controllers
    private final UserController userController = new UserController();
    private final BookController bookController = new BookController();
    private final TopicController topicController = new TopicController();
    
    private MainApp mainApp;
    
    public void setUpMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
        userTable.setItems(userController.getUsersList());
        bookTable.setItems(bookController.getBookList());
        topicTable.setItems(topicController.getTopicList());
    }
    
    @FXML
    public void initialize() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<
                UserTableClass, Integer>("UserId"));
        userNameColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getNameProperty());
        usernameColumn.setCellValueFactory(cellData ->
                cellData.getValue().getUsernameProperty());
        topicColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getTopicProperty());
        
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<
                BookTableClass, Integer>("BookId"));
        bookNameColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getBookNameProperty());
        bookTopicColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getTopicNameProperty());
        
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        topicIdColumn.setCellValueFactory(new PropertyValueFactory<
                TopicTableClass, Integer>("TopicId"));
        topicNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().getTopicNameProperty());
        
        topicTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
  
    private void refreshUserTable() {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        
        userTable.setItems(null);
        userTable.layout();
        userTable.setItems(userController.getUsersList());
        
        userTable.getSelectionModel().select(selectedIndex);
    }
    
    private void refreshBookTable() {
        int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
        
        bookTable.setItems(null);
        bookTable.layout();
        bookTable.setItems(bookController.getBookList());
        
        bookTable.getSelectionModel().select(selectedIndex);
    }
    
    private void refreshTopicTable() {
        int selectedIndex = topicTable.getSelectionModel().getSelectedIndex();
        
        topicTable.setItems(null);
        topicTable.layout();
        topicTable.setItems(topicController.getTopicList());
        
        topicTable.getSelectionModel().select(selectedIndex);
    }
     
    @FXML
    private void handleNewUser() {
        UserTableClass userTableClass = null;
        boolean isOkClicked = mainApp.showCreateUserDialog(userTableClass);
        
        if (isOkClicked) {
            refreshUserTable();
        }
    }
    
    @FXML 
    private void handleDeleteUser() {
        int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
        UserTableClass userTableClass = userTable.getSelectionModel().getSelectedItem();
        
        if (selectedIndex >= 0) {
            int id = userTableClass.getUserId();
            
            if (userController.removeUser(id) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("User Deleted");
                alert.setTitle("User has been deleted");
                alert.setContentText("User " + userTableClass.getName() + " deleted");
            
                alert.showAndWait();
                
                refreshUserTable();
            } else {
                setError("User Error", "User Could not be Deleted");
            }
        }
        else {
            setError( "User not selcted","Please select a user");
        }
    }
    
    private void setError(String titleError, String userError) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setHeaderText("Error");
        alert.setTitle(titleError);
        alert.setContentText(userError);
        
        alert.showAndWait();
    }
    
    @FXML
    private void handleNewBook() {
        BookTableClass bookTableClass = null;
        boolean isOkClicked = mainApp.showCreateBookDialog(bookTableClass);
        
        if (isOkClicked == true) {
            refreshBookTable();
        }
    }
    
    @FXML
    private void handleDeleteBook() {
        int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
        BookTableClass bookTableClass = bookTable.getSelectionModel().getSelectedItem();
        
        if (selectedIndex >= 0) {
            int id = bookTableClass.getBookId();
            
            if (bookController.deleteBook(id) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Book Deleted");
                alert.setTitle("Book has been deleted");
                alert.setContentText("Book " + bookTableClass.getBookName() + " deleted");
            
                alert.showAndWait();
                
                refreshBookTable();
            } else {
                setError("Book Error", "Book Could not be Deleted");
            }
        }
        else {
            setError( "Book not selcted","Please select a book");
        }
    }
    
    @FXML
    private void handleNewTopic() {
        TopicTableClass topicTableClass = null;
        boolean isOkClicked = mainApp.showCreateTopicDialog(topicTableClass);
        
        if (isOkClicked == true) {
            refreshTopicTable();
        }
    }
    
    @FXML
    private void handleDeleteTopic() {
        int selectedIndex = topicTable.getSelectionModel().getSelectedIndex();
        TopicTableClass topicTableClass = topicTable.getSelectionModel().getSelectedItem();
        
        if (selectedIndex >= 0) {
            int id = topicTableClass.getTopicId();
            
            if (topicController.deleteTopic(id) == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setHeaderText("Topic Deleted");
                alert.setTitle("Topic has been deleted");
                alert.setContentText("Topic " + topicTableClass.getTopicName() + " deleted");
            
                alert.showAndWait();
                refreshTopicTable();
            } else {
                setError("Topic Error", "Topic Could not be Deleted");
            }
        }
        else {
            setError( "Book not selcted","Please select a book");
        }
    }
}
