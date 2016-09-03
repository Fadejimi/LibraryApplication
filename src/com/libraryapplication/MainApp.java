/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Models.Admin;
import Models.User;
import Utils.Props;
import com.libraryapplication.tableclasses.BookTableClass;
import com.libraryapplication.tableclasses.TopicTableClass;
import com.libraryapplication.tableclasses.UserTableClass;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Adegbulugbe
 */
public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    public MainApp() {
        
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("E-Library Recommender");
        
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootMenu.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            RootMenuController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Admin showAdminLogin(int flag) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/LoginDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            
            String title = "";
            if (flag == Props.ADMIN_LOGIN) {
                title = "Admin Login";
            } else if (flag == Props.STAFF_LOGIN) {
                title = "Staff Login";
            }
            
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            LoginController controller = loader.getController();
            controller.dialogStage = dialogStage;
            controller.flag = flag;
            
            dialogStage.showAndWait();
            return controller.admin;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public User showUserLogin(int flag) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/LoginDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            
            String title = "";
            if (flag == Props.ADMIN_LOGIN) {
                title = "Admin Login";
            } else if (flag == Props.STAFF_LOGIN) {
                title = "Staff Login";
            }
            
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            LoginController controller = loader.getController();
            controller.dialogStage = dialogStage;
            controller.flag = flag;
            
            dialogStage.showAndWait();
            return controller.user;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void showAdminLayout() {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/AdminApp.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            rootLayout.setCenter(pane);
            
            // give the controller access to the admin layout
            AdminController controller = loader.getController();
            controller.setUpMainApp(this);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void showUserLayout(int id) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/UserApp.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            rootLayout.setCenter(pane);
            
            UserLayoutController controller = loader.getController();
            controller.id = id;
            controller.setMainApp(this);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean showCreateUserDialog(UserTableClass userTableClass) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/CreateUserDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            
            dialogStage.setTitle("Create new user");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            CreateUserController controller = loader.getController();
            controller.setUserValue(userTableClass);
            controller.dialogStage = dialogStage;
            
            dialogStage.showAndWait();
            return controller.okClicked;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean showCreateBookDialog(BookTableClass bookTableClass)
    {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/CreateBookDialog.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            
            dialogStage.setTitle("Create new book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            BookDialogController controller = loader.getController();
            controller.setBookTableClass(bookTableClass);
            controller.dialogStage = dialogStage;
            
            dialogStage.showAndWait();
            return controller.okClicked;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean showCreateTopicDialog(TopicTableClass topicTableClass) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/CreateTopic.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            
            dialogStage.setTitle("Create new topic");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            TopicDialogController controller = loader.getController();
            controller.dialogStage = dialogStage;
            controller.setTopicTableClass(topicTableClass);
            
            dialogStage.showAndWait();
            return controller.isOkClicked;
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public boolean showBorrowBook(int userId) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/BorrowBook.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            
            dialogStage.setTitle("Borrow Book");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(pane);
            dialogStage.setScene(scene);
            
            BorrowBookController controller = loader.getController();
            controller.dialogStage = dialogStage;
            controller.setUserId(userId);
            
            dialogStage.showAndWait();
            return controller.okClicked;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
