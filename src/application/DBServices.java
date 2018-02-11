package application;

import java.sql.ResultSet;

import problemDomain.*;

/**
 * Methods that have direct access and control to the databases
 */
public class DBServices {

	/**
	 * Sends a Client Object to be saved to the database
	 * @param client Client Object to be saved
	 * @return true if successful, false otherwise
	 */
	public boolean saveSQL(Client client) {
		return false;
	}
	
	/**
	 * Sends a Project Object to be saved to the database
	 * @param project Project Object to be saved
	 * @return true if successful, false otherwise
	 */
	public boolean saveSQL(Project project) {
		return false;
	}
	
	/**
	 * Calls the Projects export method
	 * @param project Project Object to export
	 * @return true is successful, false otherwise
	 */
	public boolean export(Project project) {
		return false;
	}
	
	/**
	 * Sends a Project Object to be deleted from the database
	 * @param project Project Object to be deleted
	 * @return true if successful, false otherwise
	 */
	public boolean deleteSQL(Project project) {
		return false;
	}
	
	/**
	 * Create a Client Object
	 * @return Successfully created Client Object
	 */
	public Client createClient() {
		return null;
	}
	
	/**
	 * Sends a Client Object to be deleted from the database
	 * @param client Client Object to be deleted
 	 * @return true is successful, false otherwise
	 */
	public boolean deleteSQL(Client client) {
		return false;
	}
	
	/**
	 * Request backup from Client, calls overloaded backup method
	 * @return true if successful, false otherwise
	 */
	public boolean backup() {
		return false;
	}
	
	/**
	 * Creates/updates an XML document in the res directory
	 * @param list ResultSet received from Client
	 */
	public void backup(ResultSet list) {
		
	}
	
	/**
	 * Sends a WorkOrder Object to be deleted from the database
	 * @param workOrder WorkOrder Object to be deleted
	 * @return true if successful, false otherwise
	 */
	public boolean deleteSQL(WorkOrder workOrder) {
		return false;
	}
}
