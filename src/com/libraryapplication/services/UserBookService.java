/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication.services;

import Database.BookQueries;
import Database.TopicQueries;
import Database.UserBookQueries;
import Database.UserRecQueries;
import Models.Book;
import Models.UserBook;
import Models.UserRec;
import com.libraryapplication.tableclasses.UserBookTableClass;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adegbulugbe
 */
public class UserBookService {
    private UserBookQueries userBookQuery = new UserBookQueries();
    private BookQueries bookQuery = new BookQueries();
    private TopicQueries topicQuery = new TopicQueries();
    private UserRecQueries userRecQuery = new UserRecQueries();
    
    public int addUserBook(int userId, int bookId) {
        return userBookQuery.addUserBook(userId, bookId);
    }
    
    public UserBook getUserBook(int userBookId) {
        if (userBookQuery.getUserBook(userBookId) != null) {
            return userBookQuery.getUserBook(userBookId).get(0);
        }
        else 
            return null;
    }
    
    public List<UserBookTableClass> getUserBooks(int id) {
        List<UserBookTableClass> userTableList = null;
        
        List<UserBook> userBookList = new ArrayList<>();
        
        userBookList = userBookQuery.getTheUsersBooks(id);
        if (!userBookList.isEmpty()) {
            for (UserBook userBook : userBookList) {
                int userBookId = userBook.userBookId;
                int bookId = userBook.BookId;
                
                String name = bookQuery.getBook(bookId).get(0).bookName;
                String topicName = topicQuery.getTopic(
                        bookQuery.getBook(bookId).get(0).topicId)
                        .get(0).topicName;
                
                userTableList.add(new UserBookTableClass(
                    userBookId, name, topicName));
            }
        }
        
        return userTableList;
    }
    
    public List<UserBookTableClass> getRecommendedBooks(int id) {
        List<UserBookTableClass> userTableList = null;
        
        List<UserRec> userRecList = new ArrayList<>();
        userRecList = userRecQuery.getTheUserRecs(id);
        
        if (!userRecList.isEmpty()) {
            for (UserRec userRec : userRecList) {
                int userRecId = userRec.UserRecId;
                
                String bookName = bookQuery.getBook(userRec.BookId).get(0)
                        .bookName;
                String topicName = topicQuery.getTopic(userRec.TopicId)
                        .get(0).topicName;
                
                userTableList.add(new UserBookTableClass(
                        userRecId, bookName, topicName));
            }
        }
        
        return userTableList;
    }
}
