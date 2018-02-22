/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * This class represents the project entity and communicates with the persistence layer
 *
 */
public class Project {
    
    //* nothing gets saved to db until the user pucnhe sin all the info to get the the quote production
    //once they have the number they will be asked to save this new project or to throw it away
    
    //details
    private String projectName; //a name user will give to easily identify their project from others NOT NULL
    private String projectDescription; //optional description for users personal reference on project
    private Client client; //user has to make a client to assign to this project beforehand NOT NULL 
    private ArrayList<String> projectLog;// notes user can add overtime as project progresses
    private String siteAddress; //the site of the project
    private char status; //has the user "deleted" this entity yet NOT NULL
    
    //dependent entitites
    private List<WorkOrder> workOrders; 
    private List<Labourer> laborers;
    
    private Date prelimStartDate; //date entered by user when first making new peoject, used for quote calculation NOT NULL
    private Date actualStartDate; //that actual date things got rolling, different from date used for quote
    private Date estimatedEndDate; //estimated date of project end used in new project to calculate initial quote
    private Date actualEndDate;
    
    //track clients payments
    private double clientOwing;
    private boolean clientPaid;
    
    //for quote math
    private double estimatedShoppingCost;
    private double estimatedLabourCost;
    private double estimatedDeliveryCost;
    private double allowanceCost;
    private double actualShoppingCost;
    private double actualDeliveryCost;
    private double extraneousExpenses;
    private double estimatedProfit;
    private double actualProfit;
    private double actualLabourCost;
    
    //the actual quote
    private double quote; //NOT NULL
    
    
    /**
     * default Project constructor
     */
    public Project(){
    }
    
    public Project(String name, String desc){ //for new project use case
    }
 
   
    /**
     * sends itself to JDBCCommands to be persisted into quickbooks
     * @param project to be exported
     * @return true if no errors occur
     */
    public boolean export(Project project){
        return false;
        
    }   
}
