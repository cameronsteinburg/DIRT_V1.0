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
import javafx.scene.layout.AnchorPane;

public class CreateClientGUIController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button saveBtn;
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
    private AnchorPane rootPane;
    @FXML
    private Label errorMessage;
    @FXML
    private Label invalidEmailMessage;

    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException {

        StageController.control.navigateTo("/ui/HomePageGui.fxml");
    }

    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException {

        errorMessage.setVisible(false); //reset messages for multiple attempts bu user to get their data correct

        String name = nameField.getText();
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

        if ((phone2Field.getText().length() > 11 || phone2Field.getText().length() < 7) && phone2Field.getText().length() > 0) { //if they are entering a second number, checking phone number isnt too long or short

            errorMessage.setText("Phone number must be 7 - 11 digits");
            errorMessage.setVisible(true);
            return;
        }

        if (name.length() > 50 || phone2Field.getText().length() > 50 || phone1Field.getText().length() > 50 || description.length() > 5000 || email.length() > 30 || address.length() > 50) {

            errorMessage.setText("One or more text boxes have too many characters");
            errorMessage.setVisible(true);
            return;
        }

        System.out.println("name: " + name);
        System.out.println("phone1: " + phone1Field.getText());
        System.out.println("phone2: " + phone2Field.getText());
        System.out.println("email: " + email);
        System.out.println("address: " + address);
        System.out.println("description: " + description);

        Client newClient = new Client(name, description, phone1Field.getText(), phone2Field.getText(), email, address, true);

        Main.jdbcc.persistClient(newClient); //persist to db

        StageController.control.navigateTo("/ui/HomePageGui.fxml");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
