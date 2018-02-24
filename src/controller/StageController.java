/**
 *
 * Class to handle FXML page navigation for StageController Classes
 */
package controller;

import application.Main;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

class StageController { //only accessible in controller package

    protected static StageController control = new StageController(); //only the other controller classes in this pachage can access this singleton StageController
    
    private StageController() { //private so no other class can use this constructor maliciously
    }

    private FXMLLoader loader; //private so no one else can manipulate the loader but this class

    /**
     * Allows for navigation between pages i.e changing the active Scene in the singleton Stage object in Main
     * 
     * @param url The local destination of the fxml page in the ui package
     * @throws IOException .load() needs to throw this
     */
    protected void navigateTo(String url) throws IOException { // <--- call this method to change pages
                                                               // StageController.control.navigateTo("/ui/name.fxml");
        this.loader = new FXMLLoader();
        this.loader.setLocation(getClass().getResource(url));
        Parent parent = this.loader.load();
        Scene scene = new Scene(parent);
        Main.stage.setScene(scene);
    }
}

/*
* The stage represents the window of the application gui, scenes represent the pages that can be opened in this stage
* FXMLLoader gets the page for us from another package, loads it in to a Parent object, which is the parent of all 
* children of the scene object, makes a new Scene object with the parent object being the new parent of the scene.
* Calls the singleton Stage object from Main and use .setScene() to display the new page to the user.
*/