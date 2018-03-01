/*
 * 
 *  This is the class that controls what happens when the user is on the apps main page
 */
package controller;

import entity.Client;
import entity.Labourer;
import entity.Project;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import services.DBServices;

public class HomePageGUIController implements Initializable {

    //Current Cleint's projects, labourers to be loaded
    ArrayList<Client> curClients;
    ArrayList<Labourer> curLabourers;
    ArrayList<Project> curProjects;

    @FXML
    private Button viewLabourerBtn;
    //for error messages or did indicate a client has completed an action. Sits in top banner
    @FXML
    private static Label actionPerformedLabel = new Label("");

    //elements from the GUI.fxml page
    @FXML
    private TextField clientsNameField = new TextField();
    @FXML
    private BorderPane borderpane; //the only thing that naviagtes pages

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

    /*==========================Project Actions==========================*/
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

    @FXML
    private void viewLabourersAction(ActionEvent event) throws IOException, URISyntaxException {

        navigateTo("/ui/ViewLabourerGUI.fxml");
    }

    /*==========================Home Page Controls==========================*/
    /**
     *
     * @param url
     */
    protected void navigateTo(String url) {

        Parent root = null;

        try {
            root = FXMLLoader.load(HomePageGUIController.class.getClass().getResource(url));
        } catch (IOException ex) {
            Logger.getLogger(HomePageGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.borderpane.setCenter(root);

    }

    /**
     * For changing the label to indicate to suer they have
     * unsuccessfully/successfully performed an action, or to indicate an error
     * has occurred
     *
     * @param message
     */
    public static void setBannerMessage(String message) {

        HomePageGUIController.actionPerformedLabel.setText(message);
    }

    /**
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {
        // put stuff in this method as if its the onLoad of the fxml page
        //stuff we want to happen when the page opens like loading information from the db to the page

        DBServices dbs = new DBServices(); //loading in db entity resources

        this.curClients = dbs.getClients(false);

        TextFlow tf;
        ArrayList<String> names = new ArrayList<String>();

        for (int i = 0; i < curClients.size(); i++) {

            names.add(curClients.get(i).getName());
            //clientsNameField.appendText(curClients.get(i).getName());
            //clientsNameArea.ap
        }

        String fill;

        //for (int i = 0; )
    }
}
