package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

/**
 *
 * Abstract parent of all controllers, gives children the ability to set the manner message tit he user and to navigate to other pages within the inner frame
 */
public abstract class Controller {

    /**
     *
     * @param value
     * @param errorMessage
     */
    protected void setMessage(String value, Label errorMessage) {

        errorMessage.setText(value);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
            errorMessage.setText(" ");
        }));

        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     * 
     * Gives controllers the ability to navigate to other pages
     * @param url
     * @param pane
     * @return
     */
    protected FXMLLoader navigateTo(String url, BorderPane pane) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        pane.setCenter(root);

        return loader;
    }

    /**
     * To be inherited by all child controllers, resets the reference to the outer frame error label for the inner frame controllers
     * @param error
     */
    protected abstract void setErrorMessage(Label error);
}
