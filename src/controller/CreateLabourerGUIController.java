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
     *empties all fields in the form for User in case they want to start over
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
    private void saveBtnAction(ActionEvent event) {

        String fname = fnameField.getText();// not null
        String lname = lnameField.getText();//not null
        String title = titleField.getText();
        String phone1 = phone1Field.getText();
        String phone2 = phone2Field.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        Double wage = Double.parseDouble(wageField.getText());
        String emergName = emergencyNameField.getText();
        String emergePhone1 = emergencyPhone1Field.getText();
        String emergePhone2 = emergencyPhone2Field.getText();
        String sin = sinField.getText();

        //data validation commences
        Labourer newLabourer = new Labourer(fname, lname, title, phone1, phone2, email, address, emergName, emergePhone1, emergePhone2, sin, wage, null /*arraylist of skills*/, true /*isActive*/);
        Main.jdbcc.persistLabourer(newLabourer); //persist to db
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
