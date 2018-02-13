/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 645011
 */
public class Main extends Application {
    
    public static Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        
       // AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("/application/homepage/HomePageGUI.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/ui/HomePageGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        this.stage = stage;
        stage.show();  
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void navigateTo(){
    }
}
