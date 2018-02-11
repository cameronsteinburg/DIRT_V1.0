package persistence;

import java.sql.Connection;
import java.sql.ResultSet;

import com.sun.security.ntlm.Client;
import java.util.ArrayList;
import problemDomain.Labourer;

import problemDomain.Project;
import problemDomain.WorkOrder;

/**
 * Queries, deletes, merges, and persists data from the databases
 *
 */
public class JDBCCommands {

	Connection conn;
	
	/**
	 * default JDBCCommands constructor
	 */
	public JDBCCommands(){
		
	}
	
	/**
	 * persists client to MySQL
	 * @param client client to be persisted
	 * @return true if no error occurs
	 */
	public boolean persistClient(Client client){
		return false;
		
	}
	
	/**
	 * persists project to MySQL
	 * @param project project to be persisted
	 * @return true if no error occurs
	 */
	public boolean persistProject(Project project){
		return false;
		
	}
	
	/**
	 * exports a project to QuickBooks
	 * @param project project to be exported to QuickBooks
	 * @return true if no error occurs
	 */
	public boolean exportProjectQuote(Project project){
		return false;
		
	}
	
	/**
	 * deletes a project from MySQL
	 * @param project project to be found and deleted
	 * @return true if no error occurs
	 */
	public boolean deleteProject(Project project){
		return false;
		
	}
	
	/**
	 * deletes a client from MySQL
	 * @param client client to be found and deleted
	 * @return true if no error occurs
	 */
	public boolean deleteClient(Client client){
		return false;
		
	}
	
	/**
	 * retreives database information to be backed up
	 * @return data to back up
	 */
	public ResultSet backup(){
		return null;
		
	}
	
	/**
	 * deletes a workorder from MySQL
	 * @param wk the WorkOrder to be found and deleted
	 * @return true if no error occurs
	 */
	public boolean deleteProject(WorkOrder wk){
		return false;
		
	}
	
	/**
	 * fetchs all of the labourers from the MySQL database
	 * @return an arraylist of the labourers
	 */
	public ArrayList<Labourer> fetchLabourers(){
		return null;
	}
	
}
