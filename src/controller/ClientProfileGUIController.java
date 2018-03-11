package controller;

import application.Main;
import entity.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientProfileGUIController implements Initializable {

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
    @FXML
    private Label errorMessage;

    private Client selected;

    @FXML
    private void saveBtnAction(ActionEvent event) {

        String desc = notesField.getText();

        if (desc.length() > 5000) {

            this.errorMessage.setText("Too many characters!");

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
            this.errorMessage.setText("Notes Updated!");
        }
    }

    public void setSelected(Client sel) {
        this.selected = sel;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setCompanyName(String value) {
        this.companyField.setText(value);
    }

    public void setName(String value) {
        this.nameField.setText(value);
    }

    public void setNotesField(String value) {
        this.notesField.setText(value);
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
}
