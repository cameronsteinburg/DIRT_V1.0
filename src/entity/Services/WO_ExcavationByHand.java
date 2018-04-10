package entity.Services;

import entity.WorkOrder;

/**
 *
 * @author 734972
 */
public class WO_ExcavationByHand extends WorkOrder {

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
    
    /**
     * 
     */
    public WO_ExcavationByHand(){
    
    }

    //first creation in java for data entered
    public WO_ExcavationByHand(double total, double estSQFT, double estDepth, double estReqYards, double estHours, double estLabour, double estTrucking, double estDisposal, boolean isActive) {

        this.quotedTotal = total;
        this.estSQFT = estSQFT;
        this.estDepth = estDepth;
        this.estReqYards = estReqYards;
        this.estHours = estHours;
        this.estLabour = estLabour;
        this.estTrucking = estTrucking;
        this.estDisposal = estDisposal;
        this.isActive = isActive;
    }

    //from/to database @Matthew
    public WO_ExcavationByHand(double total, double estSQFT, double estDepth, double estReqYards, double estHours, double estLabour, double estTrucking, double estDisposal, double actSQFT, double actDepth, double actReqYards, double actHours, double actLabour, double actTrucking, double actDisposal, boolean isActive, String proj, String woid) {

        this.quotedTotal = total;
        this.estSQFT = estSQFT;
        this.estDepth = estDepth;
        this.estReqYards = estReqYards;
        this.estHours = estHours;
        this.estLabour = estLabour;
        this.estTrucking = estTrucking;
        this.estDisposal = estDisposal;
        this.actSQFT = actSQFT;
        this.actDepth = actDepth;
        this.actReqYards = actReqYards;
        this.actHours = actHours;
        this.actLabour = actLabour;
        this.actTrucking = actTrucking;
        this.actDisposal = actDisposal;
        this.isActive = isActive;
        this.projectID = proj; //fk
        this.woid = woid; //pk only not null
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

    public double getEstTrucking() {
        return estTrucking;
    }

    public void setEstTrucking(double estTrucking) {
        this.estTrucking = estTrucking;
    }

    public double getEstDisposal() {
        return estDisposal;
    }

    public void setEstDisposal(double estDisposal) {
        this.estDisposal = estDisposal;
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

    public double getActTrucking() {
        return actTrucking;
    }

    public void setActTrucking(double actTrucking) {
        this.actTrucking = actTrucking;
    }

    public double getActDisposal() {
        return actDisposal;
    }

    public void setActDisposal(double actDisposal) {
        this.actDisposal = actDisposal;
    }

}
