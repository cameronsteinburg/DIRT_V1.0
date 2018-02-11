package application;

import problemDomain.Project;
import problemDomain.WorkOrder;

/**
 * Receives input from the GUI and pass control to the proper service
 */
public class ProjectControl {

	/**
	 * Passes Project Object through the DataService
	 * @param project project to be passed
	 * @return true if successful, false otherwise
	 */
	public boolean exportProject(Project project) {
		return false;
	}
	
	/**
	 * Delete the specified Project Object from the database
	 * @param project Project Object to be deleted
	 * @return true if successful, false otherwise
	 */
	public boolean deleteSQL(Project project) {
		return false;
	}
	
	/**
	 * Create a new WorkOrder Object
	 * @param type type of workOrder to be created
	 * @return successful WorkOrder object
	 */
	public WorkOrder createWorkorder(char type) {
		return null;
	}
}
