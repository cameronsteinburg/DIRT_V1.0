
package entity;

import java.util.List;

/**
 * This class represents the client entity and communicates with the persistence layer
 */
public class Client {

    private int clientNum;
    private String clientFirstName; //User's entered client's name, bisuness or personal, from CreateClientGUI screen.  NOT NULL
    private String clientLastName; //User's entered client's last name, required.
    private String company; //User's entered client's company, if they are employed by one (optional)
    private String description; //User's optional description they entered at CreateClientGUI, notes on the current job
    private String phone1; //Clients primary number entered by user at entered at CreateClientGUI NOT NULL 
    private String phone2; //Secondary means to contact Client with a phone, entered by User at CreateClientGUI
    private String email; //Client's email entered by User at CreateClientGUI
    private String address; //Client's personal/business address entered by User at CreateClientGUI
    private List<Project> ongoingProjects; //Projects this Client is affiliated with 
    private List<Project> completedProjects; //Projects this Client was previously affiliated with that have already completed
    private boolean isActive; //false if user deleted this entity, true if he hasn't NOT NULL
    private String fullName;
    
    
    /**
     * For creating clients in CreateClientGUI and ClientProfileGUI
     * @param clientFirstName
     * @param clientLastName
     * @param company
     * @param description
     * @param phone1
     * @param phone2
     * @param email
     * @param address
     * @param isActive 
     */
    public Client(String clientFirstName, String clientLastName, String company, String description, String phone1, String phone2, String email, String address, boolean isActive) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.company = company;
        this.description = description;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
        this.fullName = clientFirstName + " " + clientLastName;
    }
    
    /**
     * For when creating Clients in JDBC commands
     * @param clientFirstName
     * @param clientLastName
     * @param company
     * @param description
     * @param phone1
     * @param phone2
     * @param email
     * @param address
     * @param isActive
     * @param clientNum 
     */
    public Client(String clientFirstName, String clientLastName, String company, String description, String phone1, String phone2, String email, String address, boolean isActive, int clientNum) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.company = company;
        this.description = description;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.isActive = isActive;
        this.clientNum = clientNum;
        this.fullName = clientFirstName + " " + clientLastName;
    }

    /**
     * 
     * @return 
     */
    public String getFullName(){
        return fullName;
    }

    /**
     * 
     * @return 
     */
    public String getLastName() {
        return clientLastName;
    }

    /**
     * 
     * @return 
     */
    public String getClientFirstName() {
        return clientFirstName;
    }

    /**
     * 
     * @param clientFirstName 
     */
    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    /**
     * 
     * @return 
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * 
     * @return 
     */
    public String getFirstName() {
        return clientFirstName;
    }

    /**
     * 
     * @param clientName 
     */
    public void setFirstName(String clientName) {
        this.clientFirstName = clientName;
    }

    /**
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return 
     */
    public String getPhone1() {
        return phone1;
    }

    /**
     * 
     * @param phone1 
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * 
     * @return 
     */
    public String getPhone2() {
        return phone2;
    }

    /**
     * 
     * @param phone2 
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * 
     * @return 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return 
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address 
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return 
     */
    public List<Project> getOngoingProjects() {
        return ongoingProjects;
    }

    /**
     * 
     * @param ongoingProjects 
     */
    public void setOngoingProjects(List<Project> ongoingProjects) {
        this.ongoingProjects = ongoingProjects;
    }

    /**
     * 
     * @return 
     */
    public List<Project> getCompletedProjects() {
        return completedProjects;
    }

    /**
     * 
     * @param completedProjects 
     */
    public void setCompletedProjects(List<Project> completedProjects) {
        this.completedProjects = completedProjects;
    }

    /**
     * 
     * @return 
     */
    public boolean getStatus() {
        return isActive;
    }

    /**
     * 
     * @param status 
     */
    public void setStatus(boolean status) {
        this.isActive = status;
    }

    /**
     * 
     * @return 
     */
    public String getClientLastName() {
        return clientLastName;
    }

    /**
     * 
     * @param clientLastName 
     */
    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    /**
     * 
     * @return 
     */
    public String getCompany() {
        return company;
    }

    /**
     * 
     * @param company 
     */
    public void setCompany(String company) {
        this.company = company;
    }
}
