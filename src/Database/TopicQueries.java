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

import Models.Topic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TopicQueries {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertTopic = null;
    private PreparedStatement selectAllTopics = null;
    private PreparedStatement selectTopic = null;
    private PreparedStatement deleteTopic = null;
    private PreparedStatement updateTopic = null;
    public TopicQueries() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            
            insertTopic = con.prepareStatement("INSERT INTO topic (Topic) "
                    + "VALUES(?)");
            
            selectAllTopics = con.prepareStatement("SELECT * FROM topic");
            
            selectTopic = con.prepareStatement("SELECT * FROM topic WHERE TopicId = ?");
            
            deleteTopic = con.prepareStatement("DELETE FROM topic WHERE TopicId = ?");
            
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insertTopic(String topicName) {
        int result = 0;
        try {
            insertTopic.setString(1, topicName);
            
            result = insertTopic.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public List<Topic> getAllTopic() {
        List<Topic> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            rs = selectAllTopics.executeQuery();
            
            while(rs.next()) {
                results.add(new Topic(
                    rs.getInt("TopicId"),
                    rs.getString("Topic")));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public List<Topic> getTopic(int topicId) {
        List<Topic> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            selectTopic.setInt(1, topicId);
            
            rs = selectTopic.executeQuery();
            
            while(rs.next()) {
                results.add(new Topic(
                    rs.getInt("TopicId"),
                    rs.getString("Topic")));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
    
    public int deleteTopic(int topicId) {
        int result = 0;
        
        try {
            deleteTopic.setInt(1, topicId);
            
            result = deleteTopic.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
}
