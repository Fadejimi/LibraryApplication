/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Models.Admin;
import Models.User;
import Utils.Props;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Adegbulugbe
 */
public class RootMenuController {
    private MainApp mainApp;
    
    @FXML 
    private MenuItem userLoginMenuItem;
    @FXML
    private MenuItem adminLoginMenuItem;
    
    public Admin admin;
    public User user;
    
    public int userID = -1;
    
    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }
    
    @FXML
    public void handleAdminLogin() {
        admin = mainApp.showAdminLogin(Props.ADMIN_LOGIN);
        if (admin != null) {
            adminLoginMenuItem.setDisable(true);
            userLoginMenuItem.setDisable(true);
            
            mainApp.showAdminLayout();
        }
    }
    
    @FXML
    public void handleUserLogin() {
        user = mainApp.showUserLogin(Props.STAFF_LOGIN);
        if (user != null) {
            userID = user.userId;
            adminLoginMenuItem.setDisable(true);
            userLoginMenuItem.setDisable(true);
            
            mainApp.showUserLayout(userID);
        }
    }
    
    @FXML
    public void handleExit() {
        System.exit(0);
    }
}
