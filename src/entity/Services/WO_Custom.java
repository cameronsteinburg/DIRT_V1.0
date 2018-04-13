package entity.Services;

import entity.WorkOrder;

/**
 *
 * @author 734972
 */
public class WO_Custom extends WorkOrder{
    
    private double multi;
    private double rate;
    //need description as well @steinburg
    
    public WO_Custom(boolean isActive){
        this.isActive = isActive;
    }

    public double getMulti() {
        return multi;
    }

    public void setMulti(double multi) {
        this.multi = multi;
    }

    public double getRate() {
        return rate;
    }

    public void setExpense(double rate) {
        this.rate = rate;
    }
    
    
    
}
