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

import Models.Book;

public class BookQueries {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertBook = null;
    private PreparedStatement selectAllBooks = null;
    private PreparedStatement selectBook = null;
    private PreparedStatement selectBookByTopic = null;
    private PreparedStatement deleteBook = null;
    private PreparedStatement updateBook = null;
    
    public BookQueries() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            insertBook = con.prepareStatement("INSERT INTO book (TopicId, "
                    + "BookName) VALUES(?,?)");
            
            selectAllBooks = con.prepareStatement("SELECT * FROM book");
            
            selectBook = con.prepareStatement("SELECT * FROM book WHERE "
                    + "BookId = ?");
            
            selectBookByTopic = con.prepareStatement("SELECT * FROM book WHERE "
                    + "TopicId = ?");
            
            deleteBook = con.prepareStatement("DELETE FROM book WHERE BookId");
            
            updateBook = con.prepareStatement("UPDATE book SET BookName = ? AND "
                    + "TopicId = ? WHERE BookId = ?");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public int updateBook(String bookName, int topicId, int bookId) {
        int result = 0;
        try {
            updateBook.setString(1, bookName);
            updateBook.setInt(2, topicId);
            updateBook.setInt(3, bookId);
            
            result = updateBook.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    public int addBook(int tid, String name) {
        int result = 0;
        
        try {
            insertBook.setInt(1, tid);
            insertBook.setString(2, name);
            
            result = insertBook.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public List<Book> getAllBooks() {
        List<Book> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            rs = selectAllBooks.executeQuery();
            
            while(rs.next()) {
                results.add(new Book(
                    rs.getInt("BookId"),
                    rs.getInt("TopicId"),
                    rs.getString("BookName")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<Book> getBook(int bookId) {
        List<Book> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            selectBook.setInt(1, bookId);
            
            rs = selectBook.executeQuery();
            
            while(rs.next()) {
                results.add(new Book(
                    rs.getInt("BookId"),
                    rs.getInt("TopicId"),
                    rs.getString("BookName")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<Book> getBookByTopic(int topicId) {
        List<Book> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            selectBookByTopic.setInt(1, topicId);
            
            rs = selectBookByTopic.executeQuery();
            
            while(rs.next()) {
                results.add(new Book(
                    rs.getInt("BookId"),
                    rs.getInt("TopicId"),
                    rs.getString("BookName")));
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public int deleteBook(int bookId) {
        int result = 0;
        
        try {
            deleteBook.setInt(1, bookId);
            
            result = deleteBook.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
