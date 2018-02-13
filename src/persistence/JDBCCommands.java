package persistence;

import entity.Project;
import entity.WorkOrder;
import entity.Client;
import entity.Labourer;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Queries, deletes, merges, and persists data from the databases
 *
 */
public class JDBCCommands {

	Connection conn;
	
	/**
	 * default JDBCCommands constructor
	 */
	public JDBCCommands(DBAccessor dbAccess){
		this.conn = dbAccess.getConn();
	}
	
	/**
	 * persists client to MySQL
	 * @param client client to be persisted
	 * @return true if no error occurs
	 */
	public boolean persistClient(Client client){
            try {
                // the mysql prepared insert statement
                String query = " insert into clients (name, description, phone1, phone2, email, address, isActive) values (?, ?, ?, ?, ?, ?, ?)";
                
                // create the mysql insert preparedstatement
                //should probably change the ints in client class to strings at somepoint or change db to use ints instead
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, client.getClientName());
                preparedStmt.setString(2, client.getDescription());
                preparedStmt.setString(3, ""+client.getPhone1());
                preparedStmt.setString(4, ""+client.getPhone2());
                preparedStmt.setString(5, client.getEmail());
                preparedStmt.setString(6, client.getAddress());
                preparedStmt.setBoolean(7, client.getIsActive());
                
                
                // execute the preparedstatement
                preparedStmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
		return true;
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
