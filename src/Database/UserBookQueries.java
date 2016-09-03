/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.UserBook;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adegbulugbe
 */
public class UserBookQueries {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertUserBook;
    private PreparedStatement deleteUserBook;
    private PreparedStatement selectUserBook;
    private PreparedStatement selectAllUserBooks;
    private PreparedStatement selectAllUsersBooks;
    
    public UserBookQueries() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            insertUserBook = con.prepareStatement("INSERT INTO user_book (UserId, "
                    + "BookId) VALUES(?, ?)");
            deleteUserBook = con.prepareStatement("DELETE FROM user_book WHERE "
                    + "UserBookId");
            selectUserBook = con.prepareStatement("SELECT * FROM user_book WHERE "
                    + "UserBookId = ?");
            selectAllUserBooks = con.prepareStatement("SELECT * FROM user_book");
            
            selectAllUsersBooks = con.prepareStatement("SELECT * FROM user_book WHERE "
                    + "UserId = ?");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int addUserBook(int userId, int bookId) {
        int result = 0;
        
        try {
            insertUserBook.setInt(1, userId);
            insertUserBook.setInt(2, bookId);
            
            result = insertUserBook.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public int deleteUserBook(int userBookId) {
        int result = 0;
        
        try {
            deleteUserBook.setInt(1, userBookId);
            
            result = deleteUserBook.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public List<UserBook> getAllUserBooks() {
        List<UserBook> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            rs = selectAllUserBooks.executeQuery();
            
            while(rs.next()) {
                results.add(new UserBook(
                    rs.getInt("UserBookId"),
                    rs.getInt("UserId"),
                    rs.getInt("BookId")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<UserBook> getUserBook(int userBookId) {
        List<UserBook> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            selectUserBook.setInt(1, userBookId);
            rs = selectUserBook.executeQuery();
            
            while(rs.next()) {
                results.add(new UserBook(
                    rs.getInt("UserBookId"),
                    rs.getInt("UserId"),
                    rs.getInt("BookId")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<UserBook> getTheUsersBooks(int userId) {
        List<UserBook> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            selectAllUsersBooks.setInt(1, userId);
            rs = selectAllUsersBooks.executeQuery();
            
            while(rs.next()) {
                results.add(new UserBook(
                    rs.getInt("UserBookId"),
                    rs.getInt("UserId"),
                    rs.getInt("BookId")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
}
