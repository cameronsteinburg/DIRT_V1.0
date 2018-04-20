package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Aggregate Bed service entity
 */
public class WO_Bed extends WorkOrder {

    private double estSQFT;
    private double estDepth;
    private double estReqYards;
    private double estHours;
    private double estLabour;
    private double aggCost;
    private double actSQFT;
    private double actDepth;
    private double actReqYards;
    private double actHours;
    private double actLabour;
    private String aggregate;

    /**
     * Default Constructor
     * @param isActive
     */
    public WO_Bed(boolean isActive) {
        this.isActive = isActive;
        this.name = "Bed";
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
    public String getAggregate() {
        return aggregate;
    }

    /**
     *
     * @param aggregate
     */
    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }
    
    /**
     *
     * @return
     */
    public double getAggCost(){
        return this.aggCost; 
    }
    
    /**
     *
     * @param agg
     */
    public void setAggCost(double agg){
        this.aggCost = agg;
    }

}
