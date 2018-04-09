/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 * This class represents the laborer entity and communicates with the
 * persistence layer
 *
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

    /**
     * default Laborer constructor
     */
    public Labourer() {

    }

    public Labourer(String firstname) {
        this.firstName = firstname;
    }

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
        this.isActive = true;
    }
    
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
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmergContactName() {
        return emergContactName;
    }

    public void setEmergContactName(String emergContactName) {
        this.emergContactName = emergContactName;
    }

    public String getEmergContactPhone1() {
        return emergContactPhone1;
    }

    public void setEmergContactPhone1(String emergContactPhone1) {
        this.emergContactPhone1 = emergContactPhone1;
    }

    public String getEmergContactPhone2() {
        return emergContactPhone2;
    }

    public void setEmergContactPhone2(String emergContactPhone2) {
        this.emergContactPhone2 = emergContactPhone2;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean status) {
        this.isActive = status;
    }

}
