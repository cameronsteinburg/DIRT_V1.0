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

}
