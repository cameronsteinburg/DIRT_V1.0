package persistence;

import entity.Project;
import entity.WorkOrder;
import entity.Client;
import entity.Labourer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Queries, deletes, merges, and persists data from the databases
 *
 */
public class JDBCCommands {

	private Connection conn;
	
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
                preparedStmt.setString(3, client.getPhone1());
                preparedStmt.setString(4, client.getPhone2());
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
         * 
         * Retrieves all clients from the database and returns an array of them
         * @return an arrayList of client objects
         */
        public ArrayList<Client> getClients(){
            
            try {
                ArrayList<Client> clientList = new ArrayList();
                Statement statement = conn.createStatement();
                
                // Result set contains the result of the SQL query
                ResultSet results = statement.executeQuery("select * from clients;");

                //.next() retreives the next row, think of it like a cursor fetching
                while (results.next()) { 
                    String name = results.getString("name");
                    String description = results.getString("description");
                    String phone1 = results.getString("phone1");
                    String phone2 = results.getString("phone2");
                    String email = results.getString("email");
                    String address = results.getString("address");
                    char isActive = results.getString("isActive").charAt(0);
                    boolean isActiveToBoolean = false;
                    
                    if (isActive == '1'){
                        isActiveToBoolean = true;
                    }
                    
                    Client client = new Client(name,description,phone1,phone2,email,address,isActiveToBoolean);
                    clientList.add(client);
                }
                return clientList;
                
            } catch (SQLException ex) {
                Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
        
        
        /**
         * 
         * Retrieves a client from the database from the name passed to the method
         * @param clientName the name of the client to be searched for
         * @return a client object created from the information found in the clients table
         */
        public Client getClient(String clientName){
            
            try {
                Statement statement = conn.createStatement();
                
                // Result set contains the result of the SQL query
                ResultSet results = statement.executeQuery("select * from clients where name = '" + clientName + "';");

                //.next() retreives the next row, think of it like a cursor fetching
                while (results.next()) { 
                    String name = results.getString("name");
                    String description = results.getString("description");
                    String phone1 = results.getString("phone1");
                    String phone2 = results.getString("phone2");
                    String email = results.getString("email");
                    String address = results.getString("address");
                    char isActive = results.getString("isActive").charAt(0);
                    boolean isActiveToBoolean = false;
                    
                    if (isActive == '1'){
                        isActiveToBoolean = true;
                    }
                    
                    Client client = new Client(name,description,phone1,phone2,email,address,isActiveToBoolean);
                    return client;
                    
                }

                
            } catch (SQLException ex) {
                Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
        
        /**
         * 
         * Retrieves a clientNum from the database from the name passed to the method
         * @param clientName the name of the client to be searched for
         * @return clientNum from the information found in the clients table
         */
        public int getClientNum(String clientName){
            
            try {
                Statement statement = conn.createStatement();
                
                // Result set contains the result of the SQL query
                ResultSet results = statement.executeQuery("select * from clients where name = '" + clientName + "';");

                //.next() retreives the next row, think of it like a cursor fetching
                while (results.next()) { 
                    int clientNum = results.getInt("clientNum");
                    return clientNum;
                    
                    
                }

                
            } catch (SQLException ex) {
                Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            }
            return -1;
        }
	
	/**
	 * persists project to MySQL
	 * @param project project to be persisted
	 * @return true if no error occurs
	 */
	public boolean persistProject(Project project){
		
            try {
                // the mysql prepared insert statement
                String query = " insert into projects (clientNum, projectName, description, startDate, estimatedEndDate, clientOwing, clientPaid, estimatedShoppingCost, estimatedLabourCost, estimatedDeliveryCost, allowanceCost, actualShoppingCost, actualLabourCost, acutalDeliveryCost, extraneousExpenses, estimatedProfit, actualProfit, actualEndDate, isActive) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                
                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, getClientNum(project.getClient().getClientName()));
                preparedStmt.setString(2, project.getProjectName());
                preparedStmt.setString(3, project.getProjectDescription());
                preparedStmt.setDate(4, (Date) project.getPrelimStartDate());
                preparedStmt.setDate(5, (Date) project.getEstimatedEndDate());
                preparedStmt.setDouble(6, project.getClientOwing());
                preparedStmt.setBoolean(7, project.isClientPaid());
                preparedStmt.setDouble(8, project.getEstimatedShoppingCost());
                preparedStmt.setDouble(9, project.getEstimatedLabourCost());
                preparedStmt.setDouble(10, project.getEstimatedDeliveryCost());
                preparedStmt.setDouble(11, project.getAllowanceCost());
                preparedStmt.setDouble(12, project.getActualShoppingCost());
                preparedStmt.setDouble(13, project.getActualLabourCost());
                preparedStmt.setDouble(14, project.getActualDeliveryCost());
                preparedStmt.setDouble(15, project.getExtraneousExpenses());
                preparedStmt.setDouble(16, project.getEstimatedProfit());
                preparedStmt.setDouble(17, project.getActualProfit());
                preparedStmt.setDate(18, (Date) project.getActualEndDate());
                preparedStmt.setDouble(19, project.getStatus());
                
                
                
                // execute the preparedstatement
                preparedStmt.execute();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
		return true;
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
