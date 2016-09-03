/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Controller.TopicController;
import Controller.UserController;
import Models.User;
import Utils.Item;
import com.libraryapplication.tableclasses.TopicTableClass;
import com.libraryapplication.tableclasses.UserTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Adegbulugbe
 */
public class CreateUserController {
    @FXML
    private TextField nameField;
    
    @FXML
    private ComboBox topicBox;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private PasswordField confirmField;
    
    public boolean okClicked;
    public Stage dialogStage;
    public UserTableClass userTableClass;
    private int userId = -1;
    private final UserController userController = new UserController();
    
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
    public void setUserValue(UserTableClass userTableClass) {
        setTopicItems();
        if (userTableClass != null) {
            User user = userController.getUser(userTableClass.getUserId());
            this.userId = user.userId;
            nameField.setText(user.name);
            passwordField.setText(user.password);
            usernameField.setText(user.username);
            confirmField.setText(user.password);
        }
    }
    
    @FXML
    private void handleSubmit() {
        if (inputIsValid()) {
            String name = nameField.getText();
            String password = passwordField.getText();
            String username = usernameField.getText();
            Item topicItem = (Item) topicBox.getSelectionModel().getSelectedItem();
            int topicId = topicItem.getID();
            
            if (userId != -1) {
                if (userController.updateUser(name, username, topicId, 
                        password, userId) == 1) {
                    okClicked = true;
                    dialogStage.close();
                }
                else {
                    setAlert("Could not update student record");
                }
            }
            else {
                if (userController.addUser(name, topicId, username, password) 
                        == 1) {
                    okClicked = true;
                    clearFields();
                }
                else {
                    setAlert("Could not add student record");
                }
            }
        }
    }
    
    private void clearFields() {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmField.setText("");
    }
    
    private boolean inputIsValid() {
        String errorString = "";
        if (nameField.getText().equals("") || nameField.getText().length() == 0) {
            errorString += "\nName Field not set";
        }
        if (usernameField.getText().equals("") || 
                usernameField.getText().length() == 0) {
            errorString += "\nUsername Field not set";
        }
        if (passwordField.getText().equals("") || 
                passwordField.getText().length() == 0) {
            errorString += "\nPassword Field not set";
        }
        if (!passwordField.getText().equals(confirmField.getText())) {
            errorString += "\nPassword is not equal to the confirm field";
        }
        if (topicBox.getSelectionModel().getSelectedIndex() < 0) {
            errorString += "\nTopic is not selected";
        }
        
        if (errorString.equals("")) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please Correct Invalid fields");
            alert.setContentText(errorString);
            alert.showAndWait();
            
            return false;
        }
    }
    
    private void setAlert(String errorString) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Error");
        alert.setHeaderText("User Error");
        alert.setContentText(errorString);
        
        alert.showAndWait();
    }
    
    @FXML
    private void handleExit() 
    {
        dialogStage.close();
    }
}
