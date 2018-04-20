package entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents the project entity and communicates with the persistence layer
 */
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
    private LocalDate startDate; //date entered by user when first making new project, used for quote calculation NOT NULL
    private LocalDate endDate; //estimated date of project end used in new project to calculate initial quote NOT NULL

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
     * For use in CreateProjectGUI_1
     * @param name
     * @param startDate
     * @param endDate
     * @param description
     * @param address
     * @param isActive 
     */
    public Project(String name, LocalDate startDate, LocalDate endDate, String description, String address, boolean isActive) {

        this.projectName = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isActive = isActive;
        this.siteAddress = address;
    }

    /**
     * For use in JDBCCommands
     * @param projectName
     * @param startDate
     * @param endDate
     * @param description
     * @param isActive
     */
    //for tables
    public Project(String projectName, LocalDate startDate, LocalDate endDate, String description, boolean isActive) {

        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isActive = isActive;
    }

    /**
     * For use in JDBCCommands
     * @param newName
     * @param newAdd
     * @param newNotes
     * @param newStart
     * @param newEnd
     * @param chosen
     * @param labs
     * @param woList
     * @param isActive 
     */
    //for updating project
    public Project(String newName, String newAdd, String newNotes, LocalDate newStart, LocalDate newEnd, Client chosen, ArrayList<Labourer> labs, ArrayList<WorkOrder> woList, boolean isActive) {
       
        this.projectName = newName;
        this.siteAddress = newAdd;
        this.description = newNotes;
        this.startDate = newStart;
        this.endDate = newEnd;
        this.labourers = labs;
        this.workOrders = woList;
        this.client = chosen;
        this.isActive = isActive;
    }
    
    /**
     * 
     * @param val 
     */
    public void setCompleted(boolean val){
        this.completed = val;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getCompleted(){
        return this.completed;
    }

    /**
     * 
     * @return 
     */
    public String getClientName() {
        return this.clientName;
    }

    /**
     * 
     * @param name 
     */
    public void setClientName(String name) {
        this.clientName = name;
    }

    /**
     * 
     * @return 
     */
    public int getProjectNum() {
        return projectNum;
    }

    /**
     * 
     * @param projectNum 
     */
    public void setProjectNum(int projectNum) {
        this.projectNum = projectNum;
    }

    /**
     * 
     * @return 
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 
     * @param projectName 
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return 
     */
    public String getSiteAddress() {
        return siteAddress;
    }

    /**
     * 
     * @param siteAddress 
     */
    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    /**
     * 
     * @return 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * 
     * @param isActive 
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    /**
     * 
     * @param workOrders 
     */
    public void setWorkOrders(ArrayList<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<Labourer> getLabourers() {
        return labourers;
    }

    /**
     * 
     * @param labourers 
     */
    public void setLabourers(ArrayList<Labourer> labourers) {
        this.labourers = labourers;
    }

    /**
     * 
     * @return 
     */
    public Client getClient() {
        return client;
    }

    /**
     * 
     * @param client 
     */
    public void setClient(Client client) {
        this.client = client;

        if (client != null) {
            this.clientName = client.getClientFirstName() + " " + client.getClientLastName();
        }
    }

    /**
     * 
     * @return 
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate 
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return 
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate 
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @return 
     */
    public double getClientOwing() {
        return clientOwing;
    }
    
    /**
     * 
     * @param clientOwing 
     */
    public void setClientOwing(double clientOwing) {
        this.clientOwing = clientOwing;
    }

    /**
     * 
     * @return 
     */
    public boolean isClientPaid() {
        return clientPaid;
    }

    /**
     * 
     * @param clientPaid 
     */
    public void setClientPaid(boolean clientPaid) {
        this.clientPaid = clientPaid;
    }

    /**
     * 
     * @return 
     */
    public double getAllowanceCost() {
        return allowanceCost;
    }

    /**
     * 
     * @param allowanceCost 
     */
    public void setAllowanceCost(double allowanceCost) {
        this.allowanceCost = allowanceCost;
    }

    /**
     * 
     * @return 
     */
    public double getExtraneousExpenses() {
        return extraneousExpenses;
    }

    /**
     * 
     * @param extraneousExpenses 
     */
    public void setExtraneousExpenses(double extraneousExpenses) {
        this.extraneousExpenses = extraneousExpenses;
    }

    /**
     * 
     * @return 
     */
    public double getQuote() {
        return quote;
    }

    /**
     * 
     * @param quote 
     */
    public void setQuote(double quote) {
        this.quote = quote;
    }

    /**
     * 
     * @return 
     */
    public double getActualCost() {
        return actualCost;
    }

    /**
     * 
     * @param actualCost 
     */
    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }
}
