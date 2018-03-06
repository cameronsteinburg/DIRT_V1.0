/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new client
 */
package controller;

import application.Main;
import entity.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class CreateClientGUIController implements Initializable {

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private TextField nameField;
    @FXML
    private TextField phone1Field;
    @FXML
    private TextField phone2Field;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextArea notesField;
    @FXML
    private Label errorMessage;


    /**
     * empties all the form fields for the User if they want to start over
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void clearBtnAction(ActionEvent event) throws IOException { //User doesn't want to complete the action, takes them back to home page

       nameField.clear();
       phone1Field.clear();
       phone2Field.clear();
       emailField.clear();
       addressField.clear();
       notesField.clear();
    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException { //User attempts to save their details entered in fields in CreateClientGUI.fxml

        String name = nameField.getText(); //get the User's data they entered into GUI fields
        String email = emailField.getText();
        String address = addressField.getText();
        String description = notesField.getText();

        //data validation commences 
        if (name.isEmpty() || phone1Field.getText().isEmpty()) { //checking to see if the user entered blank data for not null fields

            errorMessage.setText("* Required Fields Cannot Be Left Blank");
            return;
        }

        if (email.isEmpty() == false && (email.contains("@") == false || email.contains(".") == false)) { //checking that user entered valid email address format

            errorMessage.setText("Please enter a vlid E-mail address");
            return;
        }

        if (phone1Field.getText().length() > 11 || phone1Field.getText().length() < 7) { //checking phone number isnt too long or short

            errorMessage.setText("Phone numbers must be 7 - 11 digits");
            return;
        }
        
        //if User is entering a second number, checking phone number isnt too long or short
        if ((phone2Field.getText().length() > 11 || phone2Field.getText().length() < 7) && phone2Field.getText().length() > 0) { 

            errorMessage.setText("Phone numbers must be 7 - 11 digits");
            return;
        }

        if (name.length() > 50 || phone2Field.getText().length() > 50 || phone1Field.getText().length() > 50 || description.length() > 5000 || email.length() > 30 || address.length() > 50) {

            errorMessage.setText("One or more text boxes have too many characters");
            return;
        }
        //data is valid at this point

        Client newClient = new Client(name, description, phone1Field.getText(), phone2Field.getText(), email, address, true);

        Main.jdbcc.persistClient(newClient); //persist to db
       
        this.errorMessage.setText("Client Successfully Created!");
        
        nameField.clear();
        emailField.clear();
        addressField.clear();
        notesField.clear();
        phone1Field.clear();
        phone2Field.clear();
        
        HomePageGUIController hpgc = new HomePageGUIController();
        hpgc.navigateTo("/ui/ViewClientGUI.fxml", "Client Successfully Created"); //doesnt work todo?
    }

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //nothing to load
    }
}
