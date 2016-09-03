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
import Models.UserRec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserRecQueries {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertUserRec = null;
    private PreparedStatement selectAllUserRecs = null;
    private PreparedStatement selectUserRec = null;
    private PreparedStatement deleteUserRec = null;
    private PreparedStatement selectTheUsersRecs = null;
    
    public UserRecQueries() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            insertUserRec = con.prepareStatement("INSERT INTO user_rec ("
                    + "UserId, TopicId, BookId) VALUES(?,?,?)");
            
            selectAllUserRecs = con.prepareStatement("SELECT * FROM user_rec");
            
            selectUserRec = con.prepareStatement("SELECT * FROM user_rec WHERE "
                    + "UserRecId = ?");
            
            selectTheUsersRecs = con.prepareStatement("SELECT * FROM user_rec WHERE "
                    + "UserId = ?");
            
            deleteUserRec = con.prepareStatement("DELETE FROM user_rec WHERE UserRecId = ?");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int addUserRec(int uid, int tid, int bid) {
        int result = 0;
        
        try {
            insertUserRec.setInt(1, uid);
            insertUserRec.setInt(2, tid);
            insertUserRec.setInt(3, bid);
            
            result = insertUserRec.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    
    public List<UserRec> getAllUserRecs() {
        List<UserRec> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            rs = selectAllUserRecs.executeQuery();
            while(rs.next()) {
                results.add(new UserRec(
                    rs.getInt("UserRecId"),
                    rs.getInt("UserId"),
                    rs.getInt("TopicId"),
                    rs.getInt("BookId")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<UserRec> getUserRec(int id) {
        List<UserRec> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            selectUserRec.setInt(1, id);
            rs = selectUserRec.executeQuery();
            
            while(rs.next()) {
                results.add(new UserRec(
                    rs.getInt("UserRecId"),
                    rs.getInt("UserId"),
                    rs.getInt("TopicId"),
                    rs.getInt("BookId")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<UserRec> getTheUserRecs(int id) {
        List<UserRec> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            selectTheUsersRecs.setInt(1, id);
            rs = selectTheUsersRecs.executeQuery();
            
            while(rs.next()) {
                results.add(new UserRec(
                    rs.getInt("UserRecId"),
                    rs.getInt("UserId"),
                    rs.getInt("TopicId"),
                    rs.getInt("BookId")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public int deleteUserRec(int uid) {
        int result = 0;
        
        try {
            deleteUserRec.setInt(1, uid);
            
            result = deleteUserRec.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}