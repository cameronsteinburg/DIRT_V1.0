package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Excavation By Hand/Skid Steer service
 */
public class WO_Excavation extends WorkOrder {

    private double estSQFT;
    private double estDepth;
    private double estReqYards;
    private double estHours;
    private double estLabour;
    private double estTrucking;
    private double estDisposal;

    private double actSQFT;
    private double actDepth;
    private double actReqYards;
    private double actHours;
    private double actLabour;
    private double actTrucking;
    private double actDisposal;
    
    private char type = 0;
    
    /**
     * Default Constructor
     * @param type
     * @param isActive 
     */
    public WO_Excavation(char type, boolean isActive){
        this.type = type;
        this.isActive = isActive;
        
        if(type == 'h'){
            this.name = "Excavation By Hand";
        } else if(type == 's'){
            this.name = "Excavation BY Skid";
        }
        
        
    }

    /**
     *
     * @return
     */
    public double getEstSQFT() {
        return estSQFT;
    }

    /**
     *
     * @param estSQFT
     */
    public void setEstSQFT(double estSQFT) {
        this.estSQFT = estSQFT;
    }

    /**
     *
     * @return
     */
    public double getEstDepth() {
        return estDepth;
    }

    /**
     *
     * @param estDepth
     */
    public void setEstDepth(double estDepth) {
        this.estDepth = estDepth;
    }

    /**
     *
     * @return
     */
    public double getEstReqYards() {
        return estReqYards;
    }

    /**
     *
     * @param estReqYards
     */
    public void setEstReqYards(double estReqYards) {
        this.estReqYards = estReqYards;
    }

    /**
     *
     * @return
     */
    public double getEstHours() {
        return estHours;
    }

    /**
     *
     * @param estHours
     */
    public void setEstHours(double estHours) {
        this.estHours = estHours;
    }

    /**
     *
     * @return
     */
    public double getEstLabour() {
        return estLabour;
    }

    /**
     *
     * @param estLabour
     */
    public void setEstLabour(double estLabour) {
        this.estLabour = estLabour;
    }

    /**
     *
     * @return
     */
    public double getEstTrucking() {
        return estTrucking;
    }

    /**
     *
     * @param estTrucking
     */
    public void setEstTrucking(double estTrucking) {
        this.estTrucking = estTrucking;
    }

    /**
     *
     * @return
     */
    public double getEstDisposal() {
        return estDisposal;
    }

    /**
     *
     * @param estDisposal
     */
    public void setEstDisposal(double estDisposal) {
        this.estDisposal = estDisposal;
    }

    /**
     *
     * @return
     */
    public double getActSQFT() {
        return actSQFT;
    }

    /**
     *
     * @param actSQFT
     */
    public void setActSQFT(double actSQFT) {
        this.actSQFT = actSQFT;
    }

    /**
     *
     * @return
     */
    public double getActDepth() {
        return actDepth;
    }

    /**
     *
     * @param actDepth
     */
    public void setActDepth(double actDepth) {
        this.actDepth = actDepth;
    }

    /**
     *
     * @return
     */
    public double getActReqYards() {
        return actReqYards;
    }

    /**
     *
     * @param actReqYards
     */
    public void setActReqYards(double actReqYards) {
        this.actReqYards = actReqYards;
    }

    /**
     *
     * @return
     */
    public double getActHours() {
        return actHours;
    }

    /**
     *
     * @param actHours
     */
    public void setActHours(double actHours) {
        this.actHours = actHours;
    }

    /**
     *
     * @return
     */
    public double getActLabour() {
        return actLabour;
    }

    /**
     *
     * @param actLabour
     */
    public void setActLabour(double actLabour) {
        this.actLabour = actLabour;
    }

    /**
     *
     * @return
     */
    public double getActTrucking() {
        return actTrucking;
    }

    /**
     *
     * @param actTrucking
     */
    public void setActTrucking(double actTrucking) {
        this.actTrucking = actTrucking;
    }

    /**
     *
     * @return
     */
    public double getActDisposal() {
        return actDisposal;
    }

    /**
     *
     * @param actDisposal
     */
    public void setActDisposal(double actDisposal) {
        this.actDisposal = actDisposal;
    }

    /**
     *
     * @return
     */
    public char getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(char type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuotedTotal() {
        return quotedTotal;
    }

    public void setQuotedTotal(double quotedTotal) {
        this.quotedTotal = quotedTotal;
    }

    public double getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(double actualTotal) {
        this.actualTotal = actualTotal;
    }

    public String getWoid() {
        return woid;
    }

    public void setWoid(String woid) {
        this.woid = woid;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    
}
