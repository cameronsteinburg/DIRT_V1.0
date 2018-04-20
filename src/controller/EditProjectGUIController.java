package controller;

import entity.Client;
import entity.Labourer;
import entity.Project;
import entity.WorkOrder;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    @FXML
    private TextField clientNameField;
    @FXML
    private Label currLabs;
    @FXML
    private Label ordersLabel;

    private Project selectedProject;

    private Label errorMessage;

    private static BorderPane outerPane; //for navigatingout of this page
   

    /**
     * When user clicks Save button, saves changes to the selected project entity and updates the database
     * @param event
     */
    @FXML
    private void saveBtnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Save These Changes?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            String newName = nameField.getText();
            String newAdd = addressField.getText();
            String newNotes = notesField.getText();
            String cliName = "";
            ObservableList<Labourer> labs = labTable.getSelectionModel().getSelectedItems();
            ArrayList<Labourer> labsList = new ArrayList();

            LocalDate newStart = startField.getValue();
            LocalDate newEnd = endField.getValue();

            if (labs.size() > 0) {
                labsList.addAll(labs);
            } else {
                labsList = selectedProject.getLabourers();
            }

            if (clientDropdown.getValue().equals("-- Assign Client --")) {
                cliName = selectedProject.getClientName();
            } else {
                cliName = (String) clientDropdown.getValue();
            }

            if (newAdd.length() > 30 || newName.length() > 50 || newNotes.length() > 5000) {

                setMessage("One or More of the Fields is Too Long", errorMessage);
                return;
            }

            if (newStart != null && newEnd != null && newStart.compareTo(newEnd) > 0) { //in can user set the first date to be after the end date

                setMessage("Start Date Must Be Before End Date", errorMessage);
                return;
            }//all data is valid at this point

            Client chosenClient = null;

            DBServices dbs = new DBServices();

            ArrayList<Client> clients = dbs.getClients(false);

            if (cliName != null) {
                for (int i = 0; i < clients.size(); i++) {

                    if (cliName.equals(clients.get(i).getFullName())) {

                        chosenClient = clients.get(i);

                    }
                }
            }

            ArrayList<WorkOrder> woList = selectedProject.getWorkOrders();

            Project newProj = new Project(newName, newAdd, newNotes, newStart, newEnd, chosenClient, labsList, woList, selectedProject.isIsActive());
            newProj.setQuote(selectedProject.getQuote());
            newProj.setActualCost(selectedProject.getActualCost());
            //newProj.setLabourers(labsList);

            dbs.updateProject(selectedProject, newProj);

            setMessage("Project Changes Saved!", this.errorMessage);
            navigateTo("/ui/OngoingProjectsGUI.fxml", outerPane);
        }
    }

   /**
     * Cancels users action and takes them back to the OngoingProjectsGUI.fxml
     * page.
     *
     * @param event
     */
    @FXML
    private void cancelBtnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Cancel and Discard Edits?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            navigateTo("/ui/OngoingProjectsGUI.fxml", this.outerPane);
        }
    }

    /**
     * Currently Unimplemented
     * @param event
     */
    @FXML
    private void editOrdersAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/CreateProjectGUI_3.fxml"));
        CreateProjectGUI_3Controller cont = new CreateProjectGUI_3Controller();
        loader.setController(cont);
        ObservableList<String> allItems = FXCollections.observableArrayList();
        cont.setEls(allItems);
        cont.setInProgress(null);
        cont.setErrorMessage(errorMessage);
        cont.setOuterPane(outerPane);
        Parent root = loader.load();

        outerPane.setCenter(root);
    }

    /**
     * sets selected project as either Complete of Incomplete and updates database
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

    public void setClientNameField(String name) {
        this.clientNameField.setText(name);
    }

    public DatePicker getStartField() {
        return this.startField;
    }

    public DatePicker getEndField() {
        return this.endField;
    }

    public void setErrorMessage(Label label) {
        this.errorMessage = label;
    }

    public void setSelected(Project proj) {
        this.selectedProject = proj;
    }

    public void setCurrLabs(String text) {
        this.currLabs.setText(text);
    }
    
    public void setOrders(String oivey){
        this.ordersLabel.setText(oivey);
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
    
    public TableView getWOTable(){
        return this.workOrdersTable;
    }

    /**
     * Code that runs when page loads, fills dropdown of available Clients and label with currently assigned labourers
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DBServices dbs = new DBServices();

        ObservableList<Client> clients = FXCollections.observableArrayList();
        ObservableList<Labourer> allLabs = FXCollections.observableArrayList();
        ObservableList<String> names = FXCollections.observableArrayList();

        clients.addAll(dbs.getClients(false));
        allLabs.addAll(dbs.getLabourersForTable());

        names.add("-- Assign Client --");

        for (int i = 0; i < clients.size(); i++) {
            names.add(clients.get(i).getFirstName() + " " + clients.get(i).getLastName());
        }

        clientDropdown.setItems(names);
        clientDropdown.getSelectionModel().select(0);

        this.col.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        this.labTable.setItems(dbs.getLabourersForTable());

        this.labTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
}
