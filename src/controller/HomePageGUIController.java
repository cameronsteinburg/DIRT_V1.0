/*
 * 
 *  This is the class that controls what happens when the user is on the apps main page
 */
package controller;

import entity.Client;
import java.io.IOException;
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

public class HomePageGUIController implements Initializable {

    

    
    /*==========================Client actions=============================*/
    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newClientAction(ActionEvent event) throws IOException {

        navigateTo("/ui/CreateClientGUI.fxml"); //takes user to page to make new Client inside dynamic pane
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewClientAction(ActionEvent event) throws IOException {

        navigateTo("/ui/ViewClientGUI.fxml");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void editClientAction(ActionEvent event) throws IOException {

        navigateTo("/ui/CreateClientGUI.fxml");
    }

    /**
     *
     * @param event
     */
    @FXML
    private void removeClientAction(ActionEvent event) {

        //    if (this.selectedClient != null) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Client Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Delete client with the name: " + selectedClient.getName() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            DBServices dbs = new DBServices();

            Client target = dbs.getClient(this.selectedClient.getName());
            String name = target.getName();
            dbs.deleteClient(target);
            this.updateTable();
            this.errorMessage.setText("Client Successfully Removed");
        } else {
            alert.close();
        }
        //  }
    }
     /*============Outer Frame Client Dropdown===============*/
    
    @FXML
    protected BorderPane borderpane = new BorderPane(); //the only thing that naviagtes pages
    @FXML
    private Label errorMessage = new Label();
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
    private TableColumn<?, ?> nameCol;
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
     */
    //  @FXML
    private void getSelectedClient() {

        if (clientTable.getSelectionModel().getSelectedItem() != null) {

            this.selectedClient = clientTable.getSelectionModel().getSelectedItem();

            //Enable buttons once client is selected
            viewClientBtn.setDisable(false);
            editClientBtn.setDisable(false);
            removeClientBtn.setDisable(false);
        }
    }

    private void updateTable() {

        if (nameCol != null) {

            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            firstNumCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
            secondNumCol.setCellValueFactory(new PropertyValueFactory<>("phone2"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            DBServices dbs = new DBServices();
            this.clientList = dbs.getClientsForTable();
            clientTable.setItems(clientList);
        }
    }

    /*==========================Project Actions==========================*/
    
    /*============Outer Frame Project Dropdown===============*/
    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newProjectAction(ActionEvent event) throws IOException, URISyntaxException {

        navigateTo("/ui/CreateProjectGUI.fxml");
    }

    /*==========================Labourer Actions==========================*/
    
    /*============Outer Frame Labourer Dropwdown===============*/
    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newLabourerAction(ActionEvent event) throws IOException, URISyntaxException {

        navigateTo("/ui/CreateLabourerGUI.fxml");
    }

    /**
     * 
     * @param event
     * @throws IOException
     * @throws URISyntaxException 
     */
    @FXML
    private void viewLabourersAction(ActionEvent event) throws IOException, URISyntaxException {

        navigateTo("/ui/ViewLabourerGUI.fxml");
    }

   

    /*==========================Home Page Controls==========================*/
    /**
     *
     * @param url
     */
    public void navigateTo(String url) throws IOException {

        Parent root = null;
        try {
            root = FXMLLoader.load(HomePageGUIController.class.getClass().getResource(url));
        } catch (IOException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        reloadResources(root);

        this.borderpane.setCenter(root);
        // 
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
     * when user is redirected to another page and a message to the user is
     * necessary to indicate action
     *
     * @param url
     * @param message
     * @throws IOException
     */
    public void navigateTo(String url, String message) throws IOException {

        navigateTo(url);
        this.errorMessage.setText(message);
    }

    /**
     * 
     * @param root 
     */
    private void reloadResources(Parent root) {
        //outer buttons
 

        //client table for viewing all clients
        if (root.lookup("#clientTable") != null) {

            this.clientTable = (TableView<Client>) root.lookup("#clientTable");
            this.clientTable.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    getSelectedClient();
                }
            });
        }

        //client profile
        //edit client
        //labourer table
        //labourer profile
        //edit labourer 
    }

    /**
     *Loading stuff into any given page the HomePageGUIController controls
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.updateTable(); //for viewing clients   
    }
}
