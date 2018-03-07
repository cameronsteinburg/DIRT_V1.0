/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new labourer
 */
package controller;

import application.Main;
import entity.Labourer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateLabourerGUIController implements Initializable {

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private TextField addressField;
    @FXML
    private TextField emergencyNameField;
    @FXML
    private TextField emergencyPhone1Field;
    @FXML
    private TextField emergencyPhone2Field;
    @FXML
    private TextField wageField;
    @FXML
    private TextField sinField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phone2Field;
    @FXML
    private TextField phone1Field;
    @FXML
    private TextField titleField;
    @FXML
    private TextField lnameField;
    @FXML
    private TextField fnameField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label errorMessage;

    /**
     * empties all fields in the form for User in case they want to start over
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void clearBtnAction(ActionEvent event) throws IOException {

        addressField.clear();
        emergencyNameField.clear();
        emergencyPhone1Field.clear();
        emergencyPhone2Field.clear();
        wageField.clear();
        sinField.clear();
        emailField.clear();
        phone2Field.clear();
        phone1Field.clear();
        titleField.clear();
        lnameField.clear();
        fnameField.clear();
    }

    /**
     *
     * @param event
     */
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException {

        String fname = fnameField.getText();// not null
        String lname = lnameField.getText();//not null
        String title = titleField.getText();
        String phone1 = phone1Field.getText();
        String phone2 = phone2Field.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String wage = wageField.getText();
        String emergName = emergencyNameField.getText();
        String emergePhone1 = emergencyPhone1Field.getText();
        String emergePhone2 = emergencyPhone2Field.getText();
        String sin = sinField.getText();
        
        Double wageDbl = null;

        //data validation commences
        if (fname.isEmpty() || lname.isEmpty() || phone1Field.getText().isEmpty()) { //checking to see if the user entered blank data for not null fields

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

        if ((emergencyPhone1Field.getText().length() > 11 || emergencyPhone1Field.getText().length() < 7) && emergencyPhone1Field.getText().length() > 0) {

            errorMessage.setText("Phone numbers must be 7 - 11 digits");
            return;
        }

        if ((emergencyPhone2Field.getText().length() > 11 || emergencyPhone2Field.getText().length() < 7) && emergencyPhone2Field.getText().length() > 0) {

            errorMessage.setText("Phone numbers++ must be 7 - 11 digits");
            return;
        }

        if ((sin.length() != 9 || !sin.matches("[0-9]+")) && sin.length() > 0) {

            errorMessage.setText("Please enter valid SIN");
            return;
        }

        if (title.length() > 30 || emergName.length() > 30) {

            errorMessage.setText("Title is too long");
            return;
        }

        if (wage.length() > 0) {

            if (wage.length() != 5 && wage.length() != 6) {

                errorMessage.setText("Please enter a valid wage in XX.XX");
                return;
            }

            try {

                wageDbl = Double.parseDouble(wage);

            } catch (Exception e) {

                errorMessage.setText("Please enter a valid wage in XX.XX");
                return;
            }
        }

        Labourer newLabourer = new Labourer(fname, lname, title, phone1, phone2, email, address, emergName, emergePhone1, emergePhone2, sin, wageDbl, null /*arraylist of skills*/, true /*isActive*/);
        Main.jdbcc.persistLabourer(newLabourer); //persist to db
        
        clearBtnAction(null);
        errorMessage.setText("Labourer Successfully Created!");
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ntohinng to load to the page beforehand 
    }
}
