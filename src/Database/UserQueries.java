/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

/**
 *
 * @author Adegbulugbe
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.User;

public class UserQueries {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertUser = null;
    private PreparedStatement selectAllUsers = null;
    private PreparedStatement selectUser = null;
    private PreparedStatement deleteUser = null;
    private PreparedStatement loginUser = null;
    private PreparedStatement updateUser = null;
    
    
    public UserQueries() {
        try {
            con =
                DriverManager.getConnection( URL, USERNAME, PASSWORD );
            
            insertUser = con.prepareStatement("INSERT INTO user (Name, TopicId, "
                    + "Username, Password) VALUES(?, ?, ?, ?)");
            
            selectAllUsers = con.prepareStatement("SELECT * FROM user");
            
            selectUser = con.prepareStatement("SELECT * FROM user WHERE UserId = ?");
            
            deleteUser = con.prepareStatement("DELETE FROM user WHERE UserId = ?");
            
            loginUser = con.prepareStatement("SELECT * FROM user WHERE Username = ?"
                    + " AND Password = ?");
            
            updateUser = con.prepareStatement("UPDATE user SET Name = ?, Username = ?,"
                    + "TopicId = ? AND Password = ? WHERE UserId = ?");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int updateUser(String name, String username, int tid, String pass,
            int userId) 
    {
        int result = 0;
        try {
            updateUser.setString(1, name);
            updateUser.setString(2, username);
            updateUser.setInt(3, tid);
            updateUser.setString(4, pass);
            updateUser.setInt(1, userId);
            
            result = updateUser.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public int addUser(String name, int tid, String username, String password) {
        int result = 0;
        try {
            insertUser.setString(1, name);
            insertUser.setInt(2, tid);
            insertUser.setString(3, username);
            insertUser.setString(4, password);
            
            result = insertUser.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public List<User> getAllUsers() {
        List<User> results = null;
        ResultSet rs = null;
        
        try {
            rs = selectAllUsers.executeQuery();
            
            results = new ArrayList<>();
            
            while(rs.next()) {
                results.add(new User(
                    rs.getInt("UserId"),
                    rs.getString("Name"),
                    rs.getInt("TopicId"),
                    rs.getString("Username"),
                    rs.getString("Password")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<User> getUsers() {
        List<User> results = null;
        ResultSet rs = null;
        
        try {
            rs = selectAllUsers.executeQuery();
            
            results = new ArrayList<>();
            
            while(rs.next()) {
                results.add(new User(
                    rs.getInt("UserId"),
                    rs.getString("Name"),
                    rs.getInt("TopicId"),
                    rs.getString("Username"),
                    rs.getString("Password")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<User> getUser(int id) {
        List<User> results = null;
        ResultSet rs = null;
        
        try {
            selectUser.setInt(1, id);
            rs = selectUser.executeQuery();
            
            results = new ArrayList<>();
            
            while(rs.next()) {
                results.add(new User(
                    rs.getInt("UserId"),
                    rs.getString("Name"),
                    rs.getInt("TopicId"),
                    rs.getString("Username"),
                    rs.getString("Password")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<User> getUser(String username, String password) {
        List<User> results = null;
        ResultSet rs = null;
        
        try {
            loginUser.setString(1, username);
            loginUser.setString(2, password);
            rs = loginUser.executeQuery();
            
            results = new ArrayList<>();
            
            while(rs.next()) {
                results.add(new User(
                    rs.getInt("UserId"),
                    rs.getString("Name"),
                    rs.getInt("TopicId"),
                    rs.getString("Username"),
                    rs.getString("Password")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public int deleteUser(int id) {
        int result = 0;
        
        try {
            deleteUser.setInt(1, id);
            
            result = deleteUser.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
