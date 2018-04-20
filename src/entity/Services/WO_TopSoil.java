package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Top Soil service entity
 */
public class WO_TopSoil extends WorkOrder{
    
    private double estSQFT;
    private double estDepth;
    private double estReqYards;
    private double estSupplyCost;
    private double estManHours;
    private double estInstall;
    
    private double actSQFT;
    private double actDepth;
    private double actReqYards;
    private double actSupplyCost;
    private double actManHours;
    private double actInstall;

    /**
     * Default Constructor
     * @param isActive
     */
    public WO_TopSoil(boolean isActive){
        this.isActive = isActive;
        this.name = "Top Soil";
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
    public double getEstSupplyCost() {
        return estSupplyCost;
    }

    /**
     *
     * @param estSupplyCost
     */
    public void setEstSupplyCost(double estSupplyCost) {
        this.estSupplyCost = estSupplyCost;
    }

    /**
     *
     * @return
     */
    public double getEstManHours() {
        return estManHours;
    }

    /**
     *
     * @param estManHours
     */
    public void setEstManHours(double estManHours) {
        this.estManHours = estManHours;
    }

    /**
     *
     * @return
     */
    public double getEstInstall() {
        return estInstall;
    }

    /**
     *
     * @param estInstall
     */
    public void setEstInstall(double estInstall) {
        this.estInstall = estInstall;
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
    public double getActSupplyCost() {
        return actSupplyCost;
    }

    /**
     *
     * @param actSupplyCost
     */
    public void setActSupplyCost(double actSupplyCost) {
        this.actSupplyCost = actSupplyCost;
    }

    /**
     *
     * @return
     */
    public double getActManHours() {
        return actManHours;
    }

    /**
     *
     * @param actManHours
     */
    public void setActManHours(double actManHours) {
        this.actManHours = actManHours;
    }

    /**
     *
     * @return
     */
    public double getActInstall() {
        return actInstall;
    }

    /**
     *
     * @param actInstall
     */
    public void setActInstall(double actInstall) {
        this.actInstall = actInstall;
    }
    
    
    
}
