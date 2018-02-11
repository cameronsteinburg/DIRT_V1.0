/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problemDomain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the client entity and communicates with the persistence
 * layer
 *
 */
public class Client {

	private String clientName;
	private String description;
	private int phone1;
	private int phone2;
	private String email;
	private String address;
	private List<Project> ongoinProjects;
	private List<Project> completedProjects;
	private ArrayList<String> clientNotes;
	private char status;

	/**
	 * default client constructor
	 */
	public Client() {

	}

	/**
	 * Instantiates a new project and adds it to the ongoing List
	 * 
	 * @return the instantiated project added to the ongoingProjects List
	 */
	public Project createProject() {
		return null;

	}

	/**
	 * Sends itself to JDBCCommands to be saved into the database
	 * 
	 * @param o
	 *            the object to be merged with the MySQL database
	 * @return true if no errors occurred
	 */
	public boolean saveSQL(Object o) {
		return false;

	}

	/**
	 * Sends Itself to JDBCCommands to be deleted from the database
	 * 
	 * @return true if no errors occur
	 */
	public boolean deleteSQL() {
		return false;

	}

	/**
	 * calls the backup method of JDBCCommands
	 * 
	 * @return the result set object received from JDBCCommands
	 */
	public ResultSet backup() {
		return null;

	}

}
