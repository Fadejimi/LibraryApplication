/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Models.User;
import com.libraryapplication.services.UserService;
import com.libraryapplication.tableclasses.UserTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adegbulugbe
 */
public class UserController {
    private final UserService userService = new UserService();
    private ObservableList<UserTableClass> userList = 
            FXCollections.observableArrayList();
    
    public int addUser(String name, int tid, String username, String password) {
        return userService.addUser(name, tid, username, password);
    }
    
    public int updateUser(String name, String username, int tid, String pass,
            int userId) {
        return userService.updateUser(name, username, tid, pass, userId);
    }
    
    public User getUser(int userId){
        return userService.getUser(userId);
    }
    
    public ObservableList<UserTableClass> getUsersList() {
        if (userList != null) {
            userList.clear();
        }
        userList = FXCollections.observableArrayList(userService.getUserTableClass());
        return userList;
    }
    
    public int removeUser(int userId) {
        return userService.deleteUser(userId);
    }
}
