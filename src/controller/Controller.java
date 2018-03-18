package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 *
 * @author 734972
 */
public abstract class Controller {
    
    /**
     *
     * @param value
     */
    public void setMessage(String value, Label errorMessage) {

        errorMessage.setText(value);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), ev -> {
            errorMessage.setText(" ");
        }));
        
        timeline.setCycleCount(1);
        timeline.play();
    }
    
}
