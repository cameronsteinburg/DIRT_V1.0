
package controller;

import application.Main;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * Class to handle FXML page navigation for StageController Classes 
 */
class StageController {
    
    protected static StageController control = new StageController();
    
    private StageController(){
    }
    
     private FXMLLoader loader = new FXMLLoader();
    
     protected void navigateTo(String url) throws IOException{
        
        this.loader.setLocation(getClass().getResource(url));
        Parent parent = this.loader.load();
        Scene scene = new Scene(parent);
        Main.stage.setScene(scene);
    }
}
