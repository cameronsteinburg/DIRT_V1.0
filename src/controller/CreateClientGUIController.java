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
import services.DBServices;

public class CreateClientGUIController implements Initializable {

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private TextField fNameField;
    @FXML
    private TextField lNameField;
    @FXML
    private TextField companyField;
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
    @FXML
    private Label titleField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button editBtn;

    Client selected;

    boolean editFlag = false;

    /**
     * empties all the form fields for the User if they want to start over
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void clearBtnAction(ActionEvent event) throws IOException { //User doesn't want to complete the action, takes them back to home page

        fNameField.clear();
        lNameField.clear();
        companyField.clear();
        phone1Field.clear();
        phone2Field.clear();
        emailField.clear();
        addressField.clear();
        notesField.clear();
    }

    @FXML
    private void editBtnAction(ActionEvent event) throws IOException {

        editFlag = true;
        saveBtnAction(null);
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException { //User attempts to save their details entered in fields in CreateClientGUI.fxml

        String firstName = fNameField.getText(); //get the User's data they entered into GUI fields
        String lastName = lNameField.getText();
        String company = companyField.getText();
        String phone1 = phone1Field.getText();
        String phone2 = phone2Field.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String description = notesField.getText();

        if (company == null) {
            company = "";
        }

        if (email == null) {
            email = "";
        }

        if (address == null) {
            address = "";
        }

        if (description == null) {
            description = "";
        }

        if (phone2 == null) {
            phone2 = "";
        }

        //data validation commences 
        if (firstName.isEmpty() || lastName.isEmpty() || phone1.isEmpty()) { //checking to see if the user entered blank data for not null fields

            errorMessage.setText("* Required Fields Cannot Be Left Blank");
            return;
        }

        if (email.isEmpty() == false && (email.contains("@") == false || email.contains(".") == false)) { //checking that user entered valid email address format

            errorMessage.setText("Please enter a valid E-mail address");
            return;
        }

        if (phone1.length() > 11 || phone1.length() < 7) { //checking phone number isnt too long or short

            errorMessage.setText("Phone numbers must be 7 - 11 digits");
            return;
        }

        //if User is entering a second number, checking phone number isnt too long or short
        if ((phone2.length() > 11 || phone2.length() < 7) && phone2.length() > 0) {

            errorMessage.setText("Phone numbers must be 7 - 11 digits");
            return;
        }

        if (firstName.length() > 50 || description.length() > 5000 || email.length() > 30 || address.length() > 50) {

            errorMessage.setText("One or more text boxes have too many characters");
            return;
        }
        //data is valid at this point

        Client newClient = new Client(firstName, lastName, company, description, phone1Field.getText(), phone2Field.getText(), email, address, true);
        DBServices dbs = new DBServices();

        if (editFlag == false) {

            dbs.persistClient(newClient);
            this.errorMessage.setText("Client Successfully Created!");
            clearBtnAction(null);

        } else {

            dbs.updateClient(this.selected, newClient);
            this.errorMessage.setText("Client Successfully Updated!");
        }

    }

    public void switchButtons() {

        this.saveBtn.setVisible(false);

        this.editBtn.setVisible(true);
    }

    public void setTitleField(String value) {
        this.titleField.setText(value);
    }

    public TextField getFirstName() {
        return fNameField;
    }

    public void setFirstName(String value) {
        this.fNameField.setText(value);
    }

    public TextField getLastName() {
        return lNameField;
    }

    public void setLastName(String value) {
        this.lNameField.setText(value);
    }

    public TextField getCompanyName() {
        return companyField;
    }

    public void setCompanyName(String value) {
        this.companyField.setText(value);
    }

    public TextField getPhone1Field() {
        return phone1Field;
    }

    public void setPhone1Field(String value) {
        this.phone1Field.setText(value);
    }

    public TextField getPhone2Field() {
        return phone2Field;
    }

    public void setPhone2Field(String value) {
        this.phone2Field.setText(value);
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(String value) {
        this.emailField.setText(value);
    }

    public TextField getAddressField() {
        return addressField;
    }

    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    public TextArea getNotesField() {
        return notesField;
    }

    public void setNotesField(String value) {
        this.notesField.setText(value);
    }

    public void setSelected(Client sel) {

        this.selected = sel;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //nothing to load
    }
}
