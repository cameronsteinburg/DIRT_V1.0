/*
 * 
 *  This is the class that controls what happens when the user is on the apps main page
 */
package controller;

import application.Main;
import entity.Client;
import entity.Labourer;
import entity.Project;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import services.DBServices;

public class HomePageGUIController implements Initializable {

    //Current Cleint's projects, labourers to be loaded
    ArrayList<Client> curClients;
    ArrayList<Labourer> curLabourers;
    ArrayList<Project> curProjects;

    //elements from the GUI.fxml page
    @FXML
    private TextField clientsNameField = new TextField();

    //Client actions
    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newClientAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateClientGUI.fxml"); //takes user to page to make new Client
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void viewClientAction(ActionEvent event) throws IOException {

        System.out.println("Rocket Racoon");
    }

    //Project Actions
    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newProjectAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateProjectGUI.fxml"); //takes user to page to make new Project
    }

    //Labourer Actions
    /**
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void newLabourerAction(ActionEvent event) throws IOException, URISyntaxException {

        StageController.control.navigateTo("/ui/CreateLabourerGUI.fxml"); //takes user to page to make new Project
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

        this.curClients = dbs.getClients();

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
