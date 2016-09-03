/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication.services;

import Database.TopicQueries;
import Database.UserQueries;
import Models.User;
import com.libraryapplication.tableclasses.UserTableClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adegbulugbe
 */
public class UserService {
    public UserQueries userQuery;
    public TopicQueries topicQuery;
    
    public UserService() {
        userQuery = new UserQueries();
        topicQuery = new TopicQueries();
    }
    
    public int updateUser(String name, String username, int tid, String pass,
            int userId) {
        return userQuery.updateUser(name, username, tid, pass, userId);
    }
    
    public int addUser(String name, int topicId, String username, String pass) {
        return userQuery.addUser(name, topicId, username, pass);
    }
    
    public int deleteUser(int userId) {
        return userQuery.deleteUser(userId);
    }
    
    public List<UserTableClass> getUserTableClass() {
        List<User> userList = new ArrayList<>();
        List<UserTableClass> userTableList = null;
        
        userList = userQuery.getAllUsers();
        if (userList != null) {
            if (!userList.isEmpty()) {
                userTableList = new ArrayList<>();
            
                for (User user : userList) {
                    int userId = user.userId;
                    String name = user.name;
                    String username = user.username;
                
                    int topicId = user.topicId;
                    String topicName = topicQuery.getTopic(topicId)
                                    .get(0).topicName;
                    
                    userTableList.add(new UserTableClass(userId, 
                        name, username, topicName));
                }
            }
        }
        return userTableList;
    }
    
    public User getUser(int userId) {
        return userQuery.getUser(userId).get(0);
    }
    
    public User loginUser(String username, String password) {
        return userQuery.getUser(username, password).get(0);
    }
}
