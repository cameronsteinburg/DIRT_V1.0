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
     * 
     * @param isActive
     */
    public WO_Bed(boolean isActive) {
        this.isActive = isActive;
        this.name = "Bed";
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
