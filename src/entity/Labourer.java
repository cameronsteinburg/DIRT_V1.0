package entity;

import java.util.ArrayList;

/**
 * This class represents the laborer entity and communicates with the
 * persistence layer
 */
public class Labourer {

    private int labNum;
    private String firstName;
    private String lastName;
    private String title;
    private String phone1;
    private String phone2;
    private String email;
    private String address;
    private String emergContactName;
    private String emergContactPhone1;
    private String emergContactPhone2;
    private String sin;
    private String wage;
    private ArrayList<String> skills;
    private boolean isActive;
    private String fullName;

    /**
     * For use of creating labourers in JDBC commands and CreateLabourerGUI
     * @param firstName
     * @param lastName
     * @param title
     * @param phone1
     * @param phone2
     * @param email
     * @param address
     * @param emergContactName
     * @param emergContactPhone1
     * @param emergContactPhone2
     * @param sin
     * @param wage 
     */
    public Labourer(String firstName, String lastName, String title, String phone1, String phone2, String email, String address, String emergContactName, String emergContactPhone1, String emergContactPhone2, String sin, String wage) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.emergContactName = emergContactName;
        this.emergContactPhone1 = emergContactPhone1;
        this.emergContactPhone2 = emergContactPhone2;
        this.sin = sin;
        this.wage = wage;
        this.skills = null;
        this.fullName = firstName + " " + lastName;
        this.isActive = true;
    }

    /**
     * For use in JDBCCommands
     * @param firstName
     * @param lastName
     * @param title
     * @param phone1
     * @param phone2
     * @param email
     * @param address
     * @param emergContactName
     * @param emergContactPhone1
     * @param emergContactPhone2
     * @param sin
     * @param wage
     * @param labNum 
     */
    public Labourer(String firstName, String lastName, String title, String phone1, String phone2, String email, String address, String emergContactName, String emergContactPhone1, String emergContactPhone2, String sin, String wage, int labNum) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.email = email;
        this.address = address;
        this.emergContactName = emergContactName;
        this.emergContactPhone1 = emergContactPhone1;
        this.emergContactPhone2 = emergContactPhone2;
        this.sin = sin;
        this.wage = wage;
        this.skills = null;
        this.isActive = true;
        this.labNum = labNum;
        this.fullName = firstName + " " + lastName;
    }

    /**
     * 
     * @return 
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * 
     * @return 
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName 
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return 
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName 
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
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
    public String getEmergContactName() {
        return emergContactName;
    }

    /**
     * 
     * @param emergContactName 
     */
    public void setEmergContactName(String emergContactName) {
        this.emergContactName = emergContactName;
    }

    /**
     * 
     * @return 
     */
    public String getEmergContactPhone1() {
        return emergContactPhone1;
    }

    /**
     * 
     * @param emergContactPhone1 
     */
    public void setEmergContactPhone1(String emergContactPhone1) {
        this.emergContactPhone1 = emergContactPhone1;
    }

    /**
     * 
     * @return 
     */
    public String getEmergContactPhone2() {
        return emergContactPhone2;
    }

    /**
     * 
     * @param emergContactPhone2 
     */
    public void setEmergContactPhone2(String emergContactPhone2) {
        this.emergContactPhone2 = emergContactPhone2;
    }

    /**
     * 
     * @return 
     */
    public String getSin() {
        return sin;
    }

    /**
     * 
     * @param sin 
     */
    public void setSin(String sin) {
        this.sin = sin;
    }

    /**
     * 
     * @return 
     */
    public String getWage() {
        return wage;
    }

    /**
     * 
     * @param wage 
     */
    public void setWage(String wage) {
        this.wage = wage;
    }

    /**
     * 
     * @return 
     */
    public ArrayList<String> getSkills() {
        return skills;
    }

    /**
     * 
     * @param skills 
     */
    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    /**
     * 
     * @return 
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
     * 
     * @param status 
     */
    public void setIsActive(boolean status) {
        this.isActive = status;
    }
}
