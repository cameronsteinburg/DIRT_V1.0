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
	public boolean saveSQL(Client client) { // cam: i suggest rename to persistClient
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
	 * @param searchProject Porject target to search for in db
	 * @return Project Return deep copy of Project object
	 */
	public Project searchProject(Project proj) {
		return null;
                //todo needs database querying
	}
	
	/**
	 * Create a new client Object
	 * @return new client Object
	 */
	public void createClient() {
		saveSQL(new Client());
	}
	
	/**
	 * Delete the specified Client Object from the database
	 * @param client client to be deleted
	 * @return true if successful, false otherwise
	 */
	//public boolean deleteSQL(Client client) { //cam: we dont want to actually delete anything, ever. found
        //                                          found redundant method in Client, did same happen for other entities?
	//	return false;
	//}
	
	/**
	 * Create backup .sql file for clients
	 * @return true if successful, false otherwise
	 */
	public boolean backup() {
		return false;
	}
}
