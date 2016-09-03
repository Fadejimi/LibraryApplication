/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Controller.TopicController;
import Controller.UserBookController;
import Controller.UserController;
import Models.User;
import com.libraryapplication.tableclasses.UserBookTableClass;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Adegbulugbe
 */
public class UserLayoutController {
    public MainApp mainApp;
    
    public int id = -1;
    
    @FXML
    private TableView userBookTable;
    @FXML
    private TableColumn<UserBookTableClass, Integer> userBookIDColumn;
    @FXML
    private TableColumn<UserBookTableClass, String> userBookTopicColumn;
    @FXML
    private TableColumn<UserBookTableClass, String> userBookNameColumn;
    
    @FXML
    private TableView recommendedBookTable;
    @FXML
    private TableColumn<UserBookTableClass, Integer> recommendedBookIDColumn;
    @FXML
    private TableColumn<UserBookTableClass, String> recommendedBookTopicColumn;
    @FXML
    private TableColumn<UserBookTableClass, String> recommendedBookNameColumn;
    
    @FXML
    private Label nameLabel;
    @FXML
    private Label usernameLabel;
    
    // controllers
    UserBookController userBookController = new UserBookController();
    UserController userController = new UserController();
    TopicController topicController = new TopicController();
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
        if (id != -1) {
            User user = userController.getUser(id);
            nameLabel.setText(user.name);
            usernameLabel.setText(user.username);
            
            userBookTable.setItems(userBookController.getUserBookList(id));
            recommendedBookTable.setItems(userBookController.getRecommendedUserBookList(id));
        }
    }
    
    @FXML
    public void initialize() {
        userBookIDColumn.setCellValueFactory( new PropertyValueFactory<
                UserBookTableClass, Integer>("UserBookId"));
        userBookTopicColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getTopicNameProperty());
        userBookNameColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getBookNameProperty());
        
        userBookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        recommendedBookIDColumn.setCellValueFactory( new PropertyValueFactory<
                UserBookTableClass, Integer>("userBookId"));
        recommendedBookTopicColumn.setCellValueFactory(cellData -> 
                cellData.getValue().getTopicNameProperty());
        recommendedBookNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().getBookNameProperty());
        
        recommendedBookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    
    @FXML
    private void handleBorrowRecommendedBook() {
        
    }
    
    @FXML
    private void handleBorrowBook() {
        if (id != -1) {
            boolean isOkClicked = mainApp.showBorrowBook(id);
            if (isOkClicked) {
                refreshUserBookTable();
            }
        }
    }
    
    private void refreshUserBookTable() {
        userBookTable.setItems(null);
        userBookTable.layout();
        userBookTable.setItems(userBookController.getUserBookList(id));
        
    }
}
