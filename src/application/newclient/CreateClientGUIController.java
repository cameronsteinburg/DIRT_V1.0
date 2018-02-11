/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.newclient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author 645011
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
    private void cancelBtnAction(ActionEvent event) {
        System.out.println("testCancel");
    }
    
    @FXML
    private void saveBtnAction(ActionEvent event) {
        String name = nameField.getText();
        String phone1 = phone1Field.getText();
        String phone2 = phone2Field.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String description = descField.getText();
        
        System.out.println("name: " + name);
        System.out.println("phone1: " + phone1);
        System.out.println("phone2: " + phone2);
        System.out.println("email: " + email);
        System.out.println("address: " + address);
        System.out.println("description: " + description);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
