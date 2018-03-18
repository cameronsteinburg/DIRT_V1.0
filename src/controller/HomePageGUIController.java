/*
 * 
 *  This is the class that controls what happens when the user is on the apps main page
 */
package controller;

import entity.Client;
import entity.Labourer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import services.DBServices;

public class HomePageGUIController extends Controller implements Initializable {

    /*======================================Client actions======================================*/
 /*============Outer Frame Client Dropdown===============*/
    private boolean editFlagClient = false; //in case user clicks edit
    private boolean viewClientProfileFlag = false; //in case user wants to see a clients profile page

    @FXML
    protected BorderPane borderpane = new BorderPane(); //the only thing that naviagtes pages
    @FXML
    private Label errorMessage;
    @FXML
    private Button viewClientBtn;
    @FXML
    private Button editClientBtn;
    @FXML
    private Button removeClientBtn;

    private Client selectedClient;

    /*============Inner Frame Client Dropdown===============*/
    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TextField searchBox;
    @FXML
    private TableColumn<?, ?> firstNameCol;
    @FXML
    private TableColumn<?, ?> lastNameCol;
    @FXML
    private TableColumn<?, ?> companyNameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> firstNumCol;
    @FXML
    private TableColumn<?, ?> secondNumCol;
    @FXML
    private TableColumn<?, ?> emailCol;

    private ObservableList<Client> clientList;

    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newClientAction(ActionEvent event) throws IOException {

        navigateTo("/ui/CreateClientGUI.fxml"); //takes user to page to make new Client inside dynamic pane
        disableButtons();
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewClientAction(ActionEvent event) throws IOException {

        disableButtons();
        navigateTo("/ui/ViewClientGUI.fxml");
    }

    @FXML
    private void viewClientProfilePage(ActionEvent event) throws IOException {

        viewClientProfileFlag = true;
        navigateTo("/ui/ClientProfileGUI.fxml");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void editClientAction(ActionEvent event) throws IOException {

        editFlagClient = true;
        navigateTo("/ui/CreateClientGUI.fxml");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void removeClientAction(ActionEvent event) throws MalformedURLException, IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Client Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Delete client with the name: " + selectedClient.getFirstName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            DBServices dbs = new DBServices();

            Client target = dbs.getClient(this.selectedClient.getFirstName());

            dbs.deleteClient(target);

            setMessage("Cllient Successfully Removed", this.errorMessage);
            navigateTo("/ui/ViewClientGUI.fxml");
            disableButtons();
        } else {
            alert.close();
        }
    }

    /*============Controls===============*/
    /**
     *
     * @param event
     */
    private void getSelectedClient() {

        if (clientTable.getSelectionModel().getSelectedItem() != null) {

            this.selectedClient = clientTable.getSelectionModel().getSelectedItem();

            //Enable buttons once client is selected
            viewClientBtn.setDisable(false);
            editClientBtn.setDisable(false);
            removeClientBtn.setDisable(false);
        }
    }

    private void updateClientTable() {

        if (firstNameCol != null) {

            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("clientFirstName"));
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("clientLastName"));
            companyNameCol.setCellValueFactory(new PropertyValueFactory<>("company"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            firstNumCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            secondNumCol.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            DBServices dbs = new DBServices();
            this.clientList = dbs.getClientsForTable();
            clientTable.setItems(clientList);
        }
    }

    /*======================================Project Actions======================================*/
 /*============Outer Frame Project Dropdown===============*/
    /**
     *
     * @throws IOException
     */
    @FXML
    private void viewProjectsAction() throws IOException {

        navigateTo("/ui/OngoingProjectsGUI.fxml");
    }

    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newProjectAction(ActionEvent event) throws IOException, URISyntaxException {

        navigateTo("/ui/CreateProjectGUI_1.fxml");
    }

    private void updateProjectTable() {

    }

    /*============Inner Frame Project Dropdown===============*/
    @FXML
    private TableView projectsTable;

    /*======================================Labourer Actions======================================*/
 /*============Outer Frame Labourer Dropdown===============*/
    private boolean editLabourerFlag = false;
    private boolean viewLabourerProfileFlag = false;

    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newLabourerAction(ActionEvent event) throws IOException, URISyntaxException {

        disableButtons();
        navigateTo("/ui/CreateLabourerGUI.fxml");
    }

    /**
     *
     */
    private void getSelectedLabourer() {

        if (labourerTable.getSelectionModel().getSelectedItem() != null) {

            this.selectedLabourer = labourerTable.getSelectionModel().getSelectedItem();

            //Enable buttons once client is selected
            viewLabourerProfileBtn.setDisable(false);
            editLabourerBtn.setDisable(false);
            removeLabourerBtn.setDisable(false);
        }
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void removeLabourerAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Labourer Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Delete labourer with the name: " + selectedLabourer.getFirstName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            DBServices dbs = new DBServices();

            Labourer target = dbs.getLabourer(this.selectedLabourer.getFirstName());

            dbs.deleteLabourer(target);
            setMessage("Labourer Successfully Removed", this.errorMessage);
            navigateTo("/ui/ViewLabourerGUI.fxml");
            disableButtons();
        } else {
            alert.close();
        }

    }

    /**
     *
     * @throws IOException
     */
    @FXML
    private void editLabourerAction() throws IOException {

        editLabourerFlag = true;
        navigateTo("/ui/CreateLabourerGUI.fxml");
    }

    /**
     *
     */
    @FXML
    private void viewLabourerProfilePage() throws IOException {

       viewLabourerProfileFlag = true;
       navigateTo("/ui/LabourerProfileGUI.fxml");
    }

    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void viewLabourersAction(ActionEvent event) throws IOException, URISyntaxException {

        disableButtons();
        navigateTo("/ui/ViewLabourerGUI.fxml");
    }

    /*============Inner Frame Labourer Dropdown===============*/
    @FXML
    private TableView<Labourer> labourerTable;

    private ObservableList<Labourer> labourerList;

    private Labourer selectedLabourer;

    @FXML
    private Button viewLabourerProfileBtn;
    @FXML
    private Button editLabourerBtn;
    @FXML
    private Button removeLabourerBtn;
    @FXML
    private TableColumn<?, ?> fNameColLab;
    @FXML
    private TableColumn<?, ?> lNameColLab;
    @FXML
    private TableColumn<?, ?> addressColLab;
    @FXML
    private TableColumn<?, ?> firstNumColLab;
    @FXML
    private TableColumn<?, ?> secondNumColLab;
    @FXML
    private TableColumn<?, ?> emailColLab;

    /*============Controls===============*/
    private void updateLabourerTable() {

        if (fNameColLab != null) {
            fNameColLab.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lNameColLab.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            addressColLab.setCellValueFactory(new PropertyValueFactory<>("address"));
            firstNumColLab.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            secondNumColLab.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            emailColLab.setCellValueFactory(new PropertyValueFactory<>("email"));
            DBServices dbs = new DBServices();
            this.labourerList = dbs.getLabourersForTable();
            labourerTable.setItems(labourerList);
        }
    }

    /*======================================Home Page Controls======================================*/
    @FXML
    private Label selectedField;

    /**
     * Primary means of changing pages of the inner panel of the app
     * 
     * Controllers of fxml pages in the inner frame cannot access Home Controller
     * so we set the variables the user is loading on to the page here as opposed to 
     * loading scenes inside of the already outer scene, which isn't possible
     *
     * @param url
     * @throws IOException
     */
    public void navigateTo(String url) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //if user is editing a client
        if (editFlagClient == true) {

            CreateClientGUIController ccgc = loader.getController();
            ccgc.setTitleField("Edit Client");
            ccgc.setSelected(selectedClient);
            ccgc.switchButtons();
            ccgc.setFirstName(selectedClient.getFirstName());
            ccgc.setLastName(selectedClient.getLastName());
            ccgc.setCompanyName(selectedClient.getCompany());
            ccgc.setPhone1Field(selectedClient.getPhone1());
            ccgc.setPhone2Field(selectedClient.getPhone2());
            ccgc.setEmailField(selectedClient.getEmail());
            ccgc.setAddressField(selectedClient.getAddress());
            ccgc.setNotesField(selectedClient.getDescription());
            ccgc.setSelected(selectedClient);
            editFlagClient = false;
        }

        //if user is editing a labourer
        if (editLabourerFlag == true) {

            CreateLabourerGUIController clgc = loader.getController();
            clgc.setSelected(selectedLabourer);
            clgc.setTitleLable("Edit Labourer");
            clgc.switchButtons();
            clgc.setFnameField(selectedLabourer.getFirstName());
            clgc.setLnameField(selectedLabourer.getLastName());
            clgc.setTitleField(selectedLabourer.getTitle());
            clgc.setPhone1Field(selectedLabourer.getPhone1());
            clgc.setPhone2Field(selectedLabourer.getPhone2());
            clgc.setEmailField(selectedLabourer.getEmail());
            clgc.setAddressField(selectedLabourer.getAddress());
            clgc.setEmergencyNameField(selectedLabourer.getEmergContactName());
            clgc.setEmergencyPhone1Field(selectedLabourer.getEmergContactPhone1());
            clgc.setEmergencyPhone2Field(selectedLabourer.getEmergContactPhone2());
            clgc.setSinField(selectedLabourer.getSin());
            clgc.setWageField(selectedLabourer.getWage());

            editLabourerFlag = false;
        }

        //if the user is viewing the profile page of a labourer
        if (viewLabourerProfileFlag == true) {

            LabourerProfileGUIController lpgc = loader.getController();
            lpgc.setTitleLable(selectedLabourer.getFirstName() + " " + selectedLabourer.getLastName());
            lpgc.setTitleField(selectedLabourer.getTitle());
            lpgc.setPhone1Field(selectedLabourer.getPhone1());
            lpgc.setPhone2Field(selectedLabourer.getPhone2());
            lpgc.setEmailField(selectedLabourer.getEmail());
            lpgc.setAddressField(selectedLabourer.getAddress());
            lpgc.setEmergencyNameField(selectedLabourer.getEmergContactName());
            lpgc.setEmergencyPhone1Field(selectedLabourer.getEmergContactPhone1());
            lpgc.setEmergencyPhone2Field(selectedLabourer.getEmergContactPhone2());
            lpgc.setSinField(selectedLabourer.getSin());
            lpgc.setWageField(selectedLabourer.getWage());
            
            viewLabourerProfileFlag = false;
        }

        if (viewClientProfileFlag == true) {

            //if the user is viewing the profile page of a client
            ClientProfileGUIController cpgc = loader.getController();
            String name = selectedClient.getFirstName() + " " + selectedClient.getLastName();
            cpgc.setName(name);
            String company = selectedClient.getCompany();
            cpgc.setCompanyName(company);
            String phone1 = selectedClient.getPhone1();
            cpgc.setPhone1Field(phone1);
            String phone2 = selectedClient.getPhone2();
            cpgc.setPhone2Field(phone2);
            String email = selectedClient.getEmail();
            cpgc.setEmailField(email);
            String address = selectedClient.getAddress();
            cpgc.setAddressField(address);
            String notes = selectedClient.getDescription();
            cpgc.setNotesField(notes);
            cpgc.setSelected(selectedClient);
            viewClientProfileFlag = false;
        }

        //need the view client/labourer tables to be reloaded duringt all page navigation to keep the data fresh
        reloadTables(root);
        this.borderpane.setCenter(root);
    }

  
    /**
     * For when the User clicks on the Visual Landscaping logo
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void navigateHome(MouseEvent event) throws IOException {

        navigateTo("/ui/OngoingProjectsGUI.fxml");
    }

    /**
     *  Refreshes and builds the data of clients and labourers in tables
     * @param root
     */
    private void reloadTables(Parent root) {

        Node node = null;

        //client table for viewing all clients currently in existance
        try {
            node = root.lookup("#clientTable");
        } catch (NullPointerException e) {
            //ignore
        }

        if (node != null) {

            this.clientTable = (TableView<Client>) root.lookup("#clientTable");
            this.clientTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getSelectedClient();
                }
            });
        }

        //labourer table for viewing all labourers currently in existance
        try {
            node = root.lookup("#labourerTable");
        } catch (NullPointerException e) {
            //ignore
        }

        if (node != null) {

            this.labourerTable = (TableView<Labourer>) root.lookup("#labourerTable");
            this.labourerTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getSelectedLabourer();
                }
            });
        }
    }

    

    /**
     * When user navigates app out of scope of edit/remove/view buttons for any
     * entity
     */
    @FXML
    private void disableButtons() {

        //todo add more buttons here as the app grows, more will need to be made as we go
        viewClientBtn.setDisable(true);
        editClientBtn.setDisable(true);
        removeClientBtn.setDisable(true);

        viewLabourerProfileBtn.setDisable(true);
        editLabourerBtn.setDisable(true);
        removeLabourerBtn.setDisable(true);
    }

    /**
     * Loading stuff into any given page the HomePageGUIController controls
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.updateClientTable(); //for viewing all clients in a table   
        this.updateLabourerTable(); //for viewing all labourer in a table
    }
}
