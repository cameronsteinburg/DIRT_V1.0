/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the client entity and communicates with the persistence
 * layer
 *
 */
public class Client {

    private String clientName; //definitely not-null
    private String description;// can be null
    private int phone1; // null?
    private int phone2; //can be null
    private String email;// null?
    private String address;// null?
    private List<Project> ongoingProjects;//can be null
    private List<Project> completedProjects;//can be null
    private List<String> clientNotes;//can be null
    private boolean isActive;//definitely not-null

    /**
     * default client constructor
     */
    public Client() {
    }

    /**
     * @param clientName
     * @param description
     * @param phone1
     * @param phone2
     * @param email
     * @param address
     * @param isActive
     *
     * for generation of brand new clients i.e createClient()
     */
    public Client(String clientName, String description, int phone1, int phone2, String email, String address, boolean isActive) {
        this.clientName = clientName;
        this.description = description;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
    }

    /**
     * @param clientName
     * @param description
     * @param phone1
     * @param phone2
     * @param email
     * @param address
     * @param isActive
     * @param ongoingProjects
     * @param completedProjects
     * @param clientNotes
     *
     * for regenerating entity in Java object from db or Client object deep copy
     */
    public Client(String clientName, String description, int phone1, int phone2, String email, String address, boolean isActive,
            List<Project> ongoingProjects, List<Project> completedProjects, List<String> clientNotes) {
        this.clientName = clientName;
        this.description = description;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
        this.ongoingProjects.clear();
        this.ongoingProjects.addAll(ongoingProjects);
        this.completedProjects.clear();
        this.completedProjects.addAll(completedProjects);
        this.clientNotes.clear();
        this.clientNotes.addAll(clientNotes);
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhone1() {
        return phone1;
    }

    public void setPhone1(int phone1) {
        this.phone1 = phone1;
    }

    public int getPhone2() {
        return phone2;
    }

    public void setPhone2(int phone2) {
        this.phone2 = phone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Project> getOngoingProjects() {
        return ongoingProjects;
    }

    public void setOngoingProjects(List<Project> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    public List<Project> getCompletedProjects() {
        return completedProjects;
    }

    public void setCompletedProjects(List<Project> completedProjects) {
        this.completedProjects = completedProjects;
    }

    public List<String> getClientNotes() {
        return clientNotes;
    }

    public void setClientNotes(List<String> clientNotes) {
        this.clientNotes = clientNotes;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Instantiates a new project and adds it to the ongoing List
     *
     * @return the instantiated project added to the ongoingProjects List
     */
    //public Project createProject() {
    //return null;
    //} cam: i feel like this doesnt need to be here
    
    
    
    /**
     * Sends itself to JDBCCommands to be saved into the database
     *
     * @param o the object to be merged with the MySQL database
     * @return true if no errors occurred
     */
    //public boolean saveSQL(Object o) { //cam: should only be general services	
    //	return false;
    //}
    /**
     * Sends Itself to JDBCCommands to be deleted from the database
     *
     * cam: i think we agreed to not remove anythign fromt he db but ill leave
     * this here anyway
     *
     * @return true if no errors occur
     */
    
    
    //public boolean deleteSQL() {
    //return false;
    //}
    
    
    /**
     * calls the backup method of JDBCCommands
     *
     * @return the result set object received from JDBCCommands
     */
  //  public ResultSet backup() { //cam: why is this here? backups apply to the whole db
      //  return null;
   // }

    /**
     * @param proj The project object to be added to this clients ongoing
     * projects
     *
     * For the first time a project is created with this Client
     */
    public void projectCreated(Project proj) {

        this.ongoingProjects.add(proj);
    }

    /**
     * @param proj The project that has officially been completed
     *
     * when a project with this client is completed
     */
    public void projectCompleted(Project proj) {

        this.ongoingProjects.remove(proj);
        this.completedProjects.add(proj);
    }
}
