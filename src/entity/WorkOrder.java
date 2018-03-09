package entity;

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
    private char type; //'s' for service, like a mulch bed, 't' for transportation, like gas expenses, and 
    
    /**
     * default WorkOrder constructor
     */
    public WorkOrder(){
        
    }   
}
