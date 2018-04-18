package entity;


/**
 * This class represents the work order entity and communicates with the persistence layer
 *
 */
public class WorkOrder {
   
    protected String description;
    
    protected double quotedTotal;
    protected double actualTotal;
    
    protected String woid;
    protected String projectID;
    
    protected boolean isActive;
    
    protected String name;
    
    public String getName(){
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuotedTotal() {
        return quotedTotal;
    }

    public void setQuotedTotal(double quotedTotal) {
        this.quotedTotal = quotedTotal;
    }

    public double getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(double actualTotal) {
        this.actualTotal = actualTotal;
    }

    public String getWoid() {
        return woid;
    }

    public void setWoid(String woid) {
        this.woid = woid;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
