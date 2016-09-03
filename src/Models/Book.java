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
public class Book {
    public int bookId;
    public int topicId;
    public String bookName;
    
    public Book(int bookId, int topicId, String bookName) {
        this.bookId = bookId;
        this.topicId = topicId;
        this.bookName = bookName;
    }
}
