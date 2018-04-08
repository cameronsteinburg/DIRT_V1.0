package controller;

import java.io.IOException;
import java.util.ArrayList;
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
 * @author 734972
 */
abstract class Controller {
    
    public static ArrayList<ArrayList> elementsFor3;

    /**
     *
     * @param value
     */
    protected void setMessage(String value, Label errorMessage) {

        errorMessage.setText(value);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {
            errorMessage.setText(" ");
        }));

        timeline.setCycleCount(1);
        timeline.play();
    }

    protected FXMLLoader navigateTo(String url, BorderPane pane) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        pane.setCenter(root);
        System.out.println(loader.getLocation() + "");
        return loader;
    }
    
    protected static void setElsFor3(ArrayList<ArrayList> els){
       elementsFor3 = els;
    }
    
    protected static ArrayList<ArrayList> getElsFor3(){
        return elementsFor3;
    }
    
    protected abstract void setErrorMessage(Label error);
}
