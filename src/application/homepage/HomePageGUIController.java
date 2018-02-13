/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.homepage;

import application.Main;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

        Parent root = FXMLLoader.load(getClass().getResource("/ui/CreateClientGUI.fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
        Main.stage.show();
      //  AnchorPane pane = (AnchorPane) blegh.getClass().getResource("/ui/CreateClientGUI.fxml").getContent();
        //rootPane.getChildren().setAll(pane);
        
      //  FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/CreateClientGUI.fxml"));  

        
        /*
        *MainController mainController = new MainController(path);
        Pane mainPane = FXMLLoader.load(getClass().getResource("main.fxml"));
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));        
        loader.setController(new MainController(path));
        Pane mainPane = loader.load();
        */
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
        // TODO
    }

}
