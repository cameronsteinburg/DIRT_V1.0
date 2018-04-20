package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Underground Irrigation Service
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
    
    /**
     * Default Constructor
     * @param isActive
     */
    public WO_Irrigation(boolean isActive){
        this.isActive = isActive;
        this.name = "Irrigation";
    }

    /**
     *
     * @return
     */
    public double getEstThreeQuarterLine() {
        return estThreeQuarterLine;
    }

    /**
     *
     * @param estThreeQuarterLine
     */
    public void setEstThreeQuarterLine(double estThreeQuarterLine) {
        this.estThreeQuarterLine = estThreeQuarterLine;
    }

    /**
     *
     * @return
     */
    public double getEstHoseBibs() {
        return estHoseBibs;
    }

    /**
     *
     * @param estHoseBibs
     */
    public void setEstHoseBibs(double estHoseBibs) {
        this.estHoseBibs = estHoseBibs;
    }

    /**
     *
     * @return
     */
    public double getEstOffValves() {
        return estOffValves;
    }

    /**
     *
     * @param estOffValves
     */
    public void setEstOffValves(double estOffValves) {
        this.estOffValves = estOffValves;
    }

    /**
     *
     * @return
     */
    public double getEstRotaryHeads() {
        return estRotaryHeads;
    }

    /**
     *
     * @param estRotaryHeads
     */
    public void setEstRotaryHeads(double estRotaryHeads) {
        this.estRotaryHeads = estRotaryHeads;
    }

    /**
     *
     * @return
     */
    public double getEstSprayHaeds() {
        return estSprayHaeds;
    }

    /**
     *
     * @param estSprayHaeds
     */
    public void setEstSprayHaeds(double estSprayHaeds) {
        this.estSprayHaeds = estSprayHaeds;
    }

    /**
     *
     * @return
     */
    public double getEstDripLine() {
        return estDripLine;
    }

    /**
     *
     * @param estDripLine
     */
    public void setEstDripLine(double estDripLine) {
        this.estDripLine = estDripLine;
    }

    /**
     *
     * @return
     */
    public double getEstDripEmitter() {
        return estDripEmitter;
    }

    /**
     *
     * @param estDripEmitter
     */
    public void setEstDripEmitter(double estDripEmitter) {
        this.estDripEmitter = estDripEmitter;
    }

    /**
     *
     * @return
     */
    public double getEstTimerControl() {
        return estTimerControl;
    }

    /**
     *
     * @param estTimerControl
     */
    public void setEstTimerControl(double estTimerControl) {
        this.estTimerControl = estTimerControl;
    }

    /**
     *
     * @return
     */
    public double getEstControlWire() {
        return estControlWire;
    }

    /**
     *
     * @param estControlWire
     */
    public void setEstControlWire(double estControlWire) {
        this.estControlWire = estControlWire;
    }

    /**
     *
     * @return
     */
    public double getEstValveBox() {
        return estValveBox;
    }

    /**
     *
     * @param estValveBox
     */
    public void setEstValveBox(double estValveBox) {
        this.estValveBox = estValveBox;
    }

    /**
     *
     * @return
     */
    public double getEstControlValve() {
        return estControlValve;
    }

    /**
     *
     * @param estControlValve
     */
    public void setEstControlValve(double estControlValve) {
        this.estControlValve = estControlValve;
    }

    /**
     *
     * @return
     */
    public double getActThreeQuarterLine() {
        return actThreeQuarterLine;
    }

    /**
     *
     * @param actThreeQuarterLine
     */
    public void setActThreeQuarterLine(double actThreeQuarterLine) {
        this.actThreeQuarterLine = actThreeQuarterLine;
    }

    /**
     *
     * @return
     */
    public double getActHoseBibs() {
        return actHoseBibs;
    }

    /**
     *
     * @param actHoseBibs
     */
    public void setActHoseBibs(double actHoseBibs) {
        this.actHoseBibs = actHoseBibs;
    }

    /**
     *
     * @return
     */
    public double getActOffValves() {
        return actOffValves;
    }

    /**
     *
     * @param actOffValves
     */
    public void setActOffValves(double actOffValves) {
        this.actOffValves = actOffValves;
    }

    /**
     *
     * @return
     */
    public double getActRotaryHeads() {
        return actRotaryHeads;
    }

    /**
     *
     * @param actRotaryHeads
     */
    public void setActRotaryHeads(double actRotaryHeads) {
        this.actRotaryHeads = actRotaryHeads;
    }

    /**
     *
     * @return
     */
    public double getActSprayHaeds() {
        return actSprayHaeds;
    }

    /**
     *
     * @param actSprayHaeds
     */
    public void setActSprayHaeds(double actSprayHaeds) {
        this.actSprayHaeds = actSprayHaeds;
    }

    /**
     *
     * @return
     */
    public double getActDripLine() {
        return actDripLine;
    }

    /**
     *
     * @param actDripLine
     */
    public void setActDripLine(double actDripLine) {
        this.actDripLine = actDripLine;
    }

    /**
     *
     * @return
     */
    public double getActDripEmitter() {
        return actDripEmitter;
    }

    /**
     *
     * @param actDripEmitter
     */
    public void setActDripEmitter(double actDripEmitter) {
        this.actDripEmitter = actDripEmitter;
    }

    /**
     *
     * @return
     */
    public double getActTimerControl() {
        return actTimerControl;
    }

    /**
     *
     * @param actTimerControl
     */
    public void setActTimerControl(double actTimerControl) {
        this.actTimerControl = actTimerControl;
    }

    /**
     *
     * @return
     */
    public double getActControlWire() {
        return actControlWire;
    }

    /**
     *
     * @param actControlWire
     */
    public void setActControlWire(double actControlWire) {
        this.actControlWire = actControlWire;
    }

    /**
     *
     * @return
     */
    public double getActValveBox() {
        return actValveBox;
    }

    /**
     *
     * @param actValveBox
     */
    public void setActValveBox(double actValveBox) {
        this.actValveBox = actValveBox;
    }

    /**
     *
     * @return
     */
    public double getActControlValve() {
        return actControlValve;
    }

    /**
     *
     * @param actControlValve
     */
    public void setActControlValve(double actControlValve) {
        this.actControlValve = actControlValve;
    }
    
    
    
}