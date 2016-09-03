/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Utils;

/**
 *
 * @author Test
 */
public class Item {
    private int id;
    private String description;
    
    public Item(int id, String description)
    {
        this.id = id;
        this.description = description;
    }
    
    public void setID(int id)
    {
        this.id = id;
    }
    
    public int getID()
    {
        return this.id;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    @Override
    public String toString()
    {
        return description;
    }
}
