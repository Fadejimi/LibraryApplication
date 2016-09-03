/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Models.Admin;
import Models.User;
import Utils.Props;
import com.libraryapplication.services.AdminService;
import com.libraryapplication.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 *
 * @author Adegbulugbe
 */
public class LoginController {
    private AdminService adminService = new AdminService();
    private UserService userService = new UserService();
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    public Admin admin;
    public User user;
    
    public static final int ADMIN_LOGIN = 1;
    public static final int USER_LOGIN = 2;
    
    public Stage dialogStage;
    public boolean okClicked = false;
    public int flag;
    
    @FXML
    public void handleLogin() {
        if (inputIsValid()) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            if (flag == Props.ADMIN_LOGIN) {
                admin = adminService.loginAdmin(username, password);
                if (admin == null) {
                    errorLabel.setText("Username and password is not correct");
                } else {
                    okClicked = true;
                    dialogStage.close();
                }
            }
            else if (flag == Props.STAFF_LOGIN) {
                user = userService.loginUser(username, password);
                if (user == null) {
                    errorLabel.setText("Username and password is not correct");
                } else {
                    okClicked = true;
                    dialogStage.close();
                }
            }
        }
    }
    
    @FXML
    private void handleExit() {
        dialogStage.close();
    }
    
    private boolean inputIsValid()
    {
        String errorString = "";
        
        if (usernameField.getText().equals("") || 
                usernameField.getText().length() == 0) {
            errorString += "\nNo username value set";
        }
        if (passwordField.getText().equals("") || 
                passwordField.getText().length() == 0) {
            errorString += "\nNo password value set";
        }
        
        if (errorString.equals("")) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please Correct Invalid fields");
            alert.setContentText(errorString);
            
            alert.showAndWait();
            return false;
        }
    }
}
