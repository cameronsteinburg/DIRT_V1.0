/**
 * 
 * This class represents the client entity and communicates with the persistence layer
 */
package entity;

import java.util.List;

public class Client {

    private String clientName; //User's entered client's name, bisuness or personal, from CreateClientGUI screen.  NOT NULL
    private String description; //User's optional description they entered at CreateClientGUI, notes on the current job
    private String phone1; //Clients primary number entered by user at entered at CreateClientGUI NOT NULL 
    private String phone2; //Secondary means to contact Client with a phone, entered by User at CreateClientGUI
    private String email; //Client's email entered by User at CreateClientGUI
    private String address; //Client's personal/business address entered by User at CreateClientGUI
    private List<Project> ongoingProjects; //Projects this Client is affiliated with 
    private List<Project> completedProjects; //Projects this Client was previously affiliated with that have already completed
    private List<String> clientNotes; //Notes user wants to make about this particular client, either entered by User at CreateClientGUI or added at ClientProfileGUI
    private boolean isActive; //false if user deleted this entity, true if he hasn't NOT NULL

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
    public Client(String clientName, String description, String phone1, String phone2, String email, String address, boolean isActive) {
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
    public Client(String clientName, String description, String phone1, String phone2, String email, String address, boolean isActive,
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

    public String getName() {
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
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

    public boolean getStatus() {
        return isActive;
    }

    public void setStatus(boolean status) {
        this.isActive = status;
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
