/**
 * This class represents the project entity and communicates with the persistence layer
 *
 */
package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public double getActualCost() {
        return actualCost;
    }

    public void setActualCost(double actualCost) {
        this.actualCost = actualCost;
    }
    

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<String> getProjectLog() {
        return projectLog;
    }

    public void setProjectLog(ArrayList<String> projectLog) {
        this.projectLog = projectLog;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }

    public List<Labourer> getLaborers() {
        return laborers;
    }

    public void setLaborers(List<Labourer> laborers) {
        this.laborers = laborers;
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

    public double getEstimatedShoppingCost() {
        return estimatedShoppingCost;
    }

    public void setEstimatedShoppingCost(double estimatedShoppingCost) {
        this.estimatedShoppingCost = estimatedShoppingCost;
    }

    public double getEstimatedLabourCost() {
        return estimatedLabourCost;
    }

    public void setEstimatedLabourCost(double estimatedLabourCost) {
        this.estimatedLabourCost = estimatedLabourCost;
    }

    public double getEstimatedDeliveryCost() {
        return estimatedDeliveryCost;
    }

    public void setEstimatedDeliveryCost(double estimatedDeliveryCost) {
        this.estimatedDeliveryCost = estimatedDeliveryCost;
    }

    public double getEstimatedProfit() {
        return estimatedProfit;
    }

    public void setEstimatedProfit(double estimatedProfit) {
        this.estimatedProfit = estimatedProfit;
    }

    public double getActualShoppingCost() {
        return actualShoppingCost;
    }

    public void setActualShoppingCost(double actualShoppingCost) {
        this.actualShoppingCost = actualShoppingCost;
    }

    public double getActualDeliveryCost() {
        return actualDeliveryCost;
    }

    public void setActualDeliveryCost(double actualDeliveryCost) {
        this.actualDeliveryCost = actualDeliveryCost;
    }

    public double getActualProfit() {
        return actualProfit;
    }

    public void setActualProfit(double actualProfit) {
        this.actualProfit = actualProfit;
    }

    public double getActualLabourCost() {
        return actualLabourCost;
    }

    public void setActualLabourCost(double actualLabourCost) {
        this.actualLabourCost = actualLabourCost;
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
