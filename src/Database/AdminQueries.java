/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Database;

import Models.Admin;
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

public class AdminQueries {
    static final String URL = "jdbc:mysql://localhost/library";
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    private Connection con;
    
    private PreparedStatement insertAdmin = null;
    private PreparedStatement selectAdmin = null;
    
    public AdminQueries() {
        try {
            con =
                DriverManager.getConnection( URL, USERNAME, PASSWORD );
            
            insertAdmin = con.prepareStatement("INSERT INTO admin (Username,"
                    + " Password) VALUES(?,?)");
            
            selectAdmin = con.prepareStatement("SELECT * FROM admin WHERE Username = ? "
                    + "AND Password = ?");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int insertAdmin(String username, String password) {
        int result = 0;
        try {
            insertAdmin.setString(1, username);
            insertAdmin.setString(2, password);
            
            result = insertAdmin.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
    }
    
    public List<Admin> getAdmin(String username, String password) {
        List<Admin> results = null;
        ResultSet rs = null;
        
        try {
            results = new ArrayList<>();
            
            selectAdmin.setString(1, username);
            selectAdmin.setString(2, password);
            
            rs = selectAdmin.executeQuery();
            
            while(rs.next()) {
                results.add(new Admin(
                    rs.getString("Username"),
                    rs.getString("Password")));
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
}
