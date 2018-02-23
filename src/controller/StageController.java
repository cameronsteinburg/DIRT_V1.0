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


class StageController {

    protected static StageController control = new StageController();

    private StageController() {
    }

    private FXMLLoader loader;

    protected void navigateTo(String url) throws IOException {

        loader = new FXMLLoader();
        this.loader.setLocation(getClass().getResource(url));
        Parent parent = this.loader.load();
        Scene scene = new Scene(parent);
        Main.stage.setScene(scene);
    }
}
