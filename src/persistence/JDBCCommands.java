package persistence;

import entity.Project;
import entity.WorkOrder;
import entity.Client;
import entity.Labourer;
import entity.Services.WO_Excavation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Queries, deletes, merges, and persists data from the databases
 *
 */
public class JDBCCommands {

    private Connection conn;

    /**
     * default JDBCCommands constructor
     */
    public JDBCCommands(DBAccessor dbAccess) {
        this.conn = dbAccess.getConn();
    }

    /**
     * persists client to MySQL
     *
     * @param client client to be persisted
     * @return true if no error occurs
     */
    public boolean persistClient(Client client) {
        try {
            // the mysql prepared insert statement
            String query = " insert into clients (fname, lname, company, description, phone1, phone2, email, address, isActive) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            //should probably change the ints in client class to strings at somepoint or change db to use ints instead
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, client.getFirstName());
            preparedStmt.setString(2, client.getClientLastName());
            preparedStmt.setString(3, client.getCompany());
            preparedStmt.setString(4, client.getDescription());
            preparedStmt.setString(5, client.getPhone1());
            preparedStmt.setString(6, client.getPhone2());
            preparedStmt.setString(7, client.getEmail());
            preparedStmt.setString(8, client.getAddress());
            preparedStmt.setBoolean(9, client.getStatus());

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
     * Updates a client in mysql
     *
     * @param clientOld The old client to be updated, this info is used to find
     * it.
     * @param clientNew The new information to update the old client with.
     * @return true if no errors occur
     */
    public boolean updateClient(Client clientOld, Client clientNew) {
        try {
            // the mysql prepared update statement
            String query = "update clients set fname=?, lname=?, company=?, description=?, phone1=?, phone2=?, email=?, address=? where clientNum = ?";

            // create the mysql update preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, clientNew.getFirstName());
            preparedStmt.setString(2, clientNew.getClientLastName());
            preparedStmt.setString(3, clientNew.getCompany());
            preparedStmt.setString(4, clientNew.getDescription());
            preparedStmt.setString(5, clientNew.getPhone1());
            preparedStmt.setString(6, clientNew.getPhone2());
            preparedStmt.setString(7, clientNew.getEmail());
            preparedStmt.setString(8, clientNew.getAddress());
            preparedStmt.setInt(9, getClientNum(clientOld.getFirstName(), clientOld.getLastName()));

            // execute the preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     *
     * Retrieves all clients from the database and returns an array of them
     *
     * @return an arrayList of client objects
     */
    public ArrayList<Client> getClients(boolean getDeleted) {

        try {
            ArrayList<Client> clientList = new ArrayList();
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from clients;");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                String fname = results.getString("fname");
                String lname = results.getString("lname");
                String company = results.getString("company");
                String description = results.getString("description");
                String phone1 = results.getString("phone1");
                String phone2 = results.getString("phone2");
                String email = results.getString("email");
                String address = results.getString("address");
                char isActive = results.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;

                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                if (isActive == '0' && getDeleted == true) {
                    Client client = new Client(fname, lname, company, description, phone1, phone2, email, address, isActiveToBoolean);
                    clientList.add(client);
                } else if (isActive == '1') {
                    Client client = new Client(fname, lname, company, description, phone1, phone2, email, address, isActiveToBoolean);
                    clientList.add(client);
                }
            }
            return clientList;

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Retrieves client information from MySQL database
     *
     * @return clientList List of clients in an observableList to populate
     * tables
     */
    public ObservableList<Client> getClientsForTable(boolean getDeleted) {
        try {
            ObservableList<Client> clientList = FXCollections.observableArrayList();
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from clients;");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                String fname = results.getString("fname");
                String lname = results.getString("lname");
                String company = results.getString("company");
                String description = results.getString("description");
                String phone1 = results.getString("phone1");
                String phone2 = results.getString("phone2");
                String email = results.getString("email");
                String address = results.getString("address");
                char isActive = results.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;

                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                if (isActive == '0' && getDeleted == true) {
                    Client client = new Client(fname, lname, company, description, phone1, phone2, email, address, isActiveToBoolean);
                    clientList.add(client);
                } else if (isActive == '1') {
                    Client client = new Client(fname, lname, company, description, phone1, phone2, email, address, isActiveToBoolean);
                    clientList.add(client);
                }
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
     *
     * @param clientName the name of the client to be searched for
     * @return a client object created from the information found in the clients
     * table
     */
    public Client getClient(int num) {

        try {
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from clients where clientNum = '" + num + "';");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int clientNum = results.getInt("clientNum");
                String fname = results.getString("fname");
                String lname = results.getString("lname");
                String company = results.getString("company");
                String description = results.getString("description");
                String phone1 = results.getString("phone1");
                String phone2 = results.getString("phone2");
                String email = results.getString("email");
                String address = results.getString("address");
                char isActive = results.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;

                if (isActive == '1') {
                    isActiveToBoolean = true;
                }

                Client client = new Client(fname, lname, company, description, phone1, phone2, email, address, isActiveToBoolean, clientNum);
                return client;

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param first
     * @param last
     * @return 
     */
    public Client getClient(String first, String last) {

        try {
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from clients where fname = '" + first + "' and lname = '" + last + "';");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int clientNum = results.getInt("clientNum");
                String fname = results.getString("fname");
                String lname = results.getString("lname");
                String company = results.getString("company");
                String description = results.getString("description");
                String phone1 = results.getString("phone1");
                String phone2 = results.getString("phone2");
                String email = results.getString("email");
                String address = results.getString("address");
                char isActive = results.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;

                if (isActive == '1') {
                    isActiveToBoolean = true;
                }

                Client client = new Client(fname, lname, company, description, phone1, phone2, email, address, isActiveToBoolean, clientNum);
                return client;

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * Retrieves a clientNum from the database from the name passed to the
     * method
     *
     * @param fname the first name of the client to be searched for
     * @param lname the last name of the client to be searched for
     * @return clientNum from the information found in the clients table
     */
    public int getClientNum(String fname, String lname) {

        try {
            String query = "select clientNum from clients where fname = ? and lname = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, fname);
            preparedStmt.setString(2, lname);

            // Result set contains the result of the SQL query
            ResultSet results = preparedStmt.executeQuery();

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int labourerNum = results.getInt("clientNum");
                return labourerNum;

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Logically deletes client from the app by setting the isActive value to
     * false/0
     *
     * @param client the client to be logically deleted
     * @return true if no errors occur
     */
    public boolean deleteClient(Client client) {

        try {
            Statement statement = conn.createStatement();

            // statement to set the isActive value to zero
            statement.executeUpdate("update clients set isActive=0 where fname = '" + client.getFirstName() + "';");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Logically deletes client from the app by setting the isActive value to
     * false/0
     *
     * @param client the client to be logically deleted
     * @return true if no errors occur
     */
    public boolean deleteLabourer(Labourer lab) {

        try {
            Statement statement = conn.createStatement();

            // statement to set the isActive value to zero
            statement.executeUpdate("update labourers set isActive=0 where fname = '" + lab.getFirstName() + "';");
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * persists project to MySQL
     *
     * @param project project to be persisted
     * @return true if no error occurs
     */
    public boolean persistProject(Project project) {
        
        try {
            // the mysql prepared insert statement
            String query = " insert into projects (clientNum, projectName, description, siteAddress, startDate, endDate, clientOwing, clientPaid, allowanceCost, extraneousExpenses, quote, actualCost, isActive) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
           
            
            java.sql.Date start = new java.sql.Date(project.getStartDate().getTime());
            java.sql.Date end = new java.sql.Date(project.getEndDate().getTime());
            
            System.out.println(start);
            System.out.println(end);
            
            if (project.getClient() == null){
                preparedStmt.setInt(1, 1);
            }
            preparedStmt.setInt(1, getClientNum(project.getClient().getFirstName(), project.getClient().getLastName()));
            preparedStmt.setString(2, project.getProjectName());
            preparedStmt.setString(3, project.getDescription());
            preparedStmt.setString(4, project.getSiteAddress());
            preparedStmt.setDate(5, start);
            preparedStmt.setDate(6, end);
            preparedStmt.setDouble(7, project.getClientOwing());
            preparedStmt.setBoolean(8, project.isClientPaid());
            preparedStmt.setDouble(9, project.getAllowanceCost());
            preparedStmt.setDouble(10, project.getExtraneousExpenses());
            preparedStmt.setDouble(11, project.getQuote());
            preparedStmt.setDouble(12, project.getActualCost());
            preparedStmt.setBoolean(13, project.isIsActive());

            // execute the preparedstatement
            preparedStmt.execute();
            
            
            //get the newly created projectNum
            int projectNum = getProjectNum(project.getProjectName());
            
            //add labourers to the project
            for (int i = 0; i < project.getLabourers().size(); i++){
                String projectLabourerQuery = "insert into projectLabourer values(?,?)";
                PreparedStatement preparedStmtProjectLabourer = conn.prepareStatement(projectLabourerQuery);
                preparedStmtProjectLabourer.setInt(1, projectNum);
                preparedStmtProjectLabourer.setInt(2, getLabourerNum(project.getLabourers().get(i).getFirstName(), project.getLabourers().get(i).getLastName()));
                preparedStmtProjectLabourer.execute();
            }
            
            //add work orders to the project
            for (int n = 0; n < project.getWorkOrders().size(); n++){
                String workOrderType = getWorkOrderType(project.getWorkOrders().get(n));
                String workOrderNoTypeQuery = " insert into workOrders (projectNum, description, quotedTotal, actualTotal, isActive, workOrderType) values(?, ?, ?, ?, ?, '" + workOrderType + "')";
                PreparedStatement preparedStmtWorkOrderNoType = conn.prepareStatement(workOrderNoTypeQuery, Statement.RETURN_GENERATED_KEYS);
                    preparedStmtWorkOrderNoType.setInt(1, projectNum);
                    preparedStmtWorkOrderNoType.setString(2, project.getWorkOrders().get(n).getDescription());
                    preparedStmtWorkOrderNoType.setDouble(3, project.getWorkOrders().get(n).getQuotedTotal());
                    preparedStmtWorkOrderNoType.setDouble(4, project.getWorkOrders().get(n).getActualTotal());
                    preparedStmtWorkOrderNoType.setBoolean(5, true);
                    preparedStmtWorkOrderNoType.execute();
                    
                    ResultSet lastIDsGenerated = preparedStmtWorkOrderNoType.getGeneratedKeys();
                    int workOrderNum = -1;
                    if(lastIDsGenerated.next()){
                        workOrderNum = lastIDsGenerated.getInt(1);
                    }   
                insertWorkOrderType(workOrderNum,project.getWorkOrders().get(n));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
    }
        return false;
    }
    
    
    /**
     *
     * Retrieves a projectNum from the database from the projectName passed to the
     * method
     *
     * @param projectName the name of the project to be searched for
     * @return projectNum from the information found in the projects table
     */
    public int getProjectNum(String projectName) {

        try {
            String query = "select projectNum from projects where projectName = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, projectName);

            // Result set contains the result of the SQL query
            ResultSet results = preparedStmt.executeQuery();

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int projectNum = results.getInt("projectNum");
                return projectNum;

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    /**
     * persists labourer to MySQL
     *
     * @param labourer labourer to be persisted
     * @return true if no error occurs
     */
    public boolean persistLabourer(Labourer labourer) {
        try {

            // the mysql prepared insert statement
            String query = " insert into labourers (fname, lname, title, phone1, phone2, email, address, emergcontact, emergcontactphone1, emergcontactphone2, sin, wage, isActive) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, labourer.getFirstName());
            preparedStmt.setString(2, labourer.getLastName());
            preparedStmt.setString(3, labourer.getTitle());
            preparedStmt.setString(4, labourer.getPhone1());
            preparedStmt.setString(5, labourer.getPhone2());
            preparedStmt.setString(6, labourer.getEmail());
            preparedStmt.setString(7, labourer.getAddress());
            preparedStmt.setString(8, labourer.getEmergContactName());
            preparedStmt.setString(9, labourer.getEmergContactPhone1());
            preparedStmt.setString(10, labourer.getEmergContactPhone2());
            preparedStmt.setString(11, labourer.getSin());
            preparedStmt.setString(12, labourer.getWage());
            preparedStmt.setBoolean(13, labourer.getIsActive());

            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateLabourer(Labourer labourerOld, Labourer labourerNew) {

        try {
            // the mysql prepared update statement
            String query = "update labourers set fname = ?, lname = ?, title = ?, phone1 = ?, phone2 = ?, email = ?, address = ?, emergcontact = ?, emergcontactphone1 = ?, emergcontactphone2 = ?, sin = ?, wage = ? where labourerNum = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, labourerNew.getFirstName());
            preparedStmt.setString(2, labourerNew.getLastName());
            preparedStmt.setString(3, labourerNew.getTitle());
            preparedStmt.setString(4, labourerNew.getPhone1());
            preparedStmt.setString(5, labourerNew.getPhone2());
            preparedStmt.setString(6, labourerNew.getEmail());
            preparedStmt.setString(7, labourerNew.getAddress());
            preparedStmt.setString(8, labourerNew.getEmergContactName());
            preparedStmt.setString(9, labourerNew.getEmergContactPhone1());
            preparedStmt.setString(10, labourerNew.getEmergContactPhone2());
            preparedStmt.setString(11, labourerNew.getSin());
            preparedStmt.setString(12, labourerNew.getWage());
            preparedStmt.setInt(13, getLabourerNum(labourerOld.getFirstName(), labourerOld.getLastName()));

            // execute the preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    /**
     *
     * Retrieves a labourer from the database from the name passed to the method
     *
     * @param labourerName the name of the labourer to be searched for
     * @return a labourer object created from the information found in the
     * labourers table table
     */
    public Labourer getLabourer(String labourerName) {

        try {

            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from labourers where fname = '" + labourerName + "';");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int labourerNum = results.getInt("labourerNum");
                String fname = results.getString("fname");
                String lname = results.getString("lname");
                String title = results.getString("title");
                String phone1 = results.getString("phone1");
                String phone2 = results.getString("phone2");
                String email = results.getString("email");
                String address = results.getString("address");
                String emergContact = results.getString("emergcontact");
                String emergContactPhone1 = results.getString("emergcontactphone1");
                String emergContactPhone2 = results.getString("emergcontactphone2");
                String sin = results.getString("sin");
                String wage = results.getString("wage");

                Labourer labourer = new Labourer(fname, lname, title, phone1, phone2, email, address, emergContact, emergContactPhone1, emergContactPhone2, sin, wage, labourerNum);
                return labourer;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Retrieves a labourerNum from the database from the name passed to the
     * method
     *
     * @param fname first name of the labourer to search for
     * @param lname last name of the labourer to search for
     * @return the labourerNum if found
     */
    public int getLabourerNum(String fname, String lname) {

        try {
            String query = "select labourerNum from labourers where fname = ? and lname = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, fname);
            preparedStmt.setString(2, lname);

            // Result set contains the result of the SQL query
            ResultSet results = preparedStmt.executeQuery();

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int labourerNum = results.getInt("labourerNum");
                return labourerNum;

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    /**
     * Retrieves laborer information from MySQL database
     *
     * @return labourerList List of laborers in an observableList to populate
     * tables
     */
    public ObservableList<Labourer> getLabourersForTable(boolean getDeleted) {
        try {
            ObservableList<Labourer> labourerList = FXCollections.observableArrayList();
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from labourers where isActive = 1;");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                String fname = results.getString("fname");
                String lname = results.getString("lname");
                String title = results.getString("title");
                String phone1 = results.getString("phone1");
                String phone2 = results.getString("phone2");
                String email = results.getString("email");
                String address = results.getString("address");
                String emergContact = results.getString("emergcontact");
                String emergContactPhone1 = results.getString("emergcontactphone1");
                String emergContactPhone2 = results.getString("emergcontactphone2");
                String sin = results.getString("sin");
                String wage = results.getString("wage");
                char isActive = results.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;

                Labourer labourer = new Labourer(fname, lname, title, phone1, phone2, email, address, emergContact, emergContactPhone1, emergContactPhone2, sin, wage);
                labourerList.add(labourer);

            }
            return labourerList;

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * exports a project to QuickBooks
     *
     * @param project project to be exported to QuickBooks
     * @return true if no error occurs
     */
    public boolean exportProjectQuote(Project project) {
        return false;

    }

    /**
     * deletes a project from MySQL
     *
     * @param project project to be found and deleted
     * @return true if no error occurs
     */
    public boolean deleteProject(Project project) {
        return false;

    }

    /**
     * retrieves database information to be backed up
     *
     * @return data to back up
     */
    public ResultSet backup() {
        return null;

    }

    /**
     * deletes a work order from MySQL
     *
     * @param wk the WorkOrder to be found and deleted
     * @return true if no error occurs
     */
    public boolean deleteProject(WorkOrder wk) {
        return false;

    }

    /**
     * fetches all of the labourers from the MySQL database
     *
     * @return an array list of the labourers
     */
    public ArrayList<Labourer> fetchLabourers() {
        return null;
    }

    /**
     *
     * Retrieves a constant from the database from the name passed to the method
     *
     * @param superService the superService to be searched for
     * @param subService the subService to be searched for
     * @return the constant found if no errors occur
     */
    public ArrayList getConstant(String superService, String subService) {

        try {

            // Result set contains the result of the SQL query
            String query = "select * from serviceconstants where superservice = ? and subservice = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString(1, superService);
            preparedStmt.setString(2, subService);

            // Result set contains the result of the SQL query
            ResultSet results = preparedStmt.executeQuery();

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {

                ArrayList narray = new ArrayList();
                double constantLow = results.getDouble("constantLow");
                narray.add(constantLow);

                if (superService.equals("materials") || superService.equals("irrigation")) {
                    double constantHigh = results.getDouble("constantHigh");
                    narray.add(constantHigh);
                }
                return narray;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList narray = new ArrayList();
        narray.add(-1);
        return narray;
    }

    public boolean setConstant(String superService, String subService, double constant) {

        try {
            // update to be done
            String update = "update serviceconstants set constantLow = ? where superservice = ? and subservice = ?";

            PreparedStatement preparedStmt = conn.prepareStatement(update);

            preparedStmt.setDouble(1, constant);
            preparedStmt.setString(2, superService);
            preparedStmt.setString(3, subService);

            // execute the update
            preparedStmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean setConstant(String superService, String subService, String lowOrHigh, double constant) {
        try {
            // update to be done
            String update = null;
            if (lowOrHigh.equals("constantHigh")){
                update = "update serviceconstants set constantHigh = ? where superservice = ? and subservice = ?";
            }
            else {
                update = "update serviceconstants set constantLow = ? where superservice = ? and subservice = ?";
            }
            PreparedStatement preparedStmt = conn.prepareStatement(update);

            preparedStmt.setDouble(1, constant);
            preparedStmt.setString(2, superService);
            preparedStmt.setString(3, subService);

            // execute the update
            preparedStmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public String getWorkOrderType(WorkOrder wkodr){
        if(wkodr instanceof WO_Excavation){
            if(((WO_Excavation) wkodr).getType() == 'h'){
                return "excavationByHandWorkOrder";
            }
        return "excavationBySkidWorkOrder";
    }
        return null;
    }
        
    public void insertWorkOrderType(int wkodrNum, WorkOrder wkodr){
        
        if(wkodr instanceof WO_Excavation){
            try {
                if (((WO_Excavation) wkodr).getType() == 'h'){
                    String excavationByHandQuery = "insert into ExcavationByHandWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStmtExcavationByHand = conn.prepareStatement(excavationByHandQuery);
                    preparedStmtExcavationByHand.setInt(1, wkodrNum);
                    preparedStmtExcavationByHand.setDouble(2, ((WO_Excavation) wkodr).getEstSQFT());
                    preparedStmtExcavationByHand.setDouble(3, ((WO_Excavation) wkodr).getEstDepth());
                    preparedStmtExcavationByHand.setDouble(4, ((WO_Excavation) wkodr).getEstReqYards());
                    preparedStmtExcavationByHand.setDouble(5, ((WO_Excavation) wkodr).getEstHours());
                    preparedStmtExcavationByHand.setDouble(6, ((WO_Excavation) wkodr).getEstLabour());
                    preparedStmtExcavationByHand.setDouble(7, ((WO_Excavation) wkodr).getEstTrucking());
                    preparedStmtExcavationByHand.setDouble(8, ((WO_Excavation) wkodr).getEstDisposal());
                    preparedStmtExcavationByHand.setDouble(9, ((WO_Excavation) wkodr).getActSQFT());
                    preparedStmtExcavationByHand.setDouble(10, ((WO_Excavation) wkodr).getActDepth());
                    preparedStmtExcavationByHand.setDouble(11, ((WO_Excavation) wkodr).getActReqYards());
                    preparedStmtExcavationByHand.setDouble(12, ((WO_Excavation) wkodr).getActHours());
                    preparedStmtExcavationByHand.setDouble(13, ((WO_Excavation) wkodr).getActLabour());
                    preparedStmtExcavationByHand.setDouble(14, ((WO_Excavation) wkodr).getActTrucking());
                    preparedStmtExcavationByHand.setDouble(15, ((WO_Excavation) wkodr).getActDisposal());
                    preparedStmtExcavationByHand.execute();
                }
                else{
                    String excavationByHandQuery = "insert into ExcavationBySkidWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStmtExcavationByHand = conn.prepareStatement(excavationByHandQuery);
                    preparedStmtExcavationByHand.setInt(1, wkodrNum);
                    preparedStmtExcavationByHand.setDouble(2, ((WO_Excavation) wkodr).getEstSQFT());
                    preparedStmtExcavationByHand.setDouble(3, ((WO_Excavation) wkodr).getEstDepth());
                    preparedStmtExcavationByHand.setDouble(4, ((WO_Excavation) wkodr).getEstReqYards());
                    preparedStmtExcavationByHand.setDouble(5, ((WO_Excavation) wkodr).getEstHours());
                    preparedStmtExcavationByHand.setDouble(6, ((WO_Excavation) wkodr).getEstLabour());
                    preparedStmtExcavationByHand.setDouble(7, ((WO_Excavation) wkodr).getEstTrucking());
                    preparedStmtExcavationByHand.setDouble(8, ((WO_Excavation) wkodr).getEstDisposal());
                    preparedStmtExcavationByHand.setDouble(9, ((WO_Excavation) wkodr).getActSQFT());
                    preparedStmtExcavationByHand.setDouble(10, ((WO_Excavation) wkodr).getActDepth());
                    preparedStmtExcavationByHand.setDouble(11, ((WO_Excavation) wkodr).getActReqYards());
                    preparedStmtExcavationByHand.setDouble(12, ((WO_Excavation) wkodr).getActHours());
                    preparedStmtExcavationByHand.setDouble(13, ((WO_Excavation) wkodr).getActLabour());
                    preparedStmtExcavationByHand.setDouble(14, ((WO_Excavation) wkodr).getActTrucking());
                    preparedStmtExcavationByHand.setDouble(15, ((WO_Excavation) wkodr).getActDisposal());
                    preparedStmtExcavationByHand.execute();
                }
            }catch (SQLException ex) {
                Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }
        
    }
    
}
