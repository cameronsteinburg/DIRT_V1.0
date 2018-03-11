package controller;

import application.Main;
import entity.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class EditClientGUIController implements Initializable {

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
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

    private Client selected;

    /**
     * empties all the form fields for the User if they want to start over
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void clearBtnAction(ActionEvent event) throws IOException { //User doesn't want to complete the action, takes them back to home page

        firstNameField.clear();
        lastNameField.clear();
        companyField.clear();
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

        String first = firstNameField.getText(); //get the User's data they entered into GUI fields
        String last = lastNameField.getText();
        String comp = companyField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String description = notesField.getText();
        String phone1 = phone1Field.getText();
        String phone2 = phone2Field.getText();

        //if optional stuff comes back null, set it to "" to avoid NullPointerExceptions
        if (comp == null) 
            comp = "";
        
        if (email == null)
            email = "";
        
        if (address == null)
            address = "";
        
        if(description == null)
            description = "";
        
        if(phone2 == null)
            phone2 = "";

        //data validation commences 
        if (first.isEmpty() || last.isEmpty() || phone1.isEmpty()) { //checking to see if the user entered blank data for not null fields

            errorMessage.setText("* Required Fields Cannot Be Left Blank");
            return;
        }
        if (email != null) {
            if (email.isEmpty() == false && (email.contains("@") == false || email.contains(".") == false)) { //checking that user entered valid email address format

                errorMessage.setText("Please enter a vlid E-mail address");
                return;
            }
        }

        if (phone1.length() > 11 || phone1.length() < 7) { //checking phone number isnt too long or short

            errorMessage.setText("Phone numbers must be 7 - 11 digits");
            return;
        }

        //if User is entering a second number, checking phone number isnt too long or short
        if (phone2 != null) {

            if ((phone2.length() > 11 || phone2.length() < 7) && phone2.length() > 0) {

                errorMessage.setText("Phone numbers must be 7 - 11 digits");
                return;
            }

        }

        if (first.length() > 50 || last.length() > 50 || comp.length() > 50 || phone2.length() > 50 || phone1.length() > 50 || description.length() > 5000 || email.length() > 30 || address.length() > 50) {

            errorMessage.setText("One or more text boxes have too many characters");
            return;
        }
        //data is valid at this point

        Client newClient = new Client(first, last, comp, description, phone1, phone2, email, address, true);
        Main.jdbcc.updateClient(selected, newClient); //persist to db
        this.errorMessage.setText("Client Information Successfully Updated!");

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public TextField getFirstName() {
        return firstNameField;
    }

    public void setFirstName(String value) {
        this.firstNameField.setText(value);
    }

    public TextField getLastName() {
        return lastNameField;
    }

    public void setLastName(String value) {
        this.lastNameField.setText(value);
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

}
