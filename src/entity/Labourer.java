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
    
    /**
     * default Laborer constructor
     */
    public Labourer(){
    	
    }
    
}
