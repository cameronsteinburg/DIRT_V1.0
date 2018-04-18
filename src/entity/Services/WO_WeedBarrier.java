package entity.Services;

import entity.WorkOrder;

/**
 *
 * @author 734972
 */
public class WO_WeedBarrier extends WorkOrder{
    
    private double estSQFT;
    private double estLayers;
    private double estReqSQFT;
    private double estHours;
    private double estStaples;
    private double estStaplesSupply;
    private double estBarrierSupply;
    private double estLabour;
    
    private double actSQFT;
    private double actLayers;
    private double actReqSQFT;
    private double actHours;
    private double actStaples;
    private double actStaplesSupply;
    private double actBarrierSupply;
    private double actLabour;
    
    public WO_WeedBarrier (boolean isActive){
        this.isActive = isActive;
        this.name = "Weed Barrier";
    }

    public double getEstSQFT() {
        return estSQFT;
    }

    public void setEstSQFT(double estSQFT) {
        this.estSQFT = estSQFT;
    }

    public double getEstLayers() {
        return estLayers;
    }

    public void setEstLayers(double estLayers) {
        this.estLayers = estLayers;
    }

    public double getEstReqSQFT() {
        return estReqSQFT;
    }

    public void setEstReqSQFT(double estReqSQFT) {
        this.estReqSQFT = estReqSQFT;
    }

    public double getEstHours() {
        return estHours;
    }

    public void setEstHours(double estHours) {
        this.estHours = estHours;
    }

    public double getEstStaples() {
        return estStaples;
    }

    public void setEstStaples(double estStaples) {
        this.estStaples = estStaples;
    }

    public double getEstStaplesSupply() {
        return estStaplesSupply;
    }

    public void setEstStaplesSupply(double estStaplesSupply) {
        this.estStaplesSupply = estStaplesSupply;
    }

    public double getEstBarrierSupply() {
        return estBarrierSupply;
    }

    public void setEstBarrierSupply(double estBarrierSupply) {
        this.estBarrierSupply = estBarrierSupply;
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

    public double getActLayers() {
        return actLayers;
    }

    public void setActLayers(double actLayers) {
        this.actLayers = actLayers;
    }

    public double getActReqSQFT() {
        return actReqSQFT;
    }

    public void setActReqSQFT(double actReqSQFT) {
        this.actReqSQFT = actReqSQFT;
    }

    public double getActHours() {
        return actHours;
    }

    public void setActHours(double actHours) {
        this.actHours = actHours;
    }

    public double getActStaples() {
        return actStaples;
    }

    public void setActStaples(double actStaples) {
        this.actStaples = actStaples;
    }

    public double getActStaplesSupply() {
        return actStaplesSupply;
    }

    public void setActStaplesSupply(double actStaplesSupply) {
        this.actStaplesSupply = actStaplesSupply;
    }

    public double getActBarrierSupply() {
        return actBarrierSupply;
    }

    public void setActBarrierSupply(double actBarrierSupply) {
        this.actBarrierSupply = actBarrierSupply;
    }

    public double getActLabour() {
        return actLabour;
    }

    public void setActLabour(double actLabour) {
        this.actLabour = actLabour;
    }
    
    
    
}
