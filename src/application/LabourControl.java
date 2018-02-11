package application;

import java.util.ArrayList;
import problemDomain.Labourer;

import problemDomain.Client;

/**
 * Receives input from the GUI and pass control to the proper service
 */
public class LabourControl {


	/**
	 * Save the labourer to the MySQL database
	 * @param labourer labourer to be saved
	 * @return true is successful, false otherwise
	 */
	public boolean saveSQL(Labourer labourer) {
		return false;
	}
	
	/**
	 * Set the labourer's status to inactive
	 * @param labourer labourer whose status to logically delete
	 * @return true if successful, false otherwise
	 */
	public boolean deleteLabourer(Labourer labourer) {
		return false;
	}
	
	/**
	 * Fetches the labourer's in which to add to a project
	 * @return an ArrayList of labourers to reference
	 */
	public ArrayList<Labourer> AddLabourer() {
		return null;
	}
}