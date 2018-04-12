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
public class WO_Sod extends WorkOrder{
    
    private double estSQFT;
    private double estManHours;
    private double estInstallRate;
    
    private double actSQFT;
    private double actManHours;
    private double actInstallRate;  
    
    /**
     * 
     */
    public WO_Sod() {

    }
    
    /**
     * 
     * @param estSQFT
     * @param estManHours
     * @param estInstallRate
     * @param actSQFT
     * @param actManHours
     * @param actInstallRate 
     */
    public WO_Sod(double estSQFT, double estManHours, double estInstallRate, double actSQFT, double actManHours, double actInstallRate) {
        this.estSQFT = estSQFT;
        this.estManHours = estManHours;
        this.estInstallRate = estInstallRate;
        this.actSQFT = actSQFT;
        this.actManHours = actManHours;
        this.actInstallRate = actInstallRate;
        this.isActive = isActive;
    }    
    
    public double getEstSQFT() {
        return estSQFT;
    }

    public void setEstSQFT(double estSQFT) {
        this.estSQFT = estSQFT;
    }

    public double getEstManHours() {
        return estManHours;
    }

    public void setEstManHours(double estManHours) {
        this.estManHours = estManHours;
    }

    public double getEstInstallRate() {
        return estInstallRate;
    }

    public void setEstInstallRate(double estInstallRate) {
        this.estInstallRate = estInstallRate;
    }

    public double getActSQFT() {
        return actSQFT;
    }

    public void setActSQFT(double actSQFT) {
        this.actSQFT = actSQFT;
    }

    public double getActManHours() {
        return actManHours;
    }

    public void setActManHours(double actManHours) {
        this.actManHours = actManHours;
    }

    public double getActInstallRate() {
        return actInstallRate;
    }

    public void setActInstallRate(double actInstallRate) {
        this.actInstallRate = actInstallRate;
    }
    
    
}
