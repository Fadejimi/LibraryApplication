/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication.services;

import Database.AdminQueries;
import Models.Admin;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adegbulugbe
 */
public class AdminService {
    public AdminQueries adminQuery = new AdminQueries();
    
    public int addAdmin(String username, String password) {
        return adminQuery.insertAdmin(username, password);
    }
    
    public Admin loginAdmin(String username, String password) {
        List<Admin> adminResult = new ArrayList<>();
        adminResult = adminQuery.getAdmin(username, password);
        
        if (adminResult != null) {
            Admin admin = adminResult.get(0);
            
            return admin;
        }else {
            return null;
        }
    }
}
