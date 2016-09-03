/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Models.Book;
import com.libraryapplication.services.BookService;
import com.libraryapplication.tableclasses.BookTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adegbulugbe
 */
public class BookController {
    private final BookService bookService = new BookService();
    private ObservableList<BookTableClass> bookList = 
            FXCollections.observableArrayList();
    
    public int addBook(int topicId, String bookName) {
        return bookService.addBook(topicId, bookName);
    }
    
    public Book getBook(int bookId) {
        return bookService.getBook(bookId);
    }
    
    public int updateBook(String bookName, int topicId, int bookId) {
        return bookService.updateBook(bookName, topicId, bookId);
    }
    
    public ObservableList<BookTableClass> getBookList() {
        if (bookList != null) 
            bookList.clear();
        
        bookList = FXCollections.observableArrayList(bookService.getBookTableClass());
        return bookList;
    }
    
    public ObservableList<BookTableClass> getBookByTopicList(int tid) {
        if (bookList != null) 
            bookList.clear();
        
        bookList = FXCollections.observableArrayList(bookService.getBookByTopicTableClass(tid));
        return bookList;
    }
    
    public int deleteBook(int bookId) {
        return bookService.deleteBook(bookId);
    }
}
