package application;

import problemDomain.Client;
import problemDomain.Project;

/**
 * Receives input from the GUI and pass control to the appropriate service.
 */
public class ClientControl {

	/**
	 * Save a client object to the SQL database
	 * @param client client Object to be saved to database
	 * @return true if successful, false otherwise
	 */
	public boolean saveSQL(Client client) {
		return false;
	}
	
	/**
	 * Create a new Project Object
	 * @return new Project Object
	 */
	//public Project createProject() { //cam: this is the second createProject iv'e found, should only be in ProjectControl
	//	return null;
	//}
	
	/**
	 * Search through the database looking for matching entities
	 * @param searchProject String parameter to search for
	 * @return Project Object that matches the string
	 */
	public Project searchProject(String searchProject) {
		return null;
	}
	
	/**
	 * Create a new client Object
	 * @return new client Object
	 */
	public Client createClient() {
		return null;
	}
	
	/**
	 * Delete the specified Client Object from the database
	 * @param client client to be deleted
	 * @return true if successful, false otherwise
	 */
	public boolean deleteSQL(Client client) {
		return false;
	}
	
	/**
	 * Create backup .sql file for clients
	 * @return true if successful, false otherwise
	 */
	public boolean backup() {
		return false;
	}
}
