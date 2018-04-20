package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Sod service entity
 */
public class WO_Sod extends WorkOrder {

    private double estSQFT;
    private double estSupplyCost;
    private double estManHours;
    private double estInstallCost;

    private double actSQFT;
    private double actSupplyCost;
    private double actManHours;
    private double actInstallCost;

    /**
     * Default Constructor
     * @param isActive
     */
    public WO_Sod(boolean isActive) {
        this.isActive = isActive;
        this.name = "Sod";
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
    public double getEstInstallCost() {
        return estInstallCost;
    }

    /**
     *
     * @param estInstallCost
     */
    public void setEstInstallCost(double estInstallCost) {
        this.estInstallCost = estInstallCost;
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
    public double getActInstallCost() {
        return actInstallCost;
    }

    /**
     *
     * @param actInstallCost
     */
    public void setActInstallCost(double actInstallCost) {
        this.actInstallCost = actInstallCost;
    }

}
