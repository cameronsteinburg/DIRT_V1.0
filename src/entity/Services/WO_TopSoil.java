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

    
    public WO_TopSoil(boolean isActive){
        this.isActive = isActive;
        this.name = "Top Soil";
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

    public double getEstInstall() {
        return estInstall;
    }

    public void setEstInstall(double estInstall) {
        this.estInstall = estInstall;
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

    public double getActInstall() {
        return actInstall;
    }

    public void setActInstall(double actInstall) {
        this.actInstall = actInstall;
    }
    
    
    
}
