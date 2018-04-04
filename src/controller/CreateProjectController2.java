package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author 734972
 */
public class CreateProjectController2 implements Initializable {

    public static ArrayList<ArrayList> elements;
    
    @FXML
    private GridPane starter;
    @FXML
    private AnchorPane anc;
    /**
     * Initializes the controller class.
     */
    
    protected void setEls(ArrayList<ArrayList> els){
        this.elements = els;
    }
    
    protected AnchorPane getPane(){
        return anc;
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        System.out.println(elements.get(0).get(0));
    }    
    
}
