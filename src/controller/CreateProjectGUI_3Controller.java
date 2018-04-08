package controller;

import application.Main;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    
    public ArrayList<ArrayList> elements;
    
    @FXML
    private void suh(ActionEvent event){
        System.out.println("SUH DUDE");
    }
    
    @FXML
    private void saveBtnAction(ActionEvent event){
    
        
        System.out.println(elements.get(0).get(0));
    }
    
    /**
     * 
     * @param els 
     */
    protected void setEls(ArrayList<ArrayList> els){
        elements = els;
    }
    
    /**
     * 
     * @return 
     */
    protected AnchorPane getPane(){
        return anc;
    }
    
    protected ArrayList<ArrayList> getList(){
        return elements;
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
