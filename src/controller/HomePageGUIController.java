package controller;

import application.Main;
import entity.Client;
import entity.Labourer;
import entity.Project;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import services.DBServices;

/**
 * This controller handles the actions and data of the outer frame of the Stage,
 * as well as some of the pages loaded via border pane into the inner frame this
 * includes: ViewLabourerGUI.fxml ViewClientGUI.fxml HomePageGUI.fxml
 * OngoingProjectsGUI.fxml
 *
 * Pages with different controllers but still have data loaded into them while
 * data is still in scope of HomePage (outer data controller is not visible to
 * inner data controller. The pages this class provides data for, but is not the
 * controller for, includes: CreateClient.fxml when it is being used to
 * Edit a client as opposed to making a new one. CreateLabourer.fxml when
 * it is being used to Edit a labourer as opposed to making a new one.
 * ClientProfileGUI.fxml LabourerProfileGUI.fxml CreateProjectGUI.fxml _1, _2, and _3, EditProjectGUI.fxml
 */
public class HomePageGUIController extends Controller implements Initializable {

    /*==============================================Client actions==================================================*/
 /*============Outer Frame Client Dropdown===============*/
    private boolean editFlagClient = false; //indicatse user clicks edit client instead of new in outer gui, after picking from table
    private boolean viewClientProfileFlag = false; //indicates user has clicked view client in outer gui, after picking from table
    private boolean createClientFlag = false;

    /**
     *
     */
    @FXML
    protected BorderPane borderpane = new BorderPane(); //this pane is morphed to hold all inner fram pages, as opposed to changing scenes inside of a scene, which isn't possible
    @FXML
    private Label errorMessage;
    @FXML
    private Button viewClientBtn;
    @FXML
    private Button editClientBtn;
    @FXML
    private Button removeClientBtn;

    private Client selectedClient;

    private Project selectedProject;

    /**
     * When user clicks New Client button, navigates user to page where the can make new Client entity
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newClientAction(ActionEvent event) throws IOException {

        createClientFlag = true;
        navigateTo("/ui/CreateClientGUI.fxml"); //takes user to page to make new Client inside dynamic pane
    }

    /**
     * When user clicks on List Clients button, navigates to page with list of all active clients
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewClientAction(ActionEvent event) throws IOException {

        tableFlag = true;
        navigateTo("/ui/ViewClientGUI.fxml");
    }

    /**
     * When user clicks Client Profile button, navigates to page where user can view information on a selected client entity 
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewClientProfilePage(ActionEvent event) throws IOException {

        viewClientProfileFlag = true;
        navigateTo("/ui/ClientProfileGUI.fxml");
    }

    /**
     * When user clicks Edit Client button, navigates to page where user can edit details of a selected client entity
     * @param event
     */
    @FXML
    private void editClientAction(ActionEvent event) throws IOException {

        editFlagClient = true;
        navigateTo("/ui/CreateClientGUI.fxml");
    }

    /**
     * When user clicks Remove Client button, sets selected client as inactive and removes it from all views of user
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

            Client target = dbs.getClient(this.selectedClient.getFirstName(), this.selectedClient.getClientLastName());

            dbs.deleteClient(target);

            setMessage("Cllient Successfully Removed", this.errorMessage);
            navigateTo("/ui/ViewClientGUI.fxml");

        } else {
            alert.close();
        }
    }


    /*======================================Inner Frame Client Dropdown======================================*/
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
     * When user enters data into the Search Box above the Clients table, updates table with clients with attributes that match the entered data
     * @param event
     */
    @FXML
    private void searchClients(ActionEvent event) {

        String input = searchBox.getText();

        if (input != null) {

            ObservableList<Client> matches = FXCollections.observableArrayList();

            for (int i = 0; i < clientList.size(); i++) {

                input = input.toLowerCase();

                String first = clientList.get(i).getFirstName().toLowerCase();
                String last = clientList.get(i).getLastName().toLowerCase();
                String company = clientList.get(i).getCompany();
                String address = clientList.get(i).getAddress();
                String email = clientList.get(i).getEmail();
                String phone1 = clientList.get(i).getPhone1();
                String phone2 = clientList.get(i).getPhone2();

                if (company == null) {
                    company = "";
                } else {
                    company = company.toLowerCase();
                }

                if (address == null) {
                    address = "";
                } else {
                    address = address.toLowerCase();
                }

                if (email == null) {
                    email = "";
                } else {
                    email = email.toLowerCase();
                }

                if (phone1 == null) {
                    phone1 = "";
                }

                if (phone2 == null) {
                    phone2 = "";
                }

                if (first.contains(input)
                        || last.contains(input)
                        || company.contains(input)
                        || address.contains(input)
                        || email.contains(input)
                        || phone1.contains(last)
                        || phone2.contains(input)) {

                    matches.add(clientList.get(i));
                }
            }

            updateClientTable(matches);
        }
    }

    /*================================================Controls==============================================*/
    /**
     * Gets Client entity user has picked from table
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

    /**
     * Refreshes client entities represented in the Clients table
     * @param clients
     */
    private void updateClientTable(ObservableList<Client> clients) {

        if (firstNameCol != null) {

            firstNameCol.setCellValueFactory(new PropertyValueFactory<>("clientFirstName"));
            lastNameCol.setCellValueFactory(new PropertyValueFactory<>("clientLastName"));
            companyNameCol.setCellValueFactory(new PropertyValueFactory<>("company"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            firstNumCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            secondNumCol.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            DBServices dbs = new DBServices();

            if (clients != null) {

                clientTable.setItems(clients);

            } else {

                this.clientList = dbs.getClientsForTable();
                clientTable.setItems(clientList);
            }
        }
    }

    /*============================================Project Actions==============================================*/
 /*============Outer Frame Project Dropdown===============*/
    private boolean createProjectFlag = false;
    boolean newProjectFlag = false;
    private boolean editProjectFlag = false;

    @FXML
    private Button editProjectBtn;
    @FXML
    private Button removeProjectBtn;

    /**
     * When user clicks List Projects button, navigates to page with table of all available project entities
     * @throws IOException
     */
    @FXML
    private void viewProjectsAction(ActionEvent event) throws IOException {

        tableFlag = true;
        navigateTo("/ui/OngoingProjectsGUI.fxml");
    }

    /**
     * When user clicks Edit Project button, navigates user to page where details for users selected project entity can be altered
     * @param event
     */
    @FXML
    private void editProjectAction(ActionEvent event) throws IOException {

        editProjectFlag = true;
        navigateTo("/ui/EditProjectGUI.fxml");
    }

    /**
     * When user clicks New Project button, navigates to page where user can begin process if creating a new project entity
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newProjectAction(ActionEvent event) throws IOException, URISyntaxException {

        newProjectFlag = true;
        navigateTo("/ui/CreateProjectGUI_1.fxml");
    }

     /**
     * When user clicks Remove Project button, sets selected project as inactive and removes it from all views of user
     * @param event
     */
    @FXML
    private void removeProjectAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Project Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Delete Project with the name: " + selectedProject.getProjectName() + "?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            getSelectedProject();

            DBServices dbs = new DBServices();

            dbs.deleteProject(selectedProject);

            setMessage("Project Successfully Removed", this.errorMessage);
            navigateTo("/ui/OngoingProjectsGUI.fxml");
        }

    }

    /*=======================================Inner Frame Project Dropdown================================*/
    @FXML
    private TextField currentProjField;
    @FXML
    private TextField compProjField;
    @FXML
    private TableView<Project> currentProjectsTable;
    @FXML
    private TableView<Project> completedProjectsTable;
    @FXML
    private TableColumn<?, ?> currProjNameCol;
    @FXML
    private TableColumn<?, ?> currProjClientCol;
    @FXML
    private TableColumn<?, ?> currProjStartCol;
    @FXML
    private TableColumn<?, ?> currProjEndCol;
    @FXML
    private TableColumn<?, ?> currProjDescCol;
    @FXML
    private TableColumn<?, ?> comProjNameCol;
    @FXML
    private TableColumn<?, ?> comProjClientCol;
    @FXML
    private TableColumn<?, ?> comProjStartCol;
    @FXML
    private TableColumn<?, ?> comProjEndCol;
    @FXML
    private TableColumn<?, ?> comProjDescCol;

    private ObservableList<Project> currProjList;
    private ObservableList<Project> compProjList;

    /**
     * When user enters data into the Search Box above the Completed Projects table, updates table with projects with attributes that match the entered data
     * @param event
     */
    @FXML
    private void searchCompProj(ActionEvent event) {

        String input = compProjField.getText().toLowerCase();

        if (input != null) {

            ObservableList<Project> matches = FXCollections.observableArrayList();

            for (int i = 0; i < compProjList.size(); i++) {

                String name = compProjList.get(i).getProjectName().toLowerCase();
                String client = compProjList.get(i).getClientName().toLowerCase();
                String startDate = compProjList.get(i).getStartDate() + "";
                String endDate = compProjList.get(i).getEndDate() + "";
                String desc = compProjList.get(i).getDescription();

                if (name == null) {
                    name = "";
                }
                if (client == null) {
                    client = "";
                }
                if (startDate == null) {
                    startDate = "";
                }
                if (endDate == null) {
                    endDate = "";
                }
                if (desc == null) {
                    desc = "";
                }

                if (name.contains(input)
                        || client.contains(input)
                        || startDate.contains(input)
                        || endDate.contains(input)
                        || desc.contains(input)) {

                    matches.add(compProjList.get(i));
                }
            }
            updateCompleteProjectTable(matches);
        }
    }

    /**
     * When user enters data into the Search Box above the Current Projects table, updates table with projects with attributes that match the entered data
     * @param event
     */
    @FXML
    private void searchCurrProj(ActionEvent event) throws InstantiationException, IllegalAccessException {

        String input = currentProjField.getText().toLowerCase();

        if (input != null) {

            ObservableList<Project> matches = FXCollections.observableArrayList();

            for (int i = 0; i < currProjList.size(); i++) {

                String name = currProjList.get(i).getProjectName().toLowerCase();
                String client = currProjList.get(i).getClientName().toLowerCase();
                String startDate = currProjList.get(i).getStartDate() + "";
                String endDate = currProjList.get(i).getEndDate() + "";
                String desc = currProjList.get(i).getDescription();

                if (name == null) {
                    name = "";
                }
                if (client == null) {
                    client = "";
                }
                if (startDate == null) {
                    startDate = "";
                }
                if (endDate == null) {
                    endDate = "";
                }
                if (desc == null) {
                    desc = "";
                }

                if (name.contains(input)
                        || client.contains(input)
                        || startDate.contains(input)
                        || endDate.contains(input)
                        || desc.contains(input)) {

                    matches.add(currProjList.get(i));
                }
            }

            updateProjectTable(matches);
        }
    }

    /**
     * Refreshes project entities shown in Current Projects table
     * @param newList
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void updateProjectTable(ObservableList<Project> newList) throws InstantiationException, IllegalAccessException {

        if (currProjNameCol != null) {

            currProjNameCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            currProjClientCol.setCellValueFactory(new PropertyValueFactory<>("clientName"));
            currProjStartCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            currProjEndCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            currProjDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            DBServices dbs = new DBServices();

            if (newList != null) {

                currentProjectsTable.setItems(newList);

            } else {

                ObservableList<Project> all = dbs.getAllProjectsForTable();
                ObservableList<Project> list = FXCollections.observableArrayList();

                for (int i = 0; i < all.size(); i++) {

                    if (all.get(i).getCompleted() == false) {

                        list.add(all.get(i));
                    }

                }

                this.currProjList = list;
                currentProjectsTable.setItems(currProjList);
            }
        }
    }

    /**
     * Refreshes project entities shown in Completed Projects table
     * @param newList
     */
    public void updateCompleteProjectTable(ObservableList<Project> newList) {

        if (comProjNameCol != null) {

            comProjNameCol.setCellValueFactory(new PropertyValueFactory<>("projectName"));
            comProjClientCol.setCellValueFactory(new PropertyValueFactory<>("clientName"));
            comProjStartCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            comProjEndCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            comProjDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            DBServices dbs = new DBServices();

            if (newList != null) {

                completedProjectsTable.setItems(newList);

            } else {

                ObservableList<Project> all = dbs.getAllProjectsForTable();
                ObservableList<Project> list = FXCollections.observableArrayList();

                for (int i = 0; i < all.size(); i++) {

                    if (all.get(i).getCompleted() == true) {

                        list.add(all.get(i));
                    }

                }

                this.compProjList = list;
                completedProjectsTable.setItems(compProjList);
            }
        }
    }

    /*============Controls===============*/
    /**
     * Gets project entity selected by user from Current Projects table
     */
    private void getSelectedProject() {

        if (currentProjectsTable.getSelectionModel().getSelectedItem() != null) {

            this.selectedProject = currentProjectsTable.getSelectionModel().getSelectedItem();

            //Enable buttons once project is selected
            editProjectBtn.setDisable(false);
            removeProjectBtn.setDisable(false);

        }

    }

    /**
     * Gets project entity selected by user from Completed Projects table
     */
    private void getSelectedComp() {

        if (completedProjectsTable.getSelectionModel().getSelectedItem() != null) {

            this.selectedProject = completedProjectsTable.getSelectionModel().getSelectedItem();

            //Enable buttons once project is selected
            editProjectBtn.setDisable(false);
            removeProjectBtn.setDisable(false);

        }

    }


    /*==============================================Labourer Actions============================================*/
 /*============Outer Frame Labourer Dropdown===============*/
    private boolean editLabourerFlag = false;
    private boolean viewLabourerProfileFlag = false;
    private boolean createLabourerFlag = false;

    /**
     * When user clicks on New Laborer button, navigates to page where user can create a new Labourer entity
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newLabourerAction(ActionEvent event) throws IOException, URISyntaxException {

        createLabourerFlag = true;
        navigateTo("/ui/CreateLabourerGUI.fxml");
    }

    /**
     * When user clicks Remove Labourer button, sets selected project as inactive and removes it from all views of user
     * @param event
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

        } else {
            alert.close();
        }
    }

    /**
     * When user clicks Edit Labourer button, navigates user to page where details for users selected project entity can be altered
     * @param event
     */
    @FXML
    private void editLabourerAction() throws IOException {

        editLabourerFlag = true;
        navigateTo("/ui/CreateLabourerGUI.fxml");
    }

    /**
     * When user clicks Labourer Profile button, navigates to page where user can view information on a selected client entity 
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewLabourerProfilePage() throws IOException {

        viewLabourerProfileFlag = true;
        navigateTo("/ui/LabourerProfileGUI.fxml");
    }

    /**
     * When user clicks on List Labourers button, navigates to page with list of all active labourers
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewLabourersAction(ActionEvent event) throws IOException, URISyntaxException {

        tableFlag = true;
        navigateTo("/ui/ViewLabourerGUI.fxml");
    }

    /*======================================Inner Frame Labourer Dropdown=====================================*/
    @FXML
    private TableView<Labourer> labourerTable; //the table loading the attributes of the labourers

    private ObservableList<Labourer> labourerList; //all active labourers from database

    private Labourer selectedLabourer; //a labourer from the table the user has clicked on

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
    @FXML
    private TextField labSearchBox;

    /*===============================================Controls============================================*/
    /**
     * gets the labourer objects based on what user clicked in labourers table
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
     * When user enters data into the Search Box above the Labourers table, updates table with labourers with attributes that match the entered data
     * @param event
     */
    @FXML
    private void searchLabourers(ActionEvent event) {

        String input = labSearchBox.getText();

        if (input != null) {

            ObservableList<Labourer> matches = FXCollections.observableArrayList();

            for (int i = 0; i < labourerList.size(); i++) {

                input = input.toLowerCase();

                String first = labourerList.get(i).getFirstName().toLowerCase();
                String last = labourerList.get(i).getLastName().toLowerCase();
                String address = labourerList.get(i).getAddress();
                String email = labourerList.get(i).getEmail();
                String phone1 = labourerList.get(i).getPhone1();
                String phone2 = labourerList.get(i).getPhone2();

                if (address == null) {
                    address = "";
                } else {
                    address = address.toLowerCase();
                }

                if (email == null) {
                    email = "";
                } else {
                    email = email.toLowerCase();
                }

                if (phone1 == null) {
                    phone1 = "";
                }

                if (phone2 == null) {
                    phone2 = "";
                }

                if (first.contains(input)
                        || last.contains(input)
                        || address.contains(input)
                        || email.contains(input)
                        || phone1.contains(last)
                        || phone2.contains(input)) {

                    matches.add(labourerList.get(i));
                }
            }

            updateLabourerTable(matches);
        }

    }

    /**
     * Updates data presented in Labourers table
     * @param clients
     */
    private void updateLabourerTable(ObservableList<Labourer> labs) {

        if (fNameColLab != null) {
            fNameColLab.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lNameColLab.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            addressColLab.setCellValueFactory(new PropertyValueFactory<>("address"));
            firstNumColLab.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            secondNumColLab.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            emailColLab.setCellValueFactory(new PropertyValueFactory<>("email"));
            DBServices dbs = new DBServices();

            if (labs != null) {

                labourerTable.setItems(labs);

            } else {

                this.labourerList = dbs.getLabourersForTable();
                labourerTable.setItems(labourerList);
            }
        }
    }

    /*==============================================Menu Bar  Controls=============================================*/
    private boolean constantsFlag = false;

    /**
     * When user clicks User Constants under Edit in menu bar, allows user to set rates of values that are used repeatedly in calculations
     *
     * @param event
     */
    @FXML
    private void constantsAction(ActionEvent event) throws IOException {

        constantsFlag = true;
        navigateTo("/ui/ConstantsGUI.fxml");
    }

    /**
     * When user clicks Restore.. button under Database in menu bar, allows them to pick an SQL file of a previously made database backup
     * @param event
     */
    @FXML
    private void restoreAction(ActionEvent event) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SQL Files(*.sql)", "*.sql");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(Main.stage);

        DBServices dbs = new DBServices();

        try {
            dbs.restore(file.getAbsolutePath());
        } catch (NullPointerException e) {
            //want it to ignore, errors when you cancel the window for some reason
        }

        navigateHome(null);
    }

    /**
     * When user clicks Bakcup.. button under Database in menu bar, allows them to pick a windows directory to save a backup script of the database
     * @param event
     */
    @FXML
    private void backupAction(ActionEvent event) {

        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Choose Backup Location");
        File path = dc.showDialog(Main.stage);

        DBServices dbs = new DBServices();

        try {
            dbs.backup(path.getAbsolutePath());
        } catch (NullPointerException e) {
            //want it to ignore, errors when you cancel the backup for some reason
        }
    }


    /*============================================Home Page Controls===============================================*/
    private boolean tableFlag = false;

    /**
     * For use when application first starts, updates tables in Ongoing Projects when Main runs
     * @param state
     */
    public void setTableFlag(Boolean state) {
        this.tableFlag = state;
    }

    /**
     * Primary means of changing pages of the inner panel of the app.
     * Controllers of FXML pages in the inner frame cannot access Home
     * Controller so we set the variables the user is loading on to the inner
     * page here as opposed to loading scenes inside of the already outer scene
     * from the inner controller, which isn't possible.
     *
     * @param url
     * @throws IOException
     */
    public void navigateTo(String url) throws IOException {

        disableButtons();

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
            ccgc.setFirstName(selectedClient.getFirstName());
            ccgc.setLastName(selectedClient.getLastName());
            ccgc.setCompanyName(selectedClient.getCompany());
            ccgc.setPhone1Field(selectedClient.getPhone1());
            ccgc.setPhone2Field(selectedClient.getPhone2());
            ccgc.setEmailField(selectedClient.getEmail());
            ccgc.setAddressField(selectedClient.getAddress());
            ccgc.setNotesField(selectedClient.getDescription());
            ccgc.setSelected(selectedClient);
            ccgc.setErrorMessage(errorMessage);

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
            clgc.setErrorMessage(errorMessage);
            editLabourerFlag = false;
        }

        if (editProjectFlag == true) {

            EditProjectGUIController epgc = loader.getController();

            if (selectedProject.getCompleted() == false) {
                epgc.setCompletedBtn("Set Project Completed");
            } else {
                epgc.setCompletedBtn("Set Project Incomplete");
            }

            DecimalFormat f = new DecimalFormat("#.00");

            epgc.setSelected(selectedProject);
            epgc.setQuote(f.format(selectedProject.getQuote()));
            epgc.setFinal(f.format(selectedProject.getActualCost()));
            epgc.setAddressField(selectedProject.getSiteAddress());
            epgc.setNameField(selectedProject.getProjectName());
            epgc.setErrorMessage(errorMessage);
            epgc.setOuterPane(this.borderpane);
            epgc.setNotesField(selectedProject.getDescription());
            epgc.getStartField().setValue(selectedProject.getStartDate());
            epgc.getEndField().setValue(selectedProject.getEndDate());
            epgc.setClientNameField(selectedProject.getClientName());
            
            String almost = "";
            
            for(int i = 0; i < selectedProject.getWorkOrders().size(); i++){
                
                almost += selectedProject.getWorkOrders().get(i).getName() + ":         Quoted: ";
                almost += selectedProject.getWorkOrders().get(i).getQuotedTotal() + "   Actual: ";
                almost += selectedProject.getWorkOrders().get(i).getActualTotal() + "\n";
            
            }
            
            String names = "";
            
            for(int i = 0; i < selectedProject.getLabourers().size(); i++){
            
                names += selectedProject.getLabourers().get(i).getFullName() + "\n";
            
            }
            epgc.setCurrLabs(names);
            
            epgc.setOrders(almost);
            
            editProjectFlag = false;
        }

        //if (editWorkOrdersFlag == true{} todo
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
            lpgc.setErrorMessage(errorMessage);
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
            cpgc.setErrorMessage(errorMessage);
            viewClientProfileFlag = false;
        }

        if (newProjectFlag == true) {

            CreateProjectGUIController cpgc = loader.getController();
            cpgc.setOuterPane(this.borderpane);
            cpgc.setErrorMessage(errorMessage);
            newProjectFlag = false;
        }

        if (tableFlag == true) {

            reloadTables(root);
            tableFlag = false;
        }

        //need the view client/labourer tables to be reloaded duringt all page navigation to keep the data fresh
        if (constantsFlag == true) {

            ConstantsGUIController cgc = loader.getController();
            cgc.setErrorMessage(errorMessage);
            constantsFlag = false;
        }

        if (createClientFlag == true) {

            CreateClientGUIController ccgc = loader.getController();
            ccgc.setErrorMessage(errorMessage);
            createClientFlag = false;
        }

        if (createLabourerFlag == true) {

            CreateLabourerGUIController clgc = loader.getController();
            clgc.setErrorMessage(errorMessage);
            createLabourerFlag = false;
        }

        if (createProjectFlag == true) {

            CreateProjectGUIController cpgc = loader.getController();
            cpgc.setErrorMessage(errorMessage);
            cpgc.setOuterPane(borderpane);
            createProjectFlag = false;
        }


        this.borderpane.setCenter(root);
    }

    /**
     * For when the User clicks on the Visual Landscaping logo, takes user back to OngoingProjectsGUI.fxml
     * @param event
     * @throws IOException
     */
    @FXML
    private void navigateHome(MouseEvent event) throws IOException {

        navigateTo("/ui/OngoingProjectsGUI.fxml");
    }

    /**
     * Refreshes objects for tables that builds the data of clients, labourers, and projects in respective tables
     *
     * @param root
     */
    public void reloadTables(Parent root) {

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

        try {
            node = root.lookup("#currentProjectsTable");
        } catch (NullPointerException e) {
            //ignore
        }

        if (node != null) {

            this.currentProjectsTable = (TableView<Project>) root.lookup("#currentProjectsTable");
            this.currentProjectsTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getSelectedProject();
                }
            });
        }

        //Node newNode = null;
        try {
            node = root.lookup("#completedProjectsTable");
        } catch (NullPointerException e) {
            //ignore
        }

        if (node != null) {

            this.completedProjectsTable = (TableView<Project>) root.lookup("#completedProjectsTable");
            this.completedProjectsTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getSelectedComp();
                }
            });
        }
    }

    /**
     * When user navigates app out of scope of edit/remove/view buttons for any entity
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

        editProjectBtn.setDisable(true);
        removeProjectBtn.setDisable(true);
    }

    /**
     * Refreshes banner message label object 
     * @param error
     */
    protected void setErrorMessage(Label error) {
        this.errorMessage = error;
    }

    /**
     * Refreshes entities represented in all tables
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected void updateTables() throws InstantiationException, IllegalAccessException {

        this.updateClientTable(null); //for viewing all clients in a table   
        this.updateLabourerTable(null); //for viewing all labourer in a table
        this.updateProjectTable(null);// for viewing all projects in a table
        this.updateCompleteProjectTable(null);

    }

    /**
     * Code that runs when page loads, only runs once when app is launched, loads table objects and data when Main runsS
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            updateTables();
        } catch (InstantiationException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
