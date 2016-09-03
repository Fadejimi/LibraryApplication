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
public class UserBook {
    public int userBookId;
    public int UserId;
    public int BookId;
    
    public UserBook(int userBookId, int userId, int bookId)
    {
        this.userBookId = userBookId;
        this.UserId = userId;
        this.BookId = bookId;
    }
    
}
