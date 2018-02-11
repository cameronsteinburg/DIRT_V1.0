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
	public boolean persistObject(Object obj) {
		return false;
	}
	

	/**
	 * Calls the Projects export method
	 * @param project Project Object to export CSV
	 * @return true is successful, false otherwise
	 */
	public boolean export(Project project) {
		return false;
	}
	
	/**
	 * Request backup from DB, calls overloaded backup method
	 * @return true if successful, false otherwise
	 */
	public boolean backup() {
		return false;
	}	
}
