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
    
    @FXML
    private void newClientAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateClientGUI.fxml");
    }
    
    @FXML
    private void newProjectAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateProjectGUI.fxml");
    }

    @FXML
    private void testBtn2(ActionEvent event) throws IOException {

       // Parent root = FXMLLoader.load(getClass().getResource("/capstoneguitest/CreateClientGUI.fxml"));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // put stuff in this method as if its the onLoad of the fxml page
        //stuff we want to happen when the page opens like loading information from the db to the page
    }
}
