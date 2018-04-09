package entity;

import java.util.ArrayList;

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

}
