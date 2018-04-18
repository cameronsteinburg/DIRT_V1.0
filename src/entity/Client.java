/**
 *
 * This class represents the client entity and communicates with the persistence layer
 */
package entity;

import java.util.List;

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
     * 
     */
    public Client(){
    
    }
    
    /**
     * 
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

    public String getFullName(){
        return fullName;
    }

    public String getLastName() {
        return clientLastName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getFirstName() {
        return clientFirstName;
    }

    public void setFirstName(String clientName) {
        this.clientFirstName = clientName;
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

    public boolean getStatus() {
        return isActive;
    }

    public void setStatus(boolean status) {
        this.isActive = status;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    /**
     * 
     * @param proj 
     */
    public void projectCompleted(Project proj) {

        this.ongoingProjects.remove(proj);
        this.completedProjects.add(proj);
    }
}
