
package controller;

import application.Main;
import entity.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * This is the class that controls the page where data of a client entity selected by the user can be viewed.
 */
public class ClientProfileGUIController extends Controller implements Initializable {

    //FXML elements
    @FXML
    private Label nameField;
    @FXML
    private Label companyField;
    @FXML
    private Label phone1Field;
    @FXML
    private Label phone2Field;
    @FXML
    private Label emailField;
    @FXML
    private Label addressField;
    @FXML
    private TextArea notesField;

    private Label errorMessage;

    private Client selected;

    /**
     *
     * When User clicks the Save Button, system will attempt to enter the Users
     * information from fxml fields to create a new Client entity and save it to
     * the database
     *
     * @param event
     */
    @FXML
    private void saveBtnAction(ActionEvent event) {

        String desc = notesField.getText();

        if (desc.length() > 5000) {

            setMessage("Too many characters!", this.errorMessage);

        } else {

            String first = selected.getClientFirstName();
            String second = selected.getClientLastName();
            String company = selected.getCompany();
            String phone1 = selected.getPhone1();
            String phone2 = selected.getPhone2();
            String email = selected.getEmail();
            String address = selected.getAddress();

            Client updated = new Client(first, second, company, desc, phone1, phone2, email, address, true);
            Main.jdbcc.updateClient(selected, updated);
            setMessage("Notes Updated!", this.errorMessage);
        }
    }

    /**
     * 
     * @param sel 
     */
    public void setSelected(Client sel) {
        this.selected = sel;
    }

    /**
     * 
     * @param value 
     */
    public void setCompanyName(String value) {
        this.companyField.setText(value);
    }

    /**
     * 
     * @param value 
     */
    public void setName(String value) {
        this.nameField.setText(value);
    }

    /**
     * 
     * @param value 
     */
    public void setNotesField(String value) {
        this.notesField.setText(value);
    }

    /**
     * 
     * @param value 
     */
    public void setPhone1Field(String value) {
        this.phone1Field.setText(value);
    }

    /**
     * 
     * @param value 
     */
    public void setPhone2Field(String value) {
        this.phone2Field.setText(value);
    }

    /**
     * 
     * @param value 
     */
    public void setEmailField(String value) {
        this.emailField.setText(value);
    }

    /**
     * 
     * @param value 
     */
    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    /**
     * Resets reference to outer error message label
     * @param error 
     */
    protected void setErrorMessage(Label error) {
        this.errorMessage = error;
    }

    /**
     * Code that runs when page loads, unused in this case
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //nothing to load
    }
}
