/**
 * 
 */
package controller;

import application.Main;
import entity.Client;
import entity.Labourer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class CreateLabourerGUIController implements Initializable {

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
    private TextField lnameField;
    @FXML
    private TextField fnameField;
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label errorMessage;

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveBtnAction(ActionEvent event) {
        errorMessage.setVisible(false); //reset messages for multiple attempts by user to get their data correct

        //TODO Data Validation
        
        Labourer newLabourer = new Labourer(fnameField.getText(),lnameField.getText(),titleField.getText(),phone1Field.getText(),phone2Field.getText(),emailField.getText(),addressField.getText(),emergencyNameField.getText(),emergencyPhone1Field.getText(), emergencyPhone2Field.getText(),sinField.getText(),Double.parseDouble(wageField.getText()), null /*arraylist of skills*/, true /*isActive*/);

        Main.jdbcc.persistLabourer(newLabourer); //persist to db
     //   try { //this is not how we navigate
       //     StageController.control.navigateTo("/ui/HomePageGui.fxml");//back home now
        //} catch (IOException ex) {
          //  Logger.getLogger(CreateLabourerGUIController.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException {
       // StageController.control.navigateTo("/ui/HomePageGui.fxml");
    }
    
}
