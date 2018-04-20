package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Weed Barrier service entity
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
    
    /**
     *
     * @param isActive
     */
    public WO_WeedBarrier (boolean isActive){
        this.isActive = isActive;
        this.name = "Weed Barrier";
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
    public double getEstLayers() {
        return estLayers;
    }

    /**
     *
     * @param estLayers
     */
    public void setEstLayers(double estLayers) {
        this.estLayers = estLayers;
    }

    /**
     *
     * @return
     */
    public double getEstReqSQFT() {
        return estReqSQFT;
    }

    /**
     *
     * @param estReqSQFT
     */
    public void setEstReqSQFT(double estReqSQFT) {
        this.estReqSQFT = estReqSQFT;
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
    public double getEstStaples() {
        return estStaples;
    }

    /**
     *
     * @param estStaples
     */
    public void setEstStaples(double estStaples) {
        this.estStaples = estStaples;
    }

    /**
     *
     * @return
     */
    public double getEstStaplesSupply() {
        return estStaplesSupply;
    }

    /**
     *
     * @param estStaplesSupply
     */
    public void setEstStaplesSupply(double estStaplesSupply) {
        this.estStaplesSupply = estStaplesSupply;
    }

    /**
     *
     * @return
     */
    public double getEstBarrierSupply() {
        return estBarrierSupply;
    }

    /**
     *
     * @param estBarrierSupply
     */
    public void setEstBarrierSupply(double estBarrierSupply) {
        this.estBarrierSupply = estBarrierSupply;
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
    public double getActLayers() {
        return actLayers;
    }

    /**
     *
     * @param actLayers
     */
    public void setActLayers(double actLayers) {
        this.actLayers = actLayers;
    }

    /**
     *
     * @return
     */
    public double getActReqSQFT() {
        return actReqSQFT;
    }

    /**
     *
     * @param actReqSQFT
     */
    public void setActReqSQFT(double actReqSQFT) {
        this.actReqSQFT = actReqSQFT;
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
    public double getActStaples() {
        return actStaples;
    }

    /**
     *
     * @param actStaples
     */
    public void setActStaples(double actStaples) {
        this.actStaples = actStaples;
    }

    /**
     *
     * @return
     */
    public double getActStaplesSupply() {
        return actStaplesSupply;
    }

    /**
     *
     * @param actStaplesSupply
     */
    public void setActStaplesSupply(double actStaplesSupply) {
        this.actStaplesSupply = actStaplesSupply;
    }

    /**
     *
     * @return
     */
    public double getActBarrierSupply() {
        return actBarrierSupply;
    }

    /**
     *
     * @param actBarrierSupply
     */
    public void setActBarrierSupply(double actBarrierSupply) {
        this.actBarrierSupply = actBarrierSupply;
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
    
    
    
}
