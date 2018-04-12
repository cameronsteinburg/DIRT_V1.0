/**
 *
 *
 * This class represents the project entity and communicates with the persistence layer
 */
package entity;

import java.util.ArrayList;
import java.util.Date;

public class Project {

    //* nothing gets saved to db until the user pucnhes in all the info to get the the quote production
    //once they have the number they will be asked to save this new project or to throw it away
    private int projectNum; //defined by DB

    //details
    private String projectName; //a name user will give to easily identify their project from others NOT NULL
    private String description; //optional description for users personal reference on project
    private String siteAddress; //the site of the project
    private boolean isActive; //false if the user has deleted this entity, true if he hasn't NOT NULL

    //dependent entitites
    private ArrayList<WorkOrder> workOrders = new ArrayList(); //anything that costs User money
    private ArrayList<Labourer> labourers = new ArrayList(); //User's employees he can assign to projects 
    private Client client; //user has to make a client to assign to this project beforehand

    //dates
    private Date startDate; //date entered by user when first making new project, used for quote calculation NOT NULL
    private Date endDate; //estimated date of project end used in new project to calculate initial quote NOT NULL

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

    private String clientName; //for use with TableView

    private boolean completed;

    /**
     *
     * @param isActive
     */
    public Project(boolean isActive) {
        this.isActive = isActive;
    }

    //for new project 1
    public Project(String name, Date startDate, Date endDate, String description, String address, boolean isActive) {

        this.projectName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isActive = isActive;
        this.siteAddress = address;
    }

    /**
     *
     * @param projectName
     * @param startDate
     * @param endDate
     * @param description
     * @param isActive
     */
    //for tables
    public Project(String projectName, Date startDate, Date endDate, String description, boolean isActive) {

        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isActive = isActive;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String name) {
        this.clientName = name;
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

    public void setDescription(String description) {
        this.description = description;
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

    public ArrayList<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(ArrayList<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    public ArrayList<Labourer> getLabourers() {
        return labourers;
    }

    public void setLabourers(ArrayList<Labourer> labourers) {
        this.labourers = labourers;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;

        if (client != null) {
            this.clientName = client.getClientFirstName() + " " + client.getClientLastName();
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
