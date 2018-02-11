/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.homepage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author 645011
 */
public class HomePageGUIController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button testBtn;
    @FXML
    private Button btn2;
    
    @FXML
    private void btnTest(ActionEvent event) throws IOException, URISyntaxException {
        
        URL url = this.getClass().getResource("/src/ui/CreateClientGUI.fxml");
        File file = new File(url.toURI());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/src/ui/CreateClientGUI.fxml"));
        rootPane.getChildren().setAll(pane);
    }
    
    @FXML
    private void testBtn2(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/capstoneguitest/CreateClientGUI.fxml"));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
