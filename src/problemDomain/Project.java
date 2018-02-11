/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemDomain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class represents the project entity and communicates with the persistence layer
 *
 */
public class Project {
    
    private String projectDescription;
    private Client client;
    private ArrayList<String> projectLog;
    private Date startDate;
    private Date estimatedEndDate;
    private double clientOwing;
    private boolean clientPaid;
    private List<WorkOrder> workOrders;
    private List<Labourer> laborers;
    private double estimatedShoppingCost;
    private double estimatedLabourCost;
    private double estimatedDeliveryCost;
    private double allowanceCost;
    private double actualShoppingCost;
    private double actualDeliveryCost;
    private double extraneousExpenses;
    private double estimatedProfit;
    private double actualProfit;
    private Date actualEndDate;
    private double actualLabourCost;
    private double quote;
    private String address;
    private char status;
    
    /**
     * default Project constructor
     */
    public Project(){
        
    }
 
   
    /**
     * sends itself to JDBCCommands to be persisted into quickbooks
     * @param project to be exported
     * @return true if no errors occur
     */
    public boolean export(Project project){
        return false;
        
    }
    /**
     * Sends itself to JDBCCommands to be deleted from the MySQL database
     * @return true if no errors occur
     */
    public boolean deleteSQL(){
        return false;
        
    }
    
    /**
     * Creates a new WorkOrder object and adds it to the workOrders List
     * @param type the type of WorkOrder to be created
     * @return the created WorkOrder
     */
    public WorkOrder createWorkorder(char type){
        return null;
        
    }
    
}
