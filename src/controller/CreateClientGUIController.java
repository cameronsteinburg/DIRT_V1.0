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
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException { //User doesn't want to complete the action, takes them back to home page

       // StageController.control.navigateTo("/ui/HomePageGui.fxml");
    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException { //User attempts to save their details entered in fields in CreateClientGUI.fxml

        errorMessage.setVisible(false); //reset messages for multiple attempts by user to get their data correct

        String name = nameField.getText(); //get the User's data they entered into GUI fields
        String email = emailField.getText();
        String address = addressField.getText();
        String description = notesField.getText();

        //data validation commences 
        if (name.isEmpty() || phone1Field.getText().isEmpty()) { //checking to see if the user entered blank data for not null fields

            errorMessage.setText("* Required Fields Cannot Be Left Blank");
            errorMessage.setVisible(true);
            return;
        }

        if (email.isEmpty() == false && (email.contains("@") == false || email.contains(".") == false)) { //checking that user entered valid email address format

            errorMessage.setText("Not a valid Email address");
            errorMessage.setVisible(true);
            return;
        }

        if (phone1Field.getText().length() > 11 || phone1Field.getText().length() < 7) { //checking phone number isnt too long or short

            errorMessage.setText("Phone number must be 7 - 11 digits");
            errorMessage.setVisible(true);
            return;
        }
        
        //if User is entering a second number, checking phone number isnt too long or short
        if ((phone2Field.getText().length() > 11 || phone2Field.getText().length() < 7) && phone2Field.getText().length() > 0) { 

            errorMessage.setText("Phone number must be 7 - 11 digits");
            errorMessage.setVisible(true);
            return;
        }

        if (name.length() > 50 || phone2Field.getText().length() > 50 || phone1Field.getText().length() > 50 || description.length() > 5000 || email.length() > 30 || address.length() > 50) {

            errorMessage.setText("One or more text boxes have too many characters");
            errorMessage.setVisible(true);
            return;
        }
        //data is valid at this point

        Client newClient = new Client(name, description, phone1Field.getText(), phone2Field.getText(), email, address, true);

        Main.jdbcc.persistClient(newClient); //persist to db

        //StageController.control.navigateTo("/ui/HomePageGui.fxml");//back home now
    }

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //nothing to load
    }
}
