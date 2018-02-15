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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author 645011, 734972
 */
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
    private TextArea descField;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private void cancelBtnAction(ActionEvent event) {
        System.out.println("testCancel");
    }

    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException {
     
        String name = nameField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String description = descField.getText();
        
        System.out.println("name: " + name);
        System.out.println("phone1: " + phone1Field.getText());
        System.out.println("phone2: " + phone2Field.getText());
        System.out.println("email: " + email);
        System.out.println("address: " + address);
        System.out.println("description: " + description);
        
        Client newCLient = new Client(name, description, phone1Field.getText(), phone2Field.getText(), email, address, true);
        
        Main.jdbcc.persistClient(newCLient); 

        Parent root = FXMLLoader.load(getClass().getResource("/ui/HomePageGUI.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
