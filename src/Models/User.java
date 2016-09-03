/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

/**
 *
 * @author Adegbulugbe
 */
public class User {
    public int userId;
    public String name;
    public int topicId;
    public String username;
    public String password;
    
    public User(int userId, String name, int topicId, String username, 
            String password) {
        this.userId = userId;
        this.name = name;
        this.topicId = topicId;
        this.username = username;
        this.password = password;
    }
}
