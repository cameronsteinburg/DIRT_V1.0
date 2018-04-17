/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Client;
import entity.Labourer;
import entity.Project;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
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
    private ComboBox clientDropdown;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private DatePicker startField;
    @FXML
    private DatePicker endField;
    @FXML
    private TableView labTable;
    @FXML
    private TableColumn col;
    @FXML
    private TextField quoteField;
    @FXML
    private TextField finalField;

    private Project selectedProject;

    private Label errorMessage; //

    private static BorderPane outerPane; //for navigatingout of this page

    private ArrayList<Labourer> preLabs = new ArrayList(); //selected labourers

    private ObservableList<Client> clients = FXCollections.observableArrayList(); //from db

    /**
     *
     * @param event
     */
    @FXML
    private void cancelBtnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Cancel And Discard Edits?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            navigateTo("/ui/OngoingProjectsGUI.fxml", this.outerPane);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void completedAction(ActionEvent event) {

        Project old = selectedProject;

        if (selectedProject.getCompleted() == false) {

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

    public DatePicker getStartField() {
        return this.startField;
    }

    public DatePicker getEndField() {
        return this.endField;
    }

    public void setPreLabs(ArrayList<Labourer> curr) {
        this.preLabs = curr;
    }

    public void setErrorMessage(Label label) {
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
    }

    public void setWorkOrdersTable(TableView workOrdersTable) {
        this.workOrdersTable = workOrdersTable;
    }

    public void setClientDropdown(ComboBox clientDropdown) {
        this.clientDropdown = clientDropdown;
    }

    public void setNameField(String value) {
        this.nameField.setText(value);
    }

    public void setAddressField(String value) {
        this.addressField.setText(value);
    }

    public void setOuterPane(BorderPane p) {
        outerPane = p;
    }

    public void setQuote(String val) {
        this.quoteField.setText(val);
    }
    
    public void setFinal(String val) {
        this.finalField.setText(val);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DBServices dbs = new DBServices();

        ObservableList<Client> clients = FXCollections.observableArrayList();
        ObservableList<Labourer> allLabs = FXCollections.observableArrayList();
        ObservableList<String> names = FXCollections.observableArrayList();

        clients.addAll(dbs.getClients(false));
        allLabs.addAll(dbs.getLabourersForTable());

        for (int i = 0; i < clients.size(); i++) {
            names.add(clients.get(i).getFirstName() + " " + clients.get(i).getLastName());
        }

        clientDropdown.setItems(names);

        try {
            if (this.selectedProject.getClient() != null) {
                clientDropdown.setValue(selectedProject.getClientName());
            }
        } catch (NullPointerException e) {
            clientDropdown.setValue("-- Assign Client --");
        }

        this.col.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        this.labTable.setItems(dbs.getLabourersForTable());

        this.labTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        for (int i = 0; i < labTable.getItems().size(); i++) {

            String cur;
            cur = (String) col.getCellObservableValue(i).getValue();

            for (int j = 0; j < preLabs.size(); j++) {

                if (cur == preLabs.get(j).getFullName()) {

                    labTable.getSelectionModel().select(i);

                }
            }
        }

    }
}
