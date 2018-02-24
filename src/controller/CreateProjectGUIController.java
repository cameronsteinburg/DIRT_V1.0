/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new client
 */
package controller;

import entity.Client;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.DBServices;


public class CreateProjectGUIController implements Initializable {
    
    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker prelimStart;
    @FXML
    private DatePicker estEnd;
    @FXML
    private ChoiceBox clientDropdown;
    @FXML
    private TextField addressField;
    @FXML
    private TextField notesField;
    @FXML
    private Label errorMessage;
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException { //User doesn't want to complete the action, takes them back to home page
        StageController.control.navigateTo("/ui/HomePageGui.fxml");
    }
    
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException { //User attempts to save their details entered in fields in CreateProjectGUI.fxml
        
        String name = nameField.getText(); //get the User;s data they entered into GUI fields
        LocalDate prelim = prelimStart.getValue();
        LocalDate end = estEnd.getValue();
        String address = addressField.getText();
        String description = notesField.getText();
        
        System.out.println("=====START=====");
        System.out.println(prelimStart);
        System.out.println("=====END=====");
        
        //data validation commences 
        
       // if (name.isEmpty() || phone1Field.getText().isEmpty()) { //checking to see if the user entered blank data for not null fields

            if(1 == 1){
            
            errorMessage.setText("* Required Fields Cannot Be Left Blank");
            errorMessage.setVisible(true);
            return;
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getting names for the dropdown menu so user can pick a Client to add to the project
        DBServices dbs = new DBServices(); 
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.addAll(dbs.getClients());
        ObservableList<String> names = FXCollections.observableArrayList();
        
        for(int i = 0; i < clients.size(); i++){
            names.add(clients.get(i).getClientName());
        }
        
        clientDropdown.setItems((ObservableList) names);
    }    
}
