package entity.Services;

import entity.WorkOrder;

/**
 * Represents the Custom service entity
 */
public class WO_Custom extends WorkOrder{
    
    private double multi;
    private double rate;
    
    /**
     * Default Constructor
     * @param isActive
     */
    public WO_Custom(boolean isActive){
        this.isActive = isActive;
        this.name = "Custom";
    }

    /**
     *
     * @return
     */
    public double getMulti() {
        return multi;
    }

    /**
     *
     * @param multi
     */
    public void setMulti(double multi) {
        this.multi = multi;
    }

    /**
     *
     * @return
     */
    public double getRate() {
        return rate;
    }

    /**
     *
     * @param rate
     */
    public void setExpense(double rate) {
        this.rate = rate;
    }    
}