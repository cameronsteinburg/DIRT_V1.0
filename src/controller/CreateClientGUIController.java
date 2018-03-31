/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new client
 */
package controller;

import entity.Client;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import services.DBServices;

public class CreateClientGUIController extends Controller implements Initializable {

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Clear Form?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            fNameField.clear();
            lNameField.clear();
            companyField.clear();
            phone1Field.clear();
            phone2Field.clear();
            emailField.clear();
            addressField.clear();
            notesField.clear();

        } else {
            alert.close();
        }

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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirm Action");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to confrim this actions");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            String firstName = fNameField.getText(); //get the User's data they entered into GUI fields
            String lastName = lNameField.getText();
            String company = companyField.getText();
            String phone1 = phone1Field.getText();
            String phone2 = phone2Field.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String description = notesField.getText();

            if (company == null) { //eliminates possibility of NullPointerException
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

                setMessage("* Required Fields Cannot Be Left Blank", this.errorMessage);
                return;
            }

            if (email.isEmpty() == false && (email.contains("@") == false || email.contains(".") == false)) { //checking that user entered valid email address format

                setMessage("Please enter a valid E-mail address", this.errorMessage);
                return;
            }

            if (phone1.length() > 11 || phone1.length() < 7) { //checking phone number isnt too long or short

                setMessage("Phone numbers must be 7 - 11 digits", this.errorMessage);
                return;
            }

            //if User is entering a second number, checking phone number isnt too long or short
            if ((phone2.length() > 11 || phone2.length() < 7) && phone2.length() > 0) {

                setMessage("Phone numbers must be 7 - 11 digits", this.errorMessage);
                return;
            }

            if (firstName.length() > 50 || description.length() > 5000 || email.length() > 30 || address.length() > 50) {

                setMessage("One or more text boxes have too many characters", this.errorMessage);
                return;
            }
            //data is valid at this point

            Client newClient = new Client(firstName, lastName, company, description, phone1Field.getText(), phone2Field.getText(), email, address, true);
            DBServices dbs = new DBServices();

            if (editFlag == false) {

                dbs.persistClient(newClient);
                setMessage("Client Successfully Created!", this.errorMessage);
                clearFields();

            } else {

                dbs.updateClient(this.selected, newClient);
                setMessage("Client Successfully Updated!", this.errorMessage);
            }
        } else {

            alert.close();
        }
    }

    private void clearFields() {

        fNameField.clear();
        lNameField.clear();
        companyField.clear();
        phone1Field.clear();
        phone2Field.clear();
        emailField.clear();
        addressField.clear();
        notesField.clear();
    }

    public void switchButtons() {

        this.saveBtn.setVisible(false);
        this.editBtn.setVisible(true);
    }

    public void setTitleField(String value) {
        this.titleField.setText(value);
    }

    public void setFirstName(String value) {
        this.fNameField.setText(value);
    }

    public void setLastName(String value) {
        this.lNameField.setText(value);
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

    public void setPhone2Field(String value) {
        this.phone2Field.setText(value);
    }

    public void setEmailField(String value) {
        this.emailField.setText(value);
    }

    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    public void setNotesField(String value) {
        this.notesField.setText(value);
    }

    public void setSelected(Client sel) {
        this.selected = sel;
    }

    protected void setErrorMessage(Label error) {
        this.errorMessage = error;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //nothing to load
    }
}
