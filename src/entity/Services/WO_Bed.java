package entity.Services;

import entity.WorkOrder;

/**
 *
 * @author 734972
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
     *
     * @param aggregate
     * @param isActive
     */
    public WO_Bed(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * 
     * @param aggregate
     * @param sqft
     * @param depth
     * @param yards
     * @param hours
     * @param labour
     * @param agg
     * @param isActive 
     */
    public WO_Bed(String aggregate, double sqft, double depth, double yards, double hours, double labour, double agg, boolean isActive) {
        this.aggregate = aggregate;
        this.estDepth = depth;
        this.estSQFT = sqft;
        this.estReqYards = yards;
        this.estHours = hours;
        this.estLabour = labour;
        this.aggCost = agg;
        this.isActive = isActive;
    }

    /**
     * @Matthew
     * 
     * @param estSQFT
     * @param estDepth
     * @param estReqYards
     * @param estHours
     * @param estLabour
     * @param aggCost
     * @param actSQFT
     * @param actDepth
     * @param actReqYards
     * @param actHours
     * @param actLabour
     * @param aggregate 
     * @param isActive
     */
    public WO_Bed(double estSQFT, double estDepth, double estReqYards, double estHours, double estLabour, double aggCost, double actSQFT, double actDepth, double actReqYards, double actHours, double actLabour, String aggregate, boolean isActive) {
        this.estSQFT = estSQFT;
        this.estDepth = estDepth;
        this.estReqYards = estReqYards;
        this.estHours = estHours;
        this.estLabour = estLabour;
        this.aggCost = aggCost;
        this.actSQFT = actSQFT;
        this.actDepth = actDepth;
        this.actReqYards = actReqYards;
        this.actHours = actHours;
        this.actLabour = actLabour;
        this.aggregate = aggregate;
        this.isActive = isActive;
    }
    
    

    public double getEstSQFT() {
        return estSQFT;
    }

    public void setEstSQFT(double estSQFT) {
        this.estSQFT = estSQFT;
    }

    public double getEstDepth() {
        return estDepth;
    }

    public void setEstDepth(double estDepth) {
        this.estDepth = estDepth;
    }

    public double getEstReqYards() {
        return estReqYards;
    }

    public void setEstReqYards(double estReqYards) {
        this.estReqYards = estReqYards;
    }

    public double getEstHours() {
        return estHours;
    }

    public void setEstHours(double estHours) {
        this.estHours = estHours;
    }

    public double getEstLabour() {
        return estLabour;
    }

    public void setEstLabour(double estLabour) {
        this.estLabour = estLabour;
    }

    public double getActSQFT() {
        return actSQFT;
    }

    public void setActSQFT(double actSQFT) {
        this.actSQFT = actSQFT;
    }

    public double getActDepth() {
        return actDepth;
    }

    public void setActDepth(double actDepth) {
        this.actDepth = actDepth;
    }

    public double getActReqYards() {
        return actReqYards;
    }

    public void setActReqYards(double actReqYards) {
        this.actReqYards = actReqYards;
    }

    public double getActHours() {
        return actHours;
    }

    public void setActHours(double actHours) {
        this.actHours = actHours;
    }

    public double getActLabour() {
        return actLabour;
    }

    public void setActLabour(double actLabour) {
        this.actLabour = actLabour;
    }

    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }
    
    public double getAggCost(){
        return this.aggCost; 
    }
    
    public void setAggCost(double agg){
        this.aggCost = agg;
    }

}
