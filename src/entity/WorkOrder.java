package entity;

import java.util.ArrayList;

/**
 * This class represents the work order entity and communicates with the persistence layer
 *
 */
public class WorkOrder {
   
    private ArrayList<WorkOrderItem> expenses;
    private String description;
    private double total;
    private char id;
    private char type; //'s' for service, like a mulch bed, 't' for transportation, like gas expenses, and 
    
 
}
