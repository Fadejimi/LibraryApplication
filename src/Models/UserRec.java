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
public class UserRec {
    public int UserRecId;
    public int UserId;
    public int TopicId;
    public int BookId;
    
    public UserRec(int userRecId, int userId, int topicId, int bookId) {
        this.UserRecId = userRecId;
        this.UserId = userId;
        this.TopicId = topicId;
        this.BookId = bookId;
    }
}
