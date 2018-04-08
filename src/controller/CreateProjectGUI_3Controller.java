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
public class CreateProjectGUI_3Controller implements Initializable {
    
    @FXML
    private GridPane starter;
    @FXML
    private AnchorPane anc;
    
    public static ArrayList<ArrayList> elements;
    
    /**
     * 
     * @param els 
     */
    protected void setEls(ArrayList<ArrayList> els){
        this.elements = els;
    }
    
    /**
     * 
     * @return 
     */
    protected AnchorPane getPane(){
        return anc;
    }
            
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
