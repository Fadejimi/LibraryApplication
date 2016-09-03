/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication.services;

import Database.BookQueries;
import Database.TopicQueries;
import Models.Book;
import Models.Topic;
import com.libraryapplication.tableclasses.BookTableClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adegbulugbe
 */
public class BookService {
    private BookQueries bookQuery;
    private TopicQueries topicQuery;
    
    public BookService() {
        bookQuery = new BookQueries();
        topicQuery = new TopicQueries();
    }
    
    public int updateBook(String bookName, int topicId, int bookId) {
        return bookQuery.updateBook(bookName, topicId, bookId);
    }
    
    public int addBook(int topicId, String bookName) {
        return bookQuery.addBook(topicId, bookName);
    }
    
    public int deleteBook(int bookId) {
        return bookQuery.deleteBook(bookId);
    }
    
    public List<BookTableClass> getBookTableClass() {
        List<Book> bookList = new ArrayList<>();
        bookList = bookQuery.getAllBooks();
        
        List<BookTableClass> bookTableList = new ArrayList<>();
        for (Book book : bookList) {
            int bookId = book.bookId;
            int topicId = book.topicId;
            String bookName = book.bookName;
            
            List<Topic> topicList = new ArrayList<>();
            topicList = topicQuery.getTopic(topicId);
            
            Topic topic = topicList.get(0);
            String topicName = topic.topicName;
            
            BookTableClass bookTable = new BookTableClass(bookId, topicName,
                bookName);
            
            bookTableList.add(bookTable);
        }
        
        return bookTableList;
    }
    
    public List<BookTableClass> getBookByTopicTableClass(int tid) {
        List<Book> bookList = new ArrayList<>();
        bookList = bookQuery.getBookByTopic(tid);
        
        List<BookTableClass> bookTableList = new ArrayList<>();
        for (Book book : bookList) {
            int bookId = book.bookId;
            int topicId = book.topicId;
            String bookName = book.bookName;
            
            List<Topic> topicList = new ArrayList<>();
            topicList = topicQuery.getTopic(topicId);
            
            Topic topic = topicList.get(0);
            String topicName = topic.topicName;
            
            BookTableClass bookTable = new BookTableClass(bookId, topicName,
                bookName);
            
            bookTableList.add(bookTable);
        }
        
        return bookTableList;
    }
    public Book getBook(int bookId) {
        return bookQuery.getBook(bookId).get(0);
    }
    
    
}
