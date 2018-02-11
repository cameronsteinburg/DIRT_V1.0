/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemDomain;

import java.util.ArrayList;

/**
 * This class represents the workorder entity and communicates with the persistence layer
 *
 */
public class WorkOrder {
   
    private ArrayList<WorkOrderItem> expenses;
    private String description;
    private double total;
    private char id;
    
    /**
     * default WorkOrder constructor
     */
    public WorkOrder(){
        
    }
    
    /**
     * Sends itself to JDBCCommands to be deleted from the database
     * @return true if no errors occur
     */
    public boolean deleteSQL(){
        return false;
        
    }
    
}
