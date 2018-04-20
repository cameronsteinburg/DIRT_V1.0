package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Retaining Wall service entity
 */
public class WO_RetWall extends WorkOrder {

    private double estLineFT;
    private double estHeight;
    private double estBaseDepth;
    private double estBaseWidth;
    private double estSQFT;
    private double estBaseReqYards;
    private double estBaseSupply;
    private double estBaseHours;
    private double estBaseLabour;
    private double estBaseRowHours;
    private double estBaseRowLabour;
    private double estBlock;
    
    private double actLineFT;
    private double actHeight;
    private double actBaseDepth;
    private double actBaseWidth;
    private double actSQFT;
    private double actBaseReqYards;
    private double actBaseSupply;
    private double actBaseHours;
    private double actBaseLabour;
    private double actBaseRowHours;
    private double actBaseRowLabour;
    private double actBlock;

    /**
     * Default Constructor
     * @param active
     */
    public WO_RetWall(boolean active) {
        this.isActive = isActive;
        this.name = "Retaining Wall";
    }

    /**
     *
     * @return
     */
    public double getEstLineFT() {
        return estLineFT;
    }

    /**
     *
     * @param estLineFT
     */
    public void setEstLineFT(double estLineFT) {
        this.estLineFT = estLineFT;
    }

    /**
     *
     * @return
     */
    public double getEstHeight() {
        return estHeight;
    }

    /**
     *
     * @param estHeight
     */
    public void setEstHeight(double estHeight) {
        this.estHeight = estHeight;
    }

    /**
     *
     * @return
     */
    public double getEstBaseDepth() {
        return estBaseDepth;
    }

    /**
     *
     * @param estBaseDepth
     */
    public void setEstBaseDepth(double estBaseDepth) {
        this.estBaseDepth = estBaseDepth;
    }

    /**
     *
     * @return
     */
    public double getEstBaseWidth() {
        return estBaseWidth;
    }

    /**
     *
     * @param estBaseWidth
     */
    public void setEstBaseWidth(double estBaseWidth) {
        this.estBaseWidth = estBaseWidth;
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
    public double getEstBaseReqYards() {
        return estBaseReqYards;
    }

    /**
     *
     * @param estBaseReqYards
     */
    public void setEstBaseReqYards(double estBaseReqYards) {
        this.estBaseReqYards = estBaseReqYards;
    }

    /**
     *
     * @return
     */
    public double getEstBaseSupply() {
        return estBaseSupply;
    }

    /**
     *
     * @param estBaseSupply
     */
    public void setEstBaseSupply(double estBaseSupply) {
        this.estBaseSupply = estBaseSupply;
    }

    /**
     *
     * @return
     */
    public double getEstBaseHours() {
        return estBaseHours;
    }

    /**
     *
     * @param estBaseHours
     */
    public void setEstBaseHours(double estBaseHours) {
        this.estBaseHours = estBaseHours;
    }

    /**
     *
     * @return
     */
    public double getEstBaseLabour() {
        return estBaseLabour;
    }

    /**
     *
     * @param estBaseLabour
     */
    public void setEstBaseLabour(double estBaseLabour) {
        this.estBaseLabour = estBaseLabour;
    }

    /**
     *
     * @return
     */
    public double getEstBaseRowHours() {
        return estBaseRowHours;
    }

    /**
     *
     * @param estBaseRowHours
     */
    public void setEstBaseRowHours(double estBaseRowHours) {
        this.estBaseRowHours = estBaseRowHours;
    }

    /**
     *
     * @return
     */
    public double getEstBaseRowLabour() {
        return estBaseRowLabour;
    }

    /**
     *
     * @param estBaseRowLabour
     */
    public void setEstBaseRowLabour(double estBaseRowLabour) {
        this.estBaseRowLabour = estBaseRowLabour;
    }

    /**
     *
     * @return
     */
    public double getEstBlock() {
        return estBlock;
    }

    /**
     *
     * @param estBlock
     */
    public void setEstBlock(double estBlock) {
        this.estBlock = estBlock;
    }

    /**
     *
     * @return
     */
    public double getActLineFT() {
        return actLineFT;
    }

    /**
     *
     * @param actLineFT
     */
    public void setActLineFT(double actLineFT) {
        this.actLineFT = actLineFT;
    }

    /**
     *
     * @return
     */
    public double getActHeight() {
        return actHeight;
    }

    /**
     *
     * @param actHeight
     */
    public void setActHeight(double actHeight) {
        this.actHeight = actHeight;
    }

    /**
     *
     * @return
     */
    public double getActBaseDepth() {
        return actBaseDepth;
    }

    /**
     *
     * @param actBaseDepth
     */
    public void setActBaseDepth(double actBaseDepth) {
        this.actBaseDepth = actBaseDepth;
    }

    /**
     *
     * @return
     */
    public double getActBaseWidth() {
        return actBaseWidth;
    }

    /**
     *
     * @param actBaseWidth
     */
    public void setActBaseWidth(double actBaseWidth) {
        this.actBaseWidth = actBaseWidth;
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
    public double getActBaseReqYards() {
        return actBaseReqYards;
    }

    /**
     *
     * @param actBaseReqYards
     */
    public void setActBaseReqYards(double actBaseReqYards) {
        this.actBaseReqYards = actBaseReqYards;
    }

    /**
     *
     * @return
     */
    public double getActBaseSupply() {
        return actBaseSupply;
    }

    /**
     *
     * @param actBaseSupply
     */
    public void setActBaseSupply(double actBaseSupply) {
        this.actBaseSupply = actBaseSupply;
    }

    /**
     *
     * @return
     */
    public double getActBaseHours() {
        return actBaseHours;
    }

    /**
     *
     * @param actBaseHours
     */
    public void setActBaseHours(double actBaseHours) {
        this.actBaseHours = actBaseHours;
    }

    /**
     *
     * @return
     */
    public double getActBaseLabour() {
        return actBaseLabour;
    }

    /**
     *
     * @param actBaseLabour
     */
    public void setActBaseLabour(double actBaseLabour) {
        this.actBaseLabour = actBaseLabour;
    }

    /**
     *
     * @return
     */
    public double getActBaseRowHours() {
        return actBaseRowHours;
    }

    /**
     *
     * @param actBaseRowHours
     */
    public void setActBaseRowHours(double actBaseRowHours) {
        this.actBaseRowHours = actBaseRowHours;
    }

    /**
     *
     * @return
     */
    public double getActBaseRowLabour() {
        return actBaseRowLabour;
    }

    /**
     *
     * @param actBaseRowLabour
     */
    public void setActBaseRowLabour(double actBaseRowLabour) {
        this.actBaseRowLabour = actBaseRowLabour;
    }

    /**
     *
     * @return
     */
    public double getActBlock() {
        return actBlock;
    }

    /**
     *
     * @param actBlock
     */
    public void setActBlock(double actBlock) {
        this.actBlock = actBlock;
    }

    
    
}
