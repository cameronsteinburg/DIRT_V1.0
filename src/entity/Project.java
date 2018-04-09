/**
 *
 *
 * This class represents the project entity and communicates with the persistence layer
 */
package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {

    //* nothing gets saved to db until the user pucnhes in all the info to get the the quote production
    //once they have the number they will be asked to save this new project or to throw it away
    
    private int projectNum; //defined by DB
    
    //details
    private String projectName; //a name user will give to easily identify their project from others NOT NULL
    private String projectDescription; //optional description for users personal reference on project
    private Client client; //user has to make a client to assign to this project beforehand NOT NULL 
    private String siteAddress; //the site of the project
    private boolean isActive; //false if the user has deleted this entity, true if he hasn't NOT NULL
    private String dateConstructed; //date project was made

    //dependent entitites
    private List<WorkOrder> workOrders; //anything that costs User money
    private List<Labourer> labourers; //User's employees he can assign to projects 

    //dates
    private Date prelimStartDate; //date entered by user when first making new peoject, used for quote calculation NOT NULL
    private Date actualStartDate; //that actual date things got rolling, different from date used for quote
    private Date estimatedEndDate; //estimated date of project end used in new project to calculate initial quote NOT NULL
    private Date actualEndDate; //the date the project actually ended

    //track clients payments
    private double clientOwing; //what client still owes
    private boolean clientPaid; //the amount the client has already paid 

    //project expense outliers
    private double allowanceCost; //wiggle room assigned for unexpected expenses
    private double extraneousExpenses; //expenses beyond budget during quote period

    //the actual quote of project bill
    private double quote; //NOT NULL

    //the actual bill
    private double actualCost; //the bottom line at end of project for what the client paid in the end

    /**
     *
     * @param name
     */
    public Project(String name) {

        this.projectName = name;
    }

    /**
     *
     * @param name
     * @param prelim
     * @param estEnd
     * @param isActive
     */
    public Project(String name, Date prelim, Date estEnd, boolean isActive) { //for new project use case, minimum

        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.isActive = isActive;
    }

    /**
     *
     * @param name
     * @param prelim
     * @param estEnd
     * @param notes
     * @param isActive
     */
    public Project(String name, Date prelim, Date estEnd, String notes, boolean isActive) {

        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.projectDescription = notes;
        this.isActive = isActive;
    }

    /**
     *
     * @param siteAddress
     * @param name
     * @param prelim
     * @param estEnd
     * @param isActive
     */
    public Project(String siteAddress, String name, Date prelim, Date estEnd, boolean isActive) {

        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.siteAddress = siteAddress;
        this.isActive = isActive;
    }

    /**
     *
     * @param siteAddress
     * @param name
     * @param isActive
     */
    public Project(String siteAddress, String name, boolean isActive) {

        this.projectName = name;
        this.siteAddress = siteAddress;
        this.isActive = isActive;
    }

 /**
  * 
  * @param name
  * @param prelim
  * @param estEnd
  * @param address
  * @param notes
  * @param isActive 
  */
    public Project(String name, Date prelim, Date estEnd, String address, String notes, boolean isActive) {

        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.siteAddress = address;
        this.isActive = isActive;
    }
    
    /**
     * @Matthew
     * 
     * @param projectName
     * @param projectDescription
     * @param client
     * @param siteAddress
     * @param isActive
     * @param dateConstructed
     * @param workOrders
     * @param labourers
     * @param prelimStartDate
     * @param actualStartDate
     * @param estimatedEndDate
     * @param actualEndDate
     * @param clientOwing
     * @param clientPaid
     * @param allowanceCost
     * @param extraneousExpenses
     * @param quote
     * @param actualCost 
     */
    public Project(String projectName, String projectDescription, Client client, String siteAddress, boolean isActive, String dateConstructed, List<WorkOrder> workOrders, List<Labourer> labourers, Date prelimStartDate, Date actualStartDate, Date estimatedEndDate, Date actualEndDate, double clientOwing, boolean clientPaid, double allowanceCost, double extraneousExpenses, double quote, double actualCost, int id) {
       
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.client = client;
        this.siteAddress = siteAddress;
        this.isActive = isActive;
        this.dateConstructed = dateConstructed;
        this.workOrders = workOrders; //get fks from objects
        this.labourers = labourers; //get fks from objects
        this.prelimStartDate = prelimStartDate;
        this.actualStartDate = actualStartDate;
        this.estimatedEndDate = estimatedEndDate;
        this.actualEndDate = actualEndDate;
        this.clientOwing = clientOwing;
        this.clientPaid = clientPaid;
        this.allowanceCost = allowanceCost;
        this.extraneousExpenses = extraneousExpenses;
        this.quote = quote;
        this.actualCost = actualCost;
        this.projectNum = id; //pk, only not null
    }
    
    

}
