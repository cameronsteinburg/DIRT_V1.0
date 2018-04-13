package persistence;

import entity.Project;
import entity.WorkOrder;
import entity.Client;
import entity.Labourer;
import entity.Services.*;
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

            java.sql.Date start;
            java.sql.Date end;
            
            try{
                start = new java.sql.Date(project.getStartDate().getTime());
            } catch(NullPointerException e){
                start = null;
            }       
            
            try{
                end = new java.sql.Date(project.getEndDate().getTime());
            } catch(NullPointerException e){
                end = null;
            }    

            if (project.getClient() == null) {
                preparedStmt.setInt(1, 1);
            } else {
                preparedStmt.setInt(1, getClientNum(project.getClient().getFirstName(), project.getClient().getLastName()));
            }
                 
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
            for (int i = 0; i < project.getLabourers().size(); i++) {
                String projectLabourerQuery = "insert into projectLabourer values(?,?)";
                PreparedStatement preparedStmtProjectLabourer = conn.prepareStatement(projectLabourerQuery);
                preparedStmtProjectLabourer.setInt(1, projectNum);
                preparedStmtProjectLabourer.setInt(2, getLabourerNum(project.getLabourers().get(i).getFirstName(), project.getLabourers().get(i).getLastName()));
                preparedStmtProjectLabourer.execute();
            }

            //add work orders to the project
            for (int n = 0; n < project.getWorkOrders().size(); n++) {
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
                if (lastIDsGenerated.next()) {
                    workOrderNum = lastIDsGenerated.getInt(1);
                }
                insertWorkOrderType(workOrderNum, project.getWorkOrders().get(n));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Retrieves project information from MySQL database
     *
     * @return projectList List of projects in an observableList to populate
     * tables
     */
    public ObservableList<Project> getProjectsForTable(boolean getDeleted) {
        try {
            ObservableList<Project> projectList = FXCollections.observableArrayList();
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select projectName from projects;");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                String projectName = results.getString("projectName");
                Project project = getProject(projectName,false);
                projectList.add(project);
            }
            return projectList;

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Project getProject(String projectName, boolean getDeleted) {

        try {
            Statement statement = conn.createStatement();

            int projectNum = getProjectNum(projectName);

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from projects where projectNum = " + projectNum + ";");

            //.next() retreives the next row, think of it like a cursor fetching
            while (results.next()) {
                int clientNum = results.getInt("clientNum");
                String description = results.getString("description");
                String siteAddress = results.getString("siteAddress");
                Date startDate = results.getDate("startDate");
                Date endDate = results.getDate("endDate");
                double clientOwing = results.getDouble("clientOwing");
                char clientPaid = results.getString("clientPaid").charAt(0);
                double allowanceCost = results.getDouble("allowanceCost");
                double extraneousExpenses = results.getDouble("extraneousExpenses");
                double quote = results.getDouble("quote");
                double actualCost = results.getDouble("actualCost");
                char isActive = results.getString("isActive").charAt(0);
                boolean clientPaidToBoolean = false;
                boolean isActiveToBoolean = false;

                if (clientPaid == '1') {
                    clientPaidToBoolean = true;
                }
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }

                if (isActive == '0' && getDeleted == true) {
                    Project project = new Project(projectName, startDate, endDate, description, isActiveToBoolean);

                    project.setProjectNum(projectNum);
                    project.setSiteAddress(siteAddress);
                    project.setClientOwing(clientOwing);
                    project.setClientPaid(clientPaidToBoolean);
                    project.setAllowanceCost(allowanceCost);
                    project.setExtraneousExpenses(extraneousExpenses);
                    project.setQuote(quote);
                    project.setActualCost(actualCost);

                    Client client = getClient(clientNum);
                    project.setClient(client);
                    ArrayList<Labourer> labourers = getLabourersForProject(projectNum);
                    project.setLabourers(labourers);
                    ArrayList<WorkOrder> workOrders = getWorkOrdersForProject(projectNum);
                    project.setWorkOrders(workOrders);
                    return project;
                } else if (isActive == '1') {
                    Project project = new Project(projectName, startDate, endDate, description, isActiveToBoolean);

                    project.setProjectNum(projectNum);
                    project.setSiteAddress(siteAddress);
                    project.setClientOwing(clientOwing);
                    project.setClientPaid(clientPaidToBoolean);
                    project.setAllowanceCost(allowanceCost);
                    project.setExtraneousExpenses(extraneousExpenses);
                    project.setQuote(quote);
                    project.setActualCost(actualCost);

                    Client client = getClient(clientNum);
                    project.setClient(client);
                    ArrayList<Labourer> labourers = getLabourersForProject(projectNum);
                    project.setLabourers(labourers);
                    ArrayList<WorkOrder> workOrders = getWorkOrdersForProject(projectNum);
                    project.setWorkOrders(workOrders);
                    return project;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Labourer> getLabourersForProject(int projectNum) {
        try {
            ArrayList<Labourer> labourers = new ArrayList();
            Statement statement = conn.createStatement();

            // Result set contains the result of the SQL query
            ResultSet results = statement.executeQuery("select * from labourers natural join projectlabourer where projectNum = " + projectNum + ";");

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
                labourers.add(labourer);
            }
            return labourers;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<WorkOrder> getWorkOrdersForProject(int projectNum) {
        try {
            ArrayList<WorkOrder> workOrders = new ArrayList();
            Statement gettingWorkOrderTypes = conn.createStatement();
            Statement statement = conn.createStatement();

            ResultSet typeResults = gettingWorkOrderTypes.executeQuery("select workordernum, workordertype from workorders where projectnum = " + projectNum + ";");

            while (typeResults.next()) {
                int workOrderNum = typeResults.getInt("workordernum");
                String workOrderType = typeResults.getString("workordertype");

                // Result set contains the result of the SQL query
                ResultSet results = statement.executeQuery("select * from workorders natural join " + workOrderType + " where projectNum = " + projectNum + " and workordernum = " + workOrderNum + ";");

                results.next();
                WorkOrder workOrder = createWorkOrderForProject(workOrderType, workOrderNum, projectNum, results);
                workOrders.add(workOrder);
            }
            return workOrders;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public boolean updateProject(Project projectOld, Project projectNew){
        try {
            // the mysql prepared insert statement
            String query = "update projects set clientNum=?, projectName=?, description=?, siteAddress=?, startDate=?, endDate=?, clientOwing=?, clientPaid=?, allowanceCost=?, extraneousExpenses=?, quote=?, actualCost=? where projectNum = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            java.sql.Date start;
            java.sql.Date end;
            
            try{
                start = new java.sql.Date(projectNew.getStartDate().getTime());
            } catch(NullPointerException e){
                start = null;
            }       
            
            try{
                end = new java.sql.Date(projectNew.getEndDate().getTime());
            } catch(NullPointerException e){
                end = null;
            }    

            if (projectNew.getClient() == null) {
                preparedStmt.setInt(1, 1);
            } else {
                preparedStmt.setInt(1, getClientNum(projectNew.getClient().getFirstName(), projectNew.getClient().getLastName()));
            }
                 
            preparedStmt.setString(2, projectNew.getProjectName());
            preparedStmt.setString(3, projectNew.getDescription());
            preparedStmt.setString(4, projectNew.getSiteAddress());
            preparedStmt.setDate(5, start);
            preparedStmt.setDate(6, end);
            preparedStmt.setDouble(7, projectNew.getClientOwing());
            preparedStmt.setBoolean(8, projectNew.isClientPaid());
            preparedStmt.setDouble(9, projectNew.getAllowanceCost());
            preparedStmt.setDouble(10, projectNew.getExtraneousExpenses());
            preparedStmt.setDouble(11, projectNew.getQuote());
            preparedStmt.setDouble(12, projectNew.getActualCost());
            preparedStmt.setInt(13, getProjectNum(projectOld.getProjectName()));

            // execute the preparedstatement
            preparedStmt.execute();

            //get the newly created projectNum
            int projectNum = getProjectNum(projectNew.getProjectName());

            //add labourers to the project
            String deleteProjectLabourerQuery = "delete from projectLabourer where projectNum=?";
            PreparedStatement preparedStmtDeleteProjectLabourer = conn.prepareStatement(deleteProjectLabourerQuery);
            preparedStmtDeleteProjectLabourer.setInt(1, projectNum);
            preparedStmtDeleteProjectLabourer.executeUpdate();
            
            for (int i = 0; i < projectNew.getLabourers().size(); i++) {
                String projectLabourerQuery = "insert into projectLabourer values(?,?)";
                PreparedStatement preparedStmtProjectLabourer = conn.prepareStatement(projectLabourerQuery);
                preparedStmtProjectLabourer.setInt(1, projectNum);
                preparedStmtProjectLabourer.setInt(2, getLabourerNum(projectNew.getLabourers().get(i).getFirstName(), projectNew.getLabourers().get(i).getLastName()));
                preparedStmtProjectLabourer.execute();
            }

            
            for (int j = 0; j < projectOld.getWorkOrders().size(); j++){
                int workOrderNum =Integer.parseInt(projectOld.getWorkOrders().get(j).getWoid());
                String workOrderType = getWorkOrderType(projectOld.getWorkOrders().get(j));
                String deleteSubWorkOrderQuery = "delete from " + workOrderType + " where workordernum = ?;";
                PreparedStatement preparedStmtDeleteSubWorkOrder = conn.prepareStatement(deleteSubWorkOrderQuery);
                preparedStmtDeleteSubWorkOrder.setInt(1, workOrderNum);
                preparedStmtDeleteSubWorkOrder.executeUpdate();
            }
            
            String deleteSupWorkOrderQuery = "delete from workorders where projectNum=?";
            PreparedStatement preparedStmtDeleteSupWorkOrder = conn.prepareStatement(deleteSupWorkOrderQuery);
            preparedStmtDeleteSupWorkOrder.setInt(1, projectNum);
            preparedStmtDeleteSupWorkOrder.executeUpdate();
            
            //add work orders to the project
            for (int n = 0; n < projectNew.getWorkOrders().size(); n++) {
                String workOrderType = getWorkOrderType(projectNew.getWorkOrders().get(n));
                String workOrderNoTypeQuery = " insert into workOrders (projectNum, description, quotedTotal, actualTotal, isActive, workOrderType) values(?, ?, ?, ?, ?, '" + workOrderType + "')";
                PreparedStatement preparedStmtWorkOrderNoType = conn.prepareStatement(workOrderNoTypeQuery, Statement.RETURN_GENERATED_KEYS);
                preparedStmtWorkOrderNoType.setInt(1, projectNum);
                preparedStmtWorkOrderNoType.setString(2, projectNew.getWorkOrders().get(n).getDescription());
                preparedStmtWorkOrderNoType.setDouble(3, projectNew.getWorkOrders().get(n).getQuotedTotal());
                preparedStmtWorkOrderNoType.setDouble(4, projectNew.getWorkOrders().get(n).getActualTotal());
                preparedStmtWorkOrderNoType.setBoolean(5, true);
                preparedStmtWorkOrderNoType.execute();

                ResultSet lastIDsGenerated = preparedStmtWorkOrderNoType.getGeneratedKeys();
                int workOrderNum = -1;
                if (lastIDsGenerated.next()) {
                    workOrderNum = lastIDsGenerated.getInt(1);
                }
                insertWorkOrderType(workOrderNum, projectNew.getWorkOrders().get(n));
            }

        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    

    /**
     *
     * Retrieves a projectNum from the database from the projectName passed to
     * the method
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
            if (lowOrHigh.equals("constantHigh")) {
                update = "update serviceconstants set constantHigh = ? where superservice = ? and subservice = ?";
            } else {
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
    
    //SCALE
    public String getWorkOrderType(WorkOrder wkodr) {
        if (wkodr instanceof WO_Excavation) {
            if (((WO_Excavation) wkodr).getType() == 'h') {
                return "excavationByHandWorkOrder";
            }
            return "excavationBySkidWorkOrder";
        }
        else if(wkodr instanceof WO_Bed){
            return "BedWorkOrder";
        }
        else if(wkodr instanceof WO_Sod){
            return "SodWorkOrder";
        }
        else if(wkodr instanceof WO_TopSoil){
            return "TopSoilWorkOrder";
        }
        else if(wkodr instanceof WO_RetWall){
            return "RetWallWorkOrder";
        }
        else if(wkodr instanceof WO_WeedBarrier){
            return "WeedBarrierWorkOrder";
        }
        else if(wkodr instanceof WO_Irrigation){
            return "IrrigationWorkOrder";
    }
        return null;
    }

    //SCALE
    public void insertWorkOrderType(int wkodrNum, WorkOrder wkodr) {

        try {
            if (wkodr instanceof WO_Excavation) {
                    if (((WO_Excavation) wkodr).getType() == 'h') {
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
                    } else {
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
            }
            else if(wkodr instanceof WO_Bed){
                String query = "insert into BedWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement preparedStmt = conn.prepareStatement(query);
                        preparedStmt.setInt(1, wkodrNum);
                        preparedStmt.setDouble(2, ((WO_Bed) wkodr).getEstSQFT());
                        preparedStmt.setDouble(3, ((WO_Bed) wkodr).getEstDepth());
                        preparedStmt.setDouble(4, ((WO_Bed) wkodr).getEstReqYards());
                        preparedStmt.setDouble(5, ((WO_Bed) wkodr).getEstHours());
                        preparedStmt.setDouble(6, ((WO_Bed) wkodr).getEstLabour());
                        preparedStmt.setDouble(7, ((WO_Bed) wkodr).getAggCost());
                        preparedStmt.setDouble(8, ((WO_Bed) wkodr).getActSQFT());
                        preparedStmt.setDouble(9, ((WO_Bed) wkodr).getActDepth());
                        preparedStmt.setDouble(10, ((WO_Bed) wkodr).getActReqYards());
                        preparedStmt.setDouble(11, ((WO_Bed) wkodr).getActHours());
                        preparedStmt.setDouble(12, ((WO_Bed) wkodr).getActLabour());
                        preparedStmt.setString(13, ((WO_Bed) wkodr).getAggregate());
                        preparedStmt.execute();
            }
            else if(wkodr instanceof WO_Sod){
                String query = "insert into SodWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, wkodrNum);
                preparedStmt.setDouble(2, ((WO_Sod) wkodr).getEstSQFT());
                preparedStmt.setDouble(3, ((WO_Sod) wkodr).getEstSupplyCost());
                preparedStmt.setDouble(4, ((WO_Sod) wkodr).getEstManHours());
                preparedStmt.setDouble(5, ((WO_Sod) wkodr).getEstInstallCost());
                preparedStmt.setDouble(6, ((WO_Sod) wkodr).getActSQFT());
                preparedStmt.setDouble(7, ((WO_Sod) wkodr).getActSupplyCost());
                preparedStmt.setDouble(8, ((WO_Sod) wkodr).getActManHours());
                preparedStmt.setDouble(9, ((WO_Sod) wkodr).getActInstallCost());
                preparedStmt.execute();
            }
            else if(wkodr instanceof WO_TopSoil){
                String query = "insert into TopSoilWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, wkodrNum);
                preparedStmt.setDouble(2, ((WO_TopSoil) wkodr).getEstSQFT());
                preparedStmt.setDouble(3, ((WO_TopSoil) wkodr).getEstDepth());
                preparedStmt.setDouble(4, ((WO_TopSoil) wkodr).getEstReqYards());
                preparedStmt.setDouble(5, ((WO_TopSoil) wkodr).getEstSupplyCost());
                preparedStmt.setDouble(6, ((WO_TopSoil) wkodr).getEstManHours());
                preparedStmt.setDouble(7, ((WO_TopSoil) wkodr).getEstInstall());
                preparedStmt.setDouble(8, ((WO_TopSoil) wkodr).getActSQFT());
                preparedStmt.setDouble(9, ((WO_TopSoil) wkodr).getActDepth());
                preparedStmt.setDouble(10, ((WO_TopSoil) wkodr).getActReqYards());
                preparedStmt.setDouble(11, ((WO_TopSoil) wkodr).getActSupplyCost());
                preparedStmt.setDouble(12, ((WO_TopSoil) wkodr).getActManHours());
                preparedStmt.setDouble(13, ((WO_TopSoil) wkodr).getActInstall());
                preparedStmt.execute();
            }
            else if(wkodr instanceof WO_TopSoil){
                String query = "insert into RetWallWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, wkodrNum);
                preparedStmt.setDouble(1, ((WO_RetWall) wkodr).getEstLineFT());
                preparedStmt.setDouble(2, ((WO_RetWall) wkodr).getEstHeight());
                preparedStmt.setDouble(3, ((WO_RetWall) wkodr).getEstBaseDepth());
                preparedStmt.setDouble(4, ((WO_RetWall) wkodr).getEstBaseWidth());
                preparedStmt.setDouble(5, ((WO_RetWall) wkodr).getEstSQFT());
                preparedStmt.setDouble(6, ((WO_RetWall) wkodr).getEstBaseReqYards());
                preparedStmt.setDouble(7, ((WO_RetWall) wkodr).getEstBaseSupply());
                preparedStmt.setDouble(8, ((WO_RetWall) wkodr).getEstBaseHours());
                preparedStmt.setDouble(9, ((WO_RetWall) wkodr).getEstBaseLabour());
                preparedStmt.setDouble(10, ((WO_RetWall) wkodr).getEstBaseRowHours());
                preparedStmt.setDouble(11, ((WO_RetWall) wkodr).getEstBaseRowLabour());
                preparedStmt.setDouble(12, ((WO_RetWall) wkodr).getEstBlock());
                preparedStmt.setDouble(13, ((WO_RetWall) wkodr).getActLineFT());
                preparedStmt.setDouble(14, ((WO_RetWall) wkodr).getActHeight());
                preparedStmt.setDouble(15, ((WO_RetWall) wkodr).getActBaseDepth());
                preparedStmt.setDouble(16, ((WO_RetWall) wkodr).getActBaseWidth());
                preparedStmt.setDouble(17, ((WO_RetWall) wkodr).getActSQFT());
                preparedStmt.setDouble(18, ((WO_RetWall) wkodr).getActBaseReqYards());
                preparedStmt.setDouble(19, ((WO_RetWall) wkodr).getActBaseSupply());
                preparedStmt.setDouble(20, ((WO_RetWall) wkodr).getActBaseHours());
                preparedStmt.setDouble(21, ((WO_RetWall) wkodr).getActBaseLabour());
                preparedStmt.setDouble(22, ((WO_RetWall) wkodr).getActBaseRowHours());
                preparedStmt.setDouble(23, ((WO_RetWall) wkodr).getActBaseRowLabour());
                preparedStmt.setDouble(24, ((WO_RetWall) wkodr).getActBlock());
                preparedStmt.execute();
            }
            else if (wkodr instanceof WO_WeedBarrier) {
                String query = "insert into WeedBarrierWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, wkodrNum);
                preparedStmt.setDouble(2, ((WO_WeedBarrier) wkodr).getEstSQFT());
                preparedStmt.setDouble(3, ((WO_WeedBarrier) wkodr).getEstLayers());
                preparedStmt.setDouble(4, ((WO_WeedBarrier) wkodr).getEstReqSQFT());
                preparedStmt.setDouble(5, ((WO_WeedBarrier) wkodr).getEstHours());
                preparedStmt.setDouble(6, ((WO_WeedBarrier) wkodr).getEstStaples());
                preparedStmt.setDouble(7, ((WO_WeedBarrier) wkodr).getEstStaplesSupply());
                preparedStmt.setDouble(8, ((WO_WeedBarrier) wkodr).getEstBarrierSupply());
                preparedStmt.setDouble(9, ((WO_WeedBarrier) wkodr).getEstLabour());
                preparedStmt.setDouble(10, ((WO_WeedBarrier) wkodr).getActSQFT());
                preparedStmt.setDouble(11, ((WO_WeedBarrier) wkodr).getActLayers());
                preparedStmt.setDouble(12, ((WO_WeedBarrier) wkodr).getActReqSQFT());
                preparedStmt.setDouble(13, ((WO_WeedBarrier) wkodr).getActHours());
                preparedStmt.setDouble(14, ((WO_WeedBarrier) wkodr).getActStaples());
                preparedStmt.setDouble(15, ((WO_WeedBarrier) wkodr).getActStaplesSupply());
                preparedStmt.setDouble(16, ((WO_WeedBarrier) wkodr).getActBarrierSupply());
                preparedStmt.setDouble(17, ((WO_WeedBarrier) wkodr).getActLabour());
                preparedStmt.execute();
            }
            else if (wkodr instanceof WO_Irrigation) {
                String query = "insert into IrrigationWorkOrder values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, wkodrNum);
                preparedStmt.setDouble(2, ((WO_Irrigation) wkodr).getEstThreeQuarterLine());
                preparedStmt.setDouble(3, ((WO_Irrigation) wkodr).getEstHoseBibs());
                preparedStmt.setDouble(4, ((WO_Irrigation) wkodr).getEstOffValves());
                preparedStmt.setDouble(5, ((WO_Irrigation) wkodr).getEstRotaryHeads());
                preparedStmt.setDouble(6, ((WO_Irrigation) wkodr).getEstSprayHaeds());
                preparedStmt.setDouble(7, ((WO_Irrigation) wkodr).getEstDripLine());
                preparedStmt.setDouble(8, ((WO_Irrigation) wkodr).getEstDripEmitter());
                preparedStmt.setDouble(9, ((WO_Irrigation) wkodr).getEstTimerControl());
                preparedStmt.setDouble(10, ((WO_Irrigation) wkodr).getEstControlWire());
                preparedStmt.setDouble(11, ((WO_Irrigation) wkodr).getEstValveBox());
                preparedStmt.setDouble(12, ((WO_Irrigation) wkodr).getEstControlValve());
                preparedStmt.setDouble(13, ((WO_Irrigation) wkodr).getActThreeQuarterLine());
                preparedStmt.setDouble(14, ((WO_Irrigation) wkodr).getActHoseBibs());
                preparedStmt.setDouble(15, ((WO_Irrigation) wkodr).getActOffValves());
                preparedStmt.setDouble(16, ((WO_Irrigation) wkodr).getActRotaryHeads());
                preparedStmt.setDouble(17, ((WO_Irrigation) wkodr).getActSprayHaeds());
                preparedStmt.setDouble(18, ((WO_Irrigation) wkodr).getActDripLine());
                preparedStmt.setDouble(19, ((WO_Irrigation) wkodr).getActDripEmitter());
                preparedStmt.setDouble(20, ((WO_Irrigation) wkodr).getActTimerControl());
                preparedStmt.setDouble(21, ((WO_Irrigation) wkodr).getActControlWire());
                preparedStmt.setDouble(22, ((WO_Irrigation) wkodr).getActValveBox());
                preparedStmt.setDouble(23, ((WO_Irrigation) wkodr).getActControlValve());
                preparedStmt.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //SCALE
    public WorkOrder createWorkOrderForProject(String workOrderType, int workOrderNum, int projectNum, ResultSet result) {
        try {
            if (workOrderType.equalsIgnoreCase("excavationbyhandworkorder") || workOrderType.equalsIgnoreCase("excavationbyskidworkorder")) {
                char type = 'h';

                if (workOrderType.equalsIgnoreCase("excavationbyskidworkorder")) {
                    type = 's';
                }

                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_Excavation workOrder = new WO_Excavation(type, isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstSQFT(result.getDouble("estSQFT"));
                workOrder.setEstDepth(result.getDouble("estDepth"));
                workOrder.setEstReqYards(result.getDouble("estReqYards"));
                workOrder.setEstHours(result.getDouble("estHours"));
                workOrder.setEstLabour(result.getDouble("estLabour"));
                workOrder.setEstTrucking(result.getDouble("estTrucking"));
                workOrder.setEstDisposal(result.getDouble("estDisposal"));

                workOrder.setActSQFT(result.getDouble("actSQFT"));
                workOrder.setActDepth(result.getDouble("actDepth"));
                workOrder.setActReqYards(result.getDouble("actReqYards"));
                workOrder.setActHours(result.getDouble("actHours"));
                workOrder.setActLabour(result.getDouble("actLabour"));
                workOrder.setActTrucking(result.getDouble("actTrucking"));
                workOrder.setActDisposal(result.getDouble("actDisposal"));

                return workOrder;
            }
            
            
            else if(workOrderType.equalsIgnoreCase("bedworkorder")){
                
                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_Bed workOrder = new WO_Bed(isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstSQFT(result.getDouble("estSQFT"));
                workOrder.setEstDepth(result.getDouble("estDepth"));
                workOrder.setEstReqYards(result.getDouble("estReqYards"));
                workOrder.setEstHours(result.getDouble("estHours"));
                workOrder.setEstLabour(result.getDouble("estLabour"));
                workOrder.setAggCost(result.getDouble("aggCost"));

                workOrder.setActSQFT(result.getDouble("actSQFT"));
                workOrder.setActDepth(result.getDouble("actDepth"));
                workOrder.setActReqYards(result.getDouble("actReqYards"));
                workOrder.setActHours(result.getDouble("actHours"));
                workOrder.setActLabour(result.getDouble("ActLabour"));
                workOrder.setAggregate(result.getString("aggregate"));

                return workOrder;
            }
            
            else if(workOrderType.equalsIgnoreCase("sodworkorder")){
                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_Sod workOrder = new WO_Sod(isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstSQFT(result.getDouble("estSQFT"));
                workOrder.setEstSupplyCost(result.getDouble("estSupplyCost"));
                workOrder.setEstManHours(result.getDouble("estManHours"));
                workOrder.setEstInstallCost(result.getDouble("estInstallCost"));

                workOrder.setActSQFT(result.getDouble("actSQFT"));
                workOrder.setActSupplyCost(result.getDouble("actSupplyCost"));
                workOrder.setActManHours(result.getDouble("actReqManHours"));
                workOrder.setActInstallCost(result.getDouble("actInstallCost"));

                return workOrder;
            }
            
            else if (workOrderType.equalsIgnoreCase("topsoilworkorder")) {

                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_TopSoil workOrder = new WO_TopSoil(isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstSQFT(result.getDouble("estSQFT"));
                workOrder.setEstDepth(result.getDouble("estDepth"));
                workOrder.setEstReqYards(result.getDouble("estReqYards"));
                workOrder.setEstSupplyCost(result.getDouble("estSupplyCost"));
                workOrder.setEstManHours(result.getDouble("estManHours"));
                workOrder.setEstInstall(result.getDouble("actInstall"));

                workOrder.setActSQFT(result.getDouble("actSQFT"));
                workOrder.setActDepth(result.getDouble("actDepth"));
                workOrder.setActReqYards(result.getDouble("actReqYards"));
                workOrder.setActSupplyCost(result.getDouble("actSupplyCost"));
                workOrder.setActManHours(result.getDouble("actManHours"));
                workOrder.setActInstall(result.getDouble("actInstall"));

                return workOrder;
            }
            
            else if (workOrderType.equalsIgnoreCase("retwallworkorder")) {
                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_RetWall workOrder = new WO_RetWall(isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstLineFT(result.getDouble("estLineFT"));
                workOrder.setEstHeight(result.getDouble("estHeight"));
                workOrder.setEstBaseDepth(result.getDouble("estBaseDepth"));
                workOrder.setEstBaseWidth(result.getDouble("estBaseWidth"));
                workOrder.setEstSQFT(result.getDouble("estSQFT"));
                workOrder.setEstBaseReqYards(result.getDouble("estBaseReqYards"));
                workOrder.setEstBaseSupply(result.getDouble("estBaseSupply"));
                workOrder.setEstBaseHours(result.getDouble("estBaseHours"));
                workOrder.setEstBaseLabour(result.getDouble("estBaseLabour"));
                workOrder.setEstBaseRowHours(result.getDouble("estBaseRowHours"));
                workOrder.setEstBaseRowLabour(result.getDouble("estBaseRowLabour"));
                workOrder.setEstBlock(result.getDouble("estBlock"));
                
                workOrder.setActLineFT(result.getDouble("actLineFT"));
                workOrder.setActHeight(result.getDouble("actHeight"));
                workOrder.setActBaseDepth(result.getDouble("actBaseDepth"));
                workOrder.setActBaseWidth(result.getDouble("actBaseWidth"));
                workOrder.setActSQFT(result.getDouble("actSQFT"));
                workOrder.setActBaseReqYards(result.getDouble("actBaseReqYards"));
                workOrder.setActBaseSupply(result.getDouble("actBaseSupply"));
                workOrder.setActBaseHours(result.getDouble("actBaseHours"));
                workOrder.setActBaseLabour(result.getDouble("actBaseLabour"));
                workOrder.setActBaseRowHours(result.getDouble("actBaseRowHours"));
                workOrder.setActBaseRowLabour(result.getDouble("actBaseRowLabour"));
                workOrder.setActBlock(result.getDouble("actBlock"));
                
                return workOrder;
            }
            else if (workOrderType.equalsIgnoreCase("weedbarrierworkorder")) {
                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_WeedBarrier workOrder = new WO_WeedBarrier(isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstSQFT(result.getDouble("estSQFT"));
                workOrder.setEstLayers(result.getDouble("estLayers"));
                workOrder.setEstReqSQFT(result.getDouble("estReqSQFT"));
                workOrder.setEstHours(result.getDouble("estHours"));
                workOrder.setEstStaples(result.getDouble("estStaples"));
                workOrder.setEstStaplesSupply(result.getDouble("estStaplesSupply"));
                workOrder.setEstBarrierSupply(result.getDouble("estBarrierSupply"));
                workOrder.setEstLabour(result.getDouble("estLabour"));

                workOrder.setActSQFT(result.getDouble("actSQFT"));
                workOrder.setActLayers(result.getDouble("actLayers"));
                workOrder.setActReqSQFT(result.getDouble("actReqSQFT"));
                workOrder.setActHours(result.getDouble("actHours"));
                workOrder.setActStaples(result.getDouble("actStaples"));
                workOrder.setActStaplesSupply(result.getDouble("actStaplesSupply"));
                workOrder.setActBarrierSupply(result.getDouble("actBarrierSupply"));
                workOrder.setActLabour(result.getDouble("actLabour"));

                return workOrder;
            }
            
            else if(workOrderType.equalsIgnoreCase("irrigationworkorder")){
                
                char isActive = result.getString("isActive").charAt(0);
                boolean isActiveToBoolean = false;
                if (isActive == '1') {
                    isActiveToBoolean = true;
                }
                WO_Irrigation workOrder = new WO_Irrigation(isActiveToBoolean);

                workOrder.setProjectID("" + projectNum);
                workOrder.setWoid("" + workOrderNum);
                workOrder.setDescription(result.getString("description"));
                workOrder.setQuotedTotal(result.getDouble("quotedTotal"));
                workOrder.setActualTotal(result.getDouble("actualTotal"));

                workOrder.setEstThreeQuarterLine(result.getDouble("estThreeQuarterLine"));
                workOrder.setEstHoseBibs(result.getDouble("estHoseBibs"));
                workOrder.setEstOffValves(result.getDouble("estOffValves"));
                workOrder.setEstRotaryHeads(result.getDouble("estRotaryHeads"));
                workOrder.setEstSprayHaeds(result.getDouble("estSprayHaeds"));
                workOrder.setEstDripLine(result.getDouble("estDripLine"));
                workOrder.setEstDripEmitter(result.getDouble("estDripEmitter"));
                workOrder.setEstTimerControl(result.getDouble("estTimerControl"));
                workOrder.setEstControlWire(result.getDouble("estControlWire"));
                workOrder.setEstValveBox(result.getDouble("estValveBox"));
                workOrder.setEstControlValve(result.getDouble("estControlValve"));

                workOrder.setActThreeQuarterLine(result.getDouble("actThreeQuarterLine"));
                workOrder.setActHoseBibs(result.getDouble("actHoseBibs"));
                workOrder.setActOffValves(result.getDouble("actOffValves"));
                workOrder.setActRotaryHeads(result.getDouble("actRotaryHeads"));
                workOrder.setActSprayHaeds(result.getDouble("actSprayHaeds"));
                workOrder.setActDripLine(result.getDouble("actDripLine"));
                workOrder.setActDripEmitter(result.getDouble("actDripEmitter"));
                workOrder.setActTimerControl(result.getDouble("actTimerControl"));
                workOrder.setActControlWire(result.getDouble("actControlWire"));
                workOrder.setActValveBox(result.getDouble("actValveBox"));
                workOrder.setActControlValve(result.getDouble("actControlValve"));

                return workOrder;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCCommands.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
