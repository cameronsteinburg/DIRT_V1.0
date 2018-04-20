package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * This is the class that controls the page where data of a labourer entity selected by the user can be viewed.
 */
public class LabourerProfileGUIController extends Controller implements Initializable {

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
    private Label nameField;

    
    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    public void setEmergencyNameField(String value) {
        this.emergencyNameField.setText(value);
    }

    public void setEmergencyPhone1Field(String value) {
        this.emergencyPhone1Field.setText(value);
    }

    public void setEmergencyPhone2Field(String value) {
        this.emergencyPhone2Field.setText(value);
    }

    public void setWageField(String value) {
        this.wageField.setText(value);
    }

    public void setSinField(String value) {
        this.sinField.setText(value);
    }

    public void setEmailField(String value) {
        this.emailField.setText(value);
    }

    public void setTitleLable(String value) {
        this.nameField.setText(value);
    }

    public void setPhone2Field(String value) {
        this.phone2Field.setText(value);
    }

    public void setPhone1Field(String value) {
        this.phone1Field.setText(value);
    }

    public void setTitleField(String value) {
        this.titleField.setText(value);
    }

    /**
     * Code that runs when page loads, unused in this case
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // nothing to load
    }

    /**
     * Inherited from parent class but not needed
     * @param error 
     */
    @Override
    protected void setErrorMessage(Label error) {
        //unused
    }
}
