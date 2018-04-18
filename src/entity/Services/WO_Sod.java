/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.Services;

import entity.WorkOrder;

/**
 *
 * @author 645011
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

    public WO_Sod(boolean isActive) {
        this.isActive = isActive;
        this.name = "Sod";
    }

    public double getEstSQFT() {
        return estSQFT;
    }

    public void setEstSQFT(double estSQFT) {
        this.estSQFT = estSQFT;
    }

    public double getEstSupplyCost() {
        return estSupplyCost;
    }

    public void setEstSupplyCost(double estSupplyCost) {
        this.estSupplyCost = estSupplyCost;
    }

    public double getEstManHours() {
        return estManHours;
    }

    public void setEstManHours(double estManHours) {
        this.estManHours = estManHours;
    }

    public double getEstInstallCost() {
        return estInstallCost;
    }

    public void setEstInstallCost(double estInstallCost) {
        this.estInstallCost = estInstallCost;
    }

    public double getActSQFT() {
        return actSQFT;
    }

    public void setActSQFT(double actSQFT) {
        this.actSQFT = actSQFT;
    }

    public double getActSupplyCost() {
        return actSupplyCost;
    }

    public void setActSupplyCost(double actSupplyCost) {
        this.actSupplyCost = actSupplyCost;
    }

    public double getActManHours() {
        return actManHours;
    }

    public void setActManHours(double actManHours) {
        this.actManHours = actManHours;
    }

    public double getActInstallCost() {
        return actInstallCost;
    }

    public void setActInstallCost(double actInstallCost) {
        this.actInstallCost = actInstallCost;
    }

}
