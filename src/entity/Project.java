/**
 *
 * 
 * This class represents the project entity and communicates with the persistence layer
 */
package entity;

import entity.WO.WorkOrder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project {
    
    //* nothing gets saved to db until the user pucnhes in all the info to get the the quote production
    //once they have the number they will be asked to save this new project or to throw it away
    
    //details
    private String projectName; //a name user will give to easily identify their project from others NOT NULL
    private String projectDescription; //optional description for users personal reference on project
    private Client client; //user has to make a client to assign to this project beforehand NOT NULL 
    private ArrayList<String> projectLog;// notes user can add overtime as project progresses
    private String siteAddress; //the site of the project
    private char status; //false if the user has deleted this entity, true if he hasn't NOT NULL
    
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
    
    //QUICK MATHS
    private double estimatedShoppingCost; //estimated materials cost for quote NOT NULL
    private double estimatedDeliveryCost; //estimated cost of transportation for quote NOT NULL
    private double estimatedLabourCost; //estimated cost of labour for quote NOT NULL
    private double estimatedProfit; //estimated amount in user's pocket upon completion NOT NULL
   
    private double actualShoppingCost; //final cost of materials for bill at project close
    private double actualDeliveryCost; // final cost of transportation for bill at project close
    private double actualLabourCost; //final cost of labour for bill at project close
    private double actualProfit; //final cost going into user's pocket at project close
    
    //project expense outliers
    private double allowanceCost; //wiggle room assigned for unexpected expenses
    private double extraneousExpenses; //expenses beyond budget during quote period
    
    //the actual quote of project bill
    private double quote; //NOT NULL
    
    //the actual bill
    private double actualCost; //the bottom line at end of project for what the client paid in the end

    /**
     * default Project constructor
     */
    public Project(){
    }
    
    /**
     * 
     * @param name
     * @param desc 
     */
    public Project(String name, Date prelim, Date estEnd, Client client){ //for new project use case, minimum
         
        this.projectLog = new ArrayList<String>();
        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.client = client;
    }
    
    public Project(String name, Date prelim, Date estEnd, Client client, String notes){ 
         
        this.projectLog = new ArrayList<String>();
        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.client = client;
        this.projectLog.add(notes);
    }
    
    public Project(String siteAddress, String name, Date prelim, Date estEnd, Client client){ 
         
        this.projectLog = new ArrayList<String>();
        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.client = client;
        this.siteAddress = siteAddress;
    }
    
    public Project(String name, Date prelim, Date estEnd, Client client, String address, String notes){    
        
        this.projectLog = new ArrayList<String>();
        this.projectName = name;
        this.prelimStartDate = prelim;
        this.estimatedEndDate = estEnd;
        this.client = client;
        this.siteAddress = address;
        this.projectLog.add(notes);
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
    public String getProjectDescription() {
        return projectDescription;
    }

    /**
     * 
     * @param projectDescription 
     */
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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
    }

    /**
     * 
     * @return 
     */
    public ArrayList<String> getProjectLog() {
        return projectLog;
    }

    /**
     * 
     * @param projectLog 
     */
    public void setProjectLog(ArrayList<String> projectLog) {
        this.projectLog = projectLog;
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
    public char getStatus() {
        return status;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * 
     * @return 
     */
    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    /**
     * 
     * @param workOrders 
     */
    public void setWorkOrders(List<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    /**
     * 
     * @return 
     */
    public List<Labourer> getLaborers() {
        return labourers;
    }

    /**
     * 
     * @param laborers 
     */
    public void setLaborers(List<Labourer> laborers) {
        this.labourers = laborers;
    }

    /**
     * 
     * @return 
     */
    public Date getPrelimStartDate() {
        return prelimStartDate;
    }

    /**
     * 
     * @param prelimStartDate 
     */
    public void setPrelimStartDate(Date prelimStartDate) {
        this.prelimStartDate = prelimStartDate;
    }

    /**
     * 
     * @return 
     */
    public Date getActualStartDate() {
        return actualStartDate;
    }

    /**
     * 
     * @param actualStartDate 
     */
    public void setActualStartDate(Date actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    /**
     * 
     * @return 
     */
    public Date getEstimatedEndDate() {
        return estimatedEndDate;
    }

    /**
     * 
     * @param estimatedEndDate 
     */
    public void setEstimatedEndDate(Date estimatedEndDate) {
        this.estimatedEndDate = estimatedEndDate;
    }

    /**
     * 
     * @return 
     */
    public Date getActualEndDate() {
        return actualEndDate;
    }

    /**
     * 
     * @param actualEndDate 
     */
    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
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
    public double getEstimatedShoppingCost() {
        return estimatedShoppingCost;
    }

    /**
     * 
     * @param estimatedShoppingCost 
     */
    public void setEstimatedShoppingCost(double estimatedShoppingCost) {
        this.estimatedShoppingCost = estimatedShoppingCost;
    }

    /**
     * 
     * @return 
     */
    public double getEstimatedLabourCost() {
        return estimatedLabourCost;
    }

    /**
     * 
     * @param estimatedLabourCost 
     */
    public void setEstimatedLabourCost(double estimatedLabourCost) {
        this.estimatedLabourCost = estimatedLabourCost;
    }

    /**
     * 
     * @return 
     */
    public double getEstimatedDeliveryCost() {
        return estimatedDeliveryCost;
    }

    /**
     * 
     * @param estimatedDeliveryCost 
     */
    public void setEstimatedDeliveryCost(double estimatedDeliveryCost) {
        this.estimatedDeliveryCost = estimatedDeliveryCost;
    }

    /**
     * 
     * @return 
     */
    public double getEstimatedProfit() {
        return estimatedProfit;
    }

    /**
     * 
     * @param estimatedProfit 
     */
    public void setEstimatedProfit(double estimatedProfit) {
        this.estimatedProfit = estimatedProfit;
    }

    /**
     * 
     * @return 
     */
    public double getActualShoppingCost() {
        return actualShoppingCost;
    }

    /**
     * 
     * @param actualShoppingCost 
     */
    public void setActualShoppingCost(double actualShoppingCost) {
        this.actualShoppingCost = actualShoppingCost;
    }

    /**
     * 
     * @return 
     */
    public double getActualDeliveryCost() {
        return actualDeliveryCost;
    }

    /**
     * 
     * @param actualDeliveryCost 
     */
    public void setActualDeliveryCost(double actualDeliveryCost) {
        this.actualDeliveryCost = actualDeliveryCost;
    }

    /**
     * 
     * @return 
     */
    public double getActualProfit() {
        return actualProfit;
    }

    /**
     * 
     * @param actualProfit 
     */
    public void setActualProfit(double actualProfit) {
        this.actualProfit = actualProfit;
    }

    /**
     * 
     * @return 
     */
    public double getActualLabourCost() {
        return actualLabourCost;
    }

    /**
     * 
     * @param actualLabourCost 
     */
    public void setActualLabourCost(double actualLabourCost) {
        this.actualLabourCost = actualLabourCost;
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
}
