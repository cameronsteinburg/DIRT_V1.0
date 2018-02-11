package application;
import problemDomain.*;

/**
 * Business logic that influences the application.
 */
public class GeneralService {
 ////cam: none of these should be here
	/**
	 * Send Client Object to be saved to the database
	 * @param client Client Object to be saved
	 * @return true if successful, false otherwise
	 */
	public boolean saveSQL(Client client) { //cam: ????
		return false;
	}
	
	/**
	 * Create a new Project Object
	 * @return new Project Object
	 */
	public Project createProject() { //cam: ????
		return null;
	}
	
	/**
	 * Calls the method apachePDF() with Project Object
	 * @param project Project object to be passed into apachePDF
	 * @return true if successful, false otherwise
	 */
	public boolean export(Project project) { //cam: ???
		return false;
	}
	
	/**
	 * Search for specific Project Object in the database
	 * @param searchString string used to search for Project Object
	 * @return Project Object if found
	 */
	public Project searchProject(String searchString) { //cam: ???
		return null;
	}
	
	/**
	 * Create a new WorkOrder based on parameter type
	 * @param type type of WorkOrder to be created
	 * @return new WorkOrder object
	 */
	public WorkOrder createWorkOrder(char type) { //cam: ???
		return null;
	}
}
