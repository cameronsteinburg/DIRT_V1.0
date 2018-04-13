/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Project;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.DBServices;

/**
 * 
 */
public class EditProjectGUIController extends Controller implements Initializable {

    @FXML
    private Button completedBtn;
    @FXML
    private TextArea notesField;
    @FXML
    private TableView workOrdersTable;
    @FXML
    private TableView labourersTable;
    @FXML
    private ComboBox clientDropdown;
    @FXML
    private ComboBox labourersDropdown;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private DatePicker startField;
    @FXML
    private DatePicker endField;
    
    private Project selectedProject;
    
    private Label errorMessage;
    
    @FXML
    private void completedAction(ActionEvent event){
    
        
        Project old = selectedProject;
        
        if(selectedProject.getCompleted() == false){
        
            selectedProject.setCompleted(true);
            completedBtn.setText("Set Project Incomplete");
            setMessage("Project Set as Completed", errorMessage);
        } else {
        
            selectedProject.setCompleted(false);
            completedBtn.setText("Set Project Completed");
            setMessage("Project Set as Incomplete", errorMessage);
        }
        
        DBServices dbs = new DBServices();
        dbs.updateProject(old, selectedProject);
    }
    
    public void setErrorMessage(Label label){
        this.errorMessage = label;
    }

    
    public void setSelected(Project proj) {
        this.selectedProject = proj;
    }

    public void setCompletedBtn(String value) {
        this.completedBtn.setText(value);
    }

    public void setNotesField(String value) {
        this.notesField.setText(value);
;    }

    public void setWorkOrdersTable(TableView workOrdersTable) {
        this.workOrdersTable = workOrdersTable;
    }

    public void setLabourersTable(TableView labourersTable) {
        this.labourersTable = labourersTable;
    }

    public void setClientDropdown(ComboBox clientDropdown) {
        this.clientDropdown = clientDropdown;
    }

    public void setLabourersDropdown(ComboBox labourersDropdown) {
        this.labourersDropdown = labourersDropdown;
    }

    public void setNameField(String value) {
        this.nameField.setText(value);
    }

    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    public DatePicker getStartField() {
        return this.startField;
    }
    
    public DatePicker getEndField() {
        return this.endField;
    }

    public void setEndField(DatePicker endField) {
        this.endField = endField;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//       if(this.selectedProject.getCompleted() == false){
//           this.completedBtn.setText("Set Project Complete");
//       } else {
//           this.completedBtn.setText("Set Project Incomplete");
//       }

        DBServices dbs = new DBServices();
        
    }

}
