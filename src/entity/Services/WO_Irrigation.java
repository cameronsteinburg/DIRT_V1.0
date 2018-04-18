package entity.Services;

import entity.WorkOrder;

/**
 *
 * @author 734972
 */
public class WO_Irrigation extends WorkOrder{

    private double estThreeQuarterLine;
    private double estHoseBibs;
    private double estOffValves;
    private double estRotaryHeads;
    private double estSprayHaeds;
    private double estDripLine;
    private double estDripEmitter;
    private double estTimerControl;
    private double estControlWire;
    private double estValveBox;
    private double estControlValve;
    
    private double actThreeQuarterLine;
    private double actHoseBibs;
    private double actOffValves;
    private double actRotaryHeads;
    private double actSprayHaeds;
    private double actDripLine;
    private double actDripEmitter;
    private double actTimerControl;
    private double actControlWire;
    private double actValveBox;
    private double actControlValve;
    
    public WO_Irrigation(boolean isActive){
        this.isActive = isActive;
        this.name = "Irrigation";
    }

    public double getEstThreeQuarterLine() {
        return estThreeQuarterLine;
    }

    public void setEstThreeQuarterLine(double estThreeQuarterLine) {
        this.estThreeQuarterLine = estThreeQuarterLine;
    }

    public double getEstHoseBibs() {
        return estHoseBibs;
    }

    public void setEstHoseBibs(double estHoseBibs) {
        this.estHoseBibs = estHoseBibs;
    }

    public double getEstOffValves() {
        return estOffValves;
    }

    public void setEstOffValves(double estOffValves) {
        this.estOffValves = estOffValves;
    }

    public double getEstRotaryHeads() {
        return estRotaryHeads;
    }

    public void setEstRotaryHeads(double estRotaryHeads) {
        this.estRotaryHeads = estRotaryHeads;
    }

    public double getEstSprayHaeds() {
        return estSprayHaeds;
    }

    public void setEstSprayHaeds(double estSprayHaeds) {
        this.estSprayHaeds = estSprayHaeds;
    }

    public double getEstDripLine() {
        return estDripLine;
    }

    public void setEstDripLine(double estDripLine) {
        this.estDripLine = estDripLine;
    }

    public double getEstDripEmitter() {
        return estDripEmitter;
    }

    public void setEstDripEmitter(double estDripEmitter) {
        this.estDripEmitter = estDripEmitter;
    }

    public double getEstTimerControl() {
        return estTimerControl;
    }

    public void setEstTimerControl(double estTimerControl) {
        this.estTimerControl = estTimerControl;
    }

    public double getEstControlWire() {
        return estControlWire;
    }

    public void setEstControlWire(double estControlWire) {
        this.estControlWire = estControlWire;
    }

    public double getEstValveBox() {
        return estValveBox;
    }

    public void setEstValveBox(double estValveBox) {
        this.estValveBox = estValveBox;
    }

    public double getEstControlValve() {
        return estControlValve;
    }

    public void setEstControlValve(double estControlValve) {
        this.estControlValve = estControlValve;
    }

    public double getActThreeQuarterLine() {
        return actThreeQuarterLine;
    }

    public void setActThreeQuarterLine(double actThreeQuarterLine) {
        this.actThreeQuarterLine = actThreeQuarterLine;
    }

    public double getActHoseBibs() {
        return actHoseBibs;
    }

    public void setActHoseBibs(double actHoseBibs) {
        this.actHoseBibs = actHoseBibs;
    }

    public double getActOffValves() {
        return actOffValves;
    }

    public void setActOffValves(double actOffValves) {
        this.actOffValves = actOffValves;
    }

    public double getActRotaryHeads() {
        return actRotaryHeads;
    }

    public void setActRotaryHeads(double actRotaryHeads) {
        this.actRotaryHeads = actRotaryHeads;
    }

    public double getActSprayHaeds() {
        return actSprayHaeds;
    }

    public void setActSprayHaeds(double actSprayHaeds) {
        this.actSprayHaeds = actSprayHaeds;
    }

    public double getActDripLine() {
        return actDripLine;
    }

    public void setActDripLine(double actDripLine) {
        this.actDripLine = actDripLine;
    }

    public double getActDripEmitter() {
        return actDripEmitter;
    }

    public void setActDripEmitter(double actDripEmitter) {
        this.actDripEmitter = actDripEmitter;
    }

    public double getActTimerControl() {
        return actTimerControl;
    }

    public void setActTimerControl(double actTimerControl) {
        this.actTimerControl = actTimerControl;
    }

    public double getActControlWire() {
        return actControlWire;
    }

    public void setActControlWire(double actControlWire) {
        this.actControlWire = actControlWire;
    }

    public double getActValveBox() {
        return actValveBox;
    }

    public void setActValveBox(double actValveBox) {
        this.actValveBox = actValveBox;
    }

    public double getActControlValve() {
        return actControlValve;
    }

    public void setActControlValve(double actControlValve) {
        this.actControlValve = actControlValve;
    }
    
    
    
}