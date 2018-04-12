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
    private double estSoilReq;
    private double estSoilSupply;
    private double estManHours;
    private double estSoilInstall;
    private double estDelivery;
    
    private double actSQFT;
    private double actDepth;
    private double actSoilReq;
    private double actSoilSupply;
    private double actManHours;
    private double actSoilInstall;
    private double actDelivery;

    public WO_TopSoil(double estSQFT, double estDepth, double estSoilReq, double estSoilSupply, double estManHours, double estSoilInstall, double estDelivery, double actSQFT, double actDepth, double actSoilReq, double actSoilSupply, double actManHours, double actSoilInstall, double actDelivery) {
        this.estSQFT = estSQFT;
        this.estDepth = estDepth;
        this.estSoilReq = estSoilReq;
        this.estSoilSupply = estSoilSupply;
        this.estManHours = estManHours;
        this.estSoilInstall = estSoilInstall;
        this.estDelivery = estDelivery;
        this.actSQFT = actSQFT;
        this.actDepth = actDepth;
        this.actSoilReq = actSoilReq;
        this.actSoilSupply = actSoilSupply;
        this.actManHours = actManHours;
        this.actSoilInstall = actSoilInstall;
        this.actDelivery = actDelivery;
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

    public double getEstSoilReq() {
        return estSoilReq;
    }

    public void setEstSoilReq(double estSoilReq) {
        this.estSoilReq = estSoilReq;
    }

    public double getEstSoilSupply() {
        return estSoilSupply;
    }

    public void setEstSoilSupply(double estSoilSupply) {
        this.estSoilSupply = estSoilSupply;
    }

    public double getEstManHours() {
        return estManHours;
    }

    public void setEstManHours(double estManHours) {
        this.estManHours = estManHours;
    }

    public double getEstSoilInstall() {
        return estSoilInstall;
    }

    public void setEstSoilInstall(double estSoilInstall) {
        this.estSoilInstall = estSoilInstall;
    }

    public double getEstDelivery() {
        return estDelivery;
    }

    public void setEstDelivery(double estDelivery) {
        this.estDelivery = estDelivery;
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

    public double getActSoilReq() {
        return actSoilReq;
    }

    public void setActSoilReq(double actSoilReq) {
        this.actSoilReq = actSoilReq;
    }

    public double getActSoilSupply() {
        return actSoilSupply;
    }

    public void setActSoilSupply(double actSoilSupply) {
        this.actSoilSupply = actSoilSupply;
    }

    public double getActManHours() {
        return actManHours;
    }

    public void setActManHours(double actManHours) {
        this.actManHours = actManHours;
    }

    public double getActSoilInstall() {
        return actSoilInstall;
    }

    public void setActSoilInstall(double actSoilInstall) {
        this.actSoilInstall = actSoilInstall;
    }

    public double getActDelivery() {
        return actDelivery;
    }

    public void setActDelivery(double actDelivery) {
        this.actDelivery = actDelivery;
    }
    
    
    
}
