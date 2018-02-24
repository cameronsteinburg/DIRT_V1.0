/*
 * 
 *  This is the class that controls what happens when the user is on the apps main page
 */
package controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class HomePageGUIController implements Initializable {

    //elements from the GUI.fxml page
    
    
    
    //Client actions
    
    /**
     * 
     * @param event
     * @throws IOException
     * @throws URISyntaxException 
     */
    @FXML
    private void newClientAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateClientGUI.fxml"); //takes user to page to make new Client
    }
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void editClientAction(ActionEvent event) throws IOException {

    }
    
    
    
    //Project Actions
    
    /**
     * 
     * @param event
     * @throws IOException
     * @throws URISyntaxException 
     */
    @FXML
    private void newProjectAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateProjectGUI.fxml"); //takes user to page to make new Project
    }
    
    

    //Labourer Actions
    
    /**
     * 
     * @param event
     * @throws IOException
     * @throws URISyntaxException 
     */
    @FXML
    private void newLabourerAction(ActionEvent event) throws IOException, URISyntaxException {

       StageController.control.navigateTo("/ui/CreateLabourerGUI.fxml"); //takes user to page to make new Project
    }
    

    /**
     * 
     * @param url
     * @param rb 
     */
    public void initialize(URL url, ResourceBundle rb) {
        // put stuff in this method as if its the onLoad of the fxml page
        //stuff we want to happen when the page opens like loading information from the db to the page
    }
}
