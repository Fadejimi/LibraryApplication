/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.libraryapplication;

import Controller.TopicController;
import com.libraryapplication.tableclasses.TopicTableClass;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Adegbulugbe
 */
public class TopicDialogController {
    @FXML
    private TextField topicField;
    
    public TopicTableClass topicTableClass;
    public int topicId = -1;
    public boolean isOkClicked;
    public Stage dialogStage;
    private final TopicController topicController = new TopicController();
    
    public void setTopicTableClass(TopicTableClass topicTableClass) {
        if (topicTableClass != null) {
            topicId = topicTableClass.getTopicId();
            topicField.setText(topicTableClass.getTopicName());
        }
    }
    
    @FXML
    private void handleSubmit()
    {
        if (inputIsValid()) {
            String topicName = topicField.getText();
            if (topicId == -1) {
                if (topicController.addTopic(topicName) == 1) {
                    isOkClicked = true;
                    topicField.setText("");
                }
                else {
                    setError("Unable to add topic");
                }
            }
        }
    }
    
    private boolean inputIsValid() {
        String errorString = "";
        
        if (topicField.getText().equals("") || 
                topicField.getText().length() == 0) {
            errorString += "\nTopic field has to be set";
        }
        
        if (errorString.equals("")) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please Correct Invalid fields");
            alert.setContentText(errorString);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    @FXML
    private void handleExit() {
        dialogStage.close();
    }
    
    private void setError(String errorString) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Topic Error");
            alert.setHeaderText("Unable to create topic");
            alert.setContentText(errorString);
            
            alert.showAndWait();
    }
}
