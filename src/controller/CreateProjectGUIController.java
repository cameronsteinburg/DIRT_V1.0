/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new client i.e /ui/CreateProjectGUI.fxml
    Also controls child pages of /ui/CreateProjectGUI.fxml
 */
package controller;

import entity.Client;
import entity.Project;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import services.DBServices;

public class CreateProjectGUIController extends Controller implements Initializable {

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker prelimStart;
    @FXML
    private DatePicker estEnd;
    @FXML
    private ChoiceBox clientDropdown;
    @FXML
    private TextField addressField;
    @FXML
    private TextArea notesField;

    private Label errorMessage;

    private BorderPane outerPane;
    
    private Project inProgress;
    
    
    public Label getErrorMessage(){
        return this.errorMessage;
    }

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void nextBtnAction(ActionEvent event) throws IOException { //User attempts to save their details entered in fields in CreateProjectGUI.fxml


        //get the User's data they entered into GUI fields
        LocalDate rawPrelim = prelimStart.getValue();
        LocalDate rawEst = estEnd.getValue();

        String name = nameField.getText();
        String address = addressField.getText();
        String description = notesField.getText();

        //data validation commences 
        if (name.isEmpty() || rawPrelim == null || rawEst == null) { //checking to see if the user entered blank data for not null db attributes

            setMessage("* Required Fields Cannot Be Left Blank", errorMessage);

            return;
        }

        if (name.length() > 50 || description.length() > 5000 || address.length() > 30) {

            setMessage("One or more text boxes have too many characters", errorMessage);
            return;
        }

        Instant instant = Instant.from(rawPrelim.atStartOfDay(ZoneId.systemDefault()));//some hoop jumping to get the dates picked from the User in GUI
        Date prelimStartDate = Date.from(instant);
        Instant instant2 = Instant.from(rawEst.atStartOfDay(ZoneId.systemDefault()));
        Date estEndDate = Date.from(instant2);

        if (prelimStartDate.compareTo(estEndDate) > 0) { //in can user set the first date to be after the end date

            setMessage("Preliminary Date Must Be Before End Date", errorMessage);
            return;
        }

        //all data is valid at this point
       

        if (description.length() == 0 && address.length() == 0) { //if user didn't enter anything into the optional fields
            inProgress = new Project(name, prelimStartDate, estEndDate);
        }

        if (description.length() > 0 && address.length() == 0) {
            inProgress = new Project(name, prelimStartDate, estEndDate, description); //if user only put text in notes field
        }

        if (address.length() > 0 && description.length() == 0) {
            inProgress = new Project(address, name, prelimStartDate, estEndDate); //if user only puts text in address field
        }

        if (description.length() > 0 && address.length() > 0) {
            inProgress = new Project(name, prelimStartDate, estEndDate, description, address); //user puts text in both field
        }

        navigateTo("/ui/CreateProjectGUI_2.fxml", this.outerPane);
        
    } //the project object is not committed to db until the quote has been produced

    /**
     * getting names for the dropdown menu so user can pick a Client to add to
     * the project
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //DBServices dbs = new DBServices();     // load and producesproduces list of Client names for the dropdown in the GUI
        // ArrayList<Client> clients = new ArrayList<Client>();
        //clients.addAll(dbs.getClients(false));
        //ObservableList<String> names = FXCollections.observableArrayList();

        // for (int i = 0; i < clients.size(); i++) {
        //   names.add(clients.get(i).getFirstName());
        // }
        //clientDropdown.setItems((ObservableList) names); //puts names in dropdown
    }
    
    protected BorderPane getOuterPane(){
       return this.outerPane;
    }
    
    protected void setOuterPane(BorderPane pane){
        this.outerPane = pane;
    }
    
   
    protected void setErrorMessage (Label error){
        this.errorMessage = error;
    }
}
