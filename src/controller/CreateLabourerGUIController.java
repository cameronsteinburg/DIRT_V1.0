/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new labourer
 */
package controller;

import entity.Labourer;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.DBServices;

public class CreateLabourerGUIController extends Controller implements Initializable {

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
    private TextField phone1Field; //not null
    @FXML
    private TextField titleField;
    @FXML
    private TextField lnameField; //not null
    @FXML
    private TextField fnameField; //not null
    
    private Label errorMessage;
    @FXML
    private Button saveBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Label titleLabel;

    private Labourer selected;

    private boolean editFlag;

    /**
     * empties all fields in the form for User in case they want to start over
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void clearBtnAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Clear Form?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            addressField.clear();
            emergencyNameField.clear();
            emergencyPhone1Field.clear();
            emergencyPhone2Field.clear();
            wageField.clear();
            sinField.clear();
            emailField.clear();
            phone2Field.clear();
            phone1Field.clear();
            lnameField.clear();
            fnameField.clear();
            titleField.clear();

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
     */
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirm Action");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Press OK to confrim this actions");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            String fname = fnameField.getText();// not null
            String lname = lnameField.getText();//not null
            String title = titleField.getText();
            String phone1 = phone1Field.getText(); //not null
            String phone2 = phone2Field.getText();
            String email = emailField.getText();
            String address = addressField.getText();
            String wage = wageField.getText();
            String emergeName = emergencyNameField.getText();
            String emergePhone1 = emergencyPhone1Field.getText();
            String emergePhone2 = emergencyPhone2Field.getText();
            String sin = sinField.getText();

            if (title == null) { //eliminates possibility of NullPointerException
                title = "";
            }

            if (phone2 == null) {
                phone2 = "";
            }

            if (email == null) {
                email = "";
            }

            if (address == null) {
                address = "";
            }

            if (wage == null) {
                wage = "";
            }

            if (emergeName == null) {
                emergeName = "";
            }

            if (emergePhone1 == null) {
                emergePhone1 = "";
            }

            if (sin == null) {
                sin = "";
            }

            if (emergePhone2 == null) {
                emergePhone2 = "";
            }

            //data validation commences
            if (fname.isEmpty() || lname.isEmpty() || phone1Field.getText().isEmpty()) { //checking to see if the user entered blank data for not null fields

                setMessage("* Required Fields Cannot Be Left Blank", this.errorMessage);
                return;
            }

            if (email.isEmpty() == false && (email.contains("@") == false || email.contains(".") == false)) { //checking that user entered valid email address format

                setMessage("Please enter a vlid E-mail address", this.errorMessage);
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

            if ((emergePhone1.length() > 11 || emergePhone1.length() < 7) && emergePhone1.length() > 0) {

                setMessage("Phone numbers must be 7 - 11 digits", this.errorMessage);
                return;
            }

            if ((emergePhone2.length() > 11 || emergePhone2.length() < 7) && emergePhone2.length() > 0) {

                setMessage("Phone numbers++ must be 7 - 11 digits", this.errorMessage);
                return;
            }

            if ((sin.length() != 9 || !sin.matches("[0-9]+")) && sin.length() > 0) {

                setMessage("Please enter valid SIN", this.errorMessage);
                return;
            }

            if (title.length() > 30 || emergeName.length() > 30) {

                setMessage("Title is too long", this.errorMessage);
                return;
            }

            if (wage.length() > 0) {

                if (wage.length() != 5 && wage.length() != 6) {

                    setMessage("Please enter a valid wage in XX.XX", this.errorMessage);
                    return;
                }
            }
            // data is valid at this point
            Labourer newLabourer = new Labourer(fname, lname, title, phone1, phone2, email, address, emergeName, emergePhone1, emergePhone2, sin, wage);;

            DBServices dbs = new DBServices();

            if (editFlag == false) {

                dbs.persistLabourer(newLabourer);
                clearFields();
                setMessage("Labourer Successfully Created!", this.errorMessage);

            } else {

                dbs.updateLabourer(selected, newLabourer);
                setMessage("Labourer Successfully Updated!", this.errorMessage);
            }

        } else {
            alert.close();
        }

    }

    private void clearFields() {

        addressField.clear();
        emergencyNameField.clear();
        emergencyPhone1Field.clear();
        emergencyPhone2Field.clear();
        wageField.clear();
        sinField.clear();
        emailField.clear();
        phone2Field.clear();
        phone1Field.clear();
        lnameField.clear();
        fnameField.clear();
        titleField.clear();
    }

    public TextField getAddressField() {
        return addressField;
    }

    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    public TextField getEmergencyNameField() {
        return emergencyNameField;
    }

    public void setEmergencyNameField(String value) {
        this.emergencyNameField.setText(value);
    }

    public TextField getEmergencyPhone1Field() {
        return emergencyPhone1Field;
    }

    public void setEmergencyPhone1Field(String value) {
        this.emergencyPhone1Field.setText(value);
    }

    public TextField getEmergencyPhone2Field() {
        return emergencyPhone2Field;
    }

    public void setEmergencyPhone2Field(String value) {
        this.emergencyPhone2Field.setText(value);
    }

    public TextField getWageField() {
        return wageField;
    }

    public void setWageField(String value) {
        this.wageField.setText(value);
    }

    public TextField getSinField() {
        return sinField;
    }

    public void setSinField(String value) {
        this.sinField.setText(value);
    }

    public TextField getEmailField() {
        return emailField;
    }

    public void setEmailField(String value) {
        this.emailField.setText(value);
    }

    public TextField getPhone2Field() {
        return phone2Field;
    }

    public void setTitleLable(String value) {
        this.titleLabel.setText(value);
    }

    public void setPhone2Field(String value) {
        this.phone2Field.setText(value);
    }

    public TextField getPhone1Field() {
        return phone1Field;
    }

    public void setPhone1Field(String value) {
        this.phone1Field.setText(value);
    }

    public void setTitleField(String value) {
        this.titleField.setText(value);
    }

    public TextField getLnameField() {
        return lnameField;
    }

    public void setLnameField(String value) {
        this.lnameField.setText(value);
    }

    public TextField getFnameField() {
        return fnameField;
    }

    public void setFnameField(String value) {
        this.fnameField.setText(value);
    }

    public Labourer getSelected() {
        return selected;
    }

    public void setSelected(Labourer selected) {
        this.selected = selected;
    }

    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    public void switchButtons() {

        this.saveBtn.setVisible(false);
        this.editBtn.setVisible(true);
    }

    protected void setErrorMessage(Label error) {
        this.errorMessage = error;
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // nothing to load to the page beforehand 
    }
}
