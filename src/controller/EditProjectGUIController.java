/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Project;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * 
 */
public class EditProjectGUIController implements Initializable {

    private Project selectedProject;

    /**
     * 
     * @param proj 
     */
    public void setSelected(Project proj) {
        this.selectedProject = proj;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
