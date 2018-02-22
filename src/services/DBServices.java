package services;

import application.Main;
import entity.Client;
import entity.Project;
import java.sql.ResultSet;


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
         * gets a client object from the database via jdbccommands based on name
         * @param name the name of the client to retrieve
         * @return the Client object
         */
        public Client getClient(String name){
            return Main.jdbcc.getClient(name);
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
