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
    private String description; //optional description for users personal reference on project
    private Client client; //user has to make a client to assign to this project beforehand NOT NULL 
    private String siteAddress; //the site of the project
    private boolean isActive; //false if the user has deleted this entity, true if he hasn't NOT NULL
    private String dateConstructed; //date project was made

    //dependent entitites
    private ArrayList<WorkOrder> workOrders; //anything that costs User money
    private ArrayList<Labourer> labourers; //User's employees he can assign to projects 

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
     */
    public Project(){
    }
    
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
        this.description = notes;
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
    public Project(String projectName, String projectDescription, Client client, String siteAddress, boolean isActive, String dateConstructed, ArrayList<WorkOrder> workOrders, ArrayList<Labourer> labourers, Date prelimStartDate, Date actualStartDate, Date estimatedEndDate, Date actualEndDate, double clientOwing, boolean clientPaid, double allowanceCost, double extraneousExpenses, double quote, double actualCost, int id) {
       
        this.projectName = projectName;
        this.description = projectDescription;
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
    
    public void addOrder(WorkOrder order){
        workOrders.add(order);
    }

    public int getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setProjectDescription(String projectDescription) {
        this.description = projectDescription;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getDateConstructed() {
        return dateConstructed;
    }

    public void setDateConstructed(String dateConstructed) {
        this.dateConstructed = dateConstructed;
    }

    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(ArrayList<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    public List<Labourer> getLabourers() {
        return labourers;
    }

    public void setLabourers(ArrayList<Labourer> labourers) {
        this.labourers = labourers;
    }

    public Date getPrelimStartDate() {
        return prelimStartDate;
    }

    public void setPrelimStartDate(Date prelimStartDate) {
        this.prelimStartDate = prelimStartDate;
    }

    public Date getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public Date getEstimatedEndDate() {
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(Date estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    public Date getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public double getClientOwing() {
        return clientOwing;
    }

    public void setClientOwing(double clientOwing) {
        this.clientOwing = clientOwing;
    }

    public boolean isClientPaid() {
        return clientPaid;
    }

    public void setClientPaid(boolean clientPaid) {
        this.clientPaid = clientPaid;
    }

    public double getAllowanceCost() {
        return allowanceCost;
    }

    public void setAllowanceCost(double allowanceCost) {
        this.allowanceCost = allowanceCost;
    }

    public double getExtraneousExpenses() {
        return extraneousExpenses;
    }

    public void setExtraneousExpenses(double extraneousExpenses) {
        this.extraneousExpenses = extraneousExpenses;
    }

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }
    
    
    
    

}
