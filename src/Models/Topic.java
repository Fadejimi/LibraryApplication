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
public class Topic {
    public int topicId;
    public String topicName;
    
    public Topic(int topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }
}
