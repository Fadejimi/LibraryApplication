/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Models.UserBook;
import com.libraryapplication.services.UserBookService;
import com.libraryapplication.tableclasses.UserBookTableClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adegbulugbe
 */
public class UserBookController {
    private UserBookService userBookService = new UserBookService();
    
    private ObservableList<UserBookTableClass> userBookList = 
            FXCollections.observableArrayList();
    private ObservableList<UserBookTableClass> userRecommendedList = 
            FXCollections.observableArrayList();
    
    
    public int addUserBook(int userId, int bookId) {
        return userBookService.addUserBook(userId, bookId);
    }
    
    public UserBook getUserBook(int userBookId) {
        return userBookService.getUserBook(userBookId);
    }
    
    public ObservableList<UserBookTableClass> getUserBookList(int userId) {
        if (userBookList != null) {
            userBookList.clear();
        }
        userBookList = FXCollections.observableArrayList(userBookService.getUserBooks(userId));
        return userBookList;
    }
    
    public ObservableList<UserBookTableClass> getRecommendedUserBookList(int userId) {
        if (userRecommendedList != null) {
            userRecommendedList.clear();
        }
        userRecommendedList = FXCollections.observableArrayList(userBookService.getRecommendedBooks(userId));
        return userRecommendedList;
    }
}
