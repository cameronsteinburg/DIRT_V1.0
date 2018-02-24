package controller;

import entity.Client;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.DBServices;


public class CreateProjectGUIController implements Initializable {
    
    //elements from the GUI.fxml page
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker prelimStart;
    @FXML
    private DatePicker estEnd;
    @FXML
    private ChoiceBox clientDropdown;
    
    @FXML
    private void saveBtnAction(ActionEvent event) throws IOException {
        
        
    }
    
    @FXML
    private void cancelBtnAction(ActionEvent event) throws IOException {

        StageController.control.navigateTo("/ui/HomePageGui.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getting names for the dropdown menu so user can pick a Client to add to the project
        DBServices dbs = new DBServices(); 
        ArrayList<Client> clients = new ArrayList<Client>();
        clients.addAll(dbs.getClients());
        ObservableList<String> names = FXCollections.observableArrayList();
        
        for(int i = 0; i < clients.size(); i++){
            names.add(clients.get(i).getClientName());
        }
        
        clientDropdown.setItems((ObservableList) names);
    }    
}
