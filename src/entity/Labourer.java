/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;


/**
 * This class represents the laborer entity and communicates with the persistence layer
 *
 */
public class Labourer {
   
    private String firstName;
    private String lastName;
    private String title;
    private int phone1;
    private int phone2;
    private String email;
    private String address;
    private String emergContactName;
    private int emergContactPhone1;
    private int emergContactPhone2;
    private int sin;
    private double wage;
    private ArrayList<String> skills;
    private boolean status;

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

    public String getEmergContactName() {
        return emergContactName;
    }

    public void setEmergContactName(String emergContactName) {
        this.emergContactName = emergContactName;
    }

    public int getEmergContactPhone1() {
        return emergContactPhone1;
    }

    public void setEmergContactPhone1(int emergContactPhone1) {
        this.emergContactPhone1 = emergContactPhone1;
    }

    public int getEmergContactPhone2() {
        return emergContactPhone2;
    }

    public void setEmergContactPhone2(int emergContactPhone2) {
        this.emergContactPhone2 = emergContactPhone2;
    }

    public int getSin() {
        return sin;
    }

    public void setSin(int sin) {
        this.sin = sin;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    /**
     * default Laborer constructor
     */
    public Labourer(){
    	
    }
    
}
