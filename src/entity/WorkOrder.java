package entity;


/**
 * This class represents the work order entity and communicates with the persistence layer
 */
public class WorkOrder {
   
    protected String description;
    
    protected double quotedTotal;
    protected double actualTotal;
    
    protected String woid;
    protected String projectID;
    
    protected boolean isActive;
    
    protected String name;
    
    /**
     * 
     * @return 
     */
    public String getName(){
        return name;
    }

    /**
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return 
     */
    public double getQuotedTotal() {
        return quotedTotal;
    }

    /**
     * 
     * @param quotedTotal 
     */
    public void setQuotedTotal(double quotedTotal) {
        this.quotedTotal = quotedTotal;
    }

    /**
     * 
     * @return 
     */
    public double getActualTotal() {
        return actualTotal;
    }

    /**
     * 
     * @param actualTotal 
     */
    public void setActualTotal(double actualTotal) {
        this.actualTotal = actualTotal;
    }

    /**
     * 
     * @return 
     */
    public String getWoid() {
        return woid;
    }

    /**
     * 
     * @param woid 
     */
    public void setWoid(String woid) {
        this.woid = woid;
    }

    /**
     * 
     * @return 
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * 
     * @param projectID 
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * 
     * @return 
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * 
     * @param isActive 
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
