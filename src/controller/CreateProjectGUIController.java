/*
 * 
 *  This is the class that controls what happens when the user is on the page that lets them make a new client i.e /ui/CreateProjectGUI.fxml
    Also controls child pages of /ui/CreateProjectGUI.fxml
 */
package controller;

import entity.Project;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CreateProjectGUIController extends Controller implements Initializable {

    //try to keeps this in the relative order they appear on the page
    //elements from the GUI.fxml page
    @FXML
    private Button removeBtn;
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker prelimStart;
    @FXML
    private DatePicker estEnd;
    @FXML
    private TextField addressField;
    @FXML
    private TextArea notesField;
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> servCol;
    @FXML
    private Button nextBtn2;
    @FXML
    private AnchorPane pane3;

    private Label errorMessage;

    private static BorderPane outerPane;

    private static Project inProgress; //is actually used. netbeans is a liar

    private int customsAdded;

    private String selectedItem;

    private ObservableList<String> allItems = FXCollections.observableArrayList();

    /**
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void nextBtnAction(ActionEvent event) throws IOException { //User attempts to save their details entered in fields in CreateProjectGUI.fxml

        //get the User's data they entered into GUI fields
        LocalDate start = prelimStart.getValue();
        LocalDate end = estEnd.getValue();

        String name = nameField.getText();
        String address = addressField.getText();
        String description = notesField.getText();

        //data validation commences 
        if (name.isEmpty()) { //checking to see if the user entered blank data for not null db attributes

            setMessage("* Required Fields Cannot Be Left Blank", errorMessage);

            return;
        }

        if (name.length() > 50 || description.length() > 5000 || address.length() > 30) {

            setMessage("One or more text boxes have too many characters", errorMessage);
            return;
        }

        Instant instant;
        Instant instant2;
        Date startDate;
        Date endDate;

        try {
            instant = Instant.from(start.atStartOfDay(ZoneId.systemDefault()));//some hoop jumping to get the dates picked from the User in GUI
            startDate = Date.from(instant);
        } catch (NullPointerException e){
            instant = null;
            startDate = null;
        }

        try {
            instant2 = Instant.from(end.atStartOfDay(ZoneId.systemDefault()));//some hoop jumping to get the dates picked from the User in GUI
            endDate = Date.from(instant);
        } catch (NullPointerException e){
            instant2 = null;
            endDate = null;
        }

            if (start != null && end != null && startDate.compareTo(endDate) > 0) { //in can user set the first date to be after the end date

                setMessage("Preliminary Date Must Be Before End Date", errorMessage);
                return;
            }//all data is valid at this point

            inProgress = new Project(name, startDate, endDate, description, address, true);

            FXMLLoader loader = navigateTo("/ui/CreateProjectGUI_2.fxml", this.outerPane);
            CreateProjectGUIController cont = loader.getController();
            cont.setErrorMessage(errorMessage);

        } //the project object is not committed to db until the quote has been produced

        /**
         *
         * @param event
         */
        @FXML
        private void nextBtnAction2
        (ActionEvent event) throws IOException {

            //give allitems to new controller, load fxml and set controller manually to enable objcet access during initialize 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/CreateProjectGUI_3.fxml"));
            CreateProjectGUI_3Controller cont = new CreateProjectGUI_3Controller();
            loader.setController(cont);
            cont.setEls(allItems);
            cont.setInProgress(inProgress);
            cont.setErrorMessage(errorMessage);
            cont.setOuterPane(outerPane);
            Parent root = loader.load();

            outerPane.setCenter(root);
        }

        /**
         *
         * @param event
         */
        @FXML
        private void removeServiceAction
        (ActionEvent event
        
            ) {

        for (int i = 0; i < allItems.size(); i++) {

                selectServiceFromList();

                String sel = (String) allItems.get(i);

                if (sel.contains(selectedItem)) {

                    allItems.remove(i);
                }
            }

            updateTable(allItems);
        }
        /**
         *
         * @param event
         */
    private void selectServiceFromList() {

        if (table.getSelectionModel().getSelectedItem() != null) {

            this.selectedItem = table.getSelectionModel().getSelectedItem();
            removeBtn.setDisable(false);

        } else {
            removeBtn.setDisable(true);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void byHandAction(ActionEvent event) {

        allItems.add("Excavation by Hand");
        updateTable(allItems);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void bySkidAction(ActionEvent event) {

        allItems.add("Excavation by Skid");
        updateTable(allItems);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void bedAction(ActionEvent event) {

        allItems.add("Bed");
        updateTable(allItems);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void addCustomAction(ActionEvent event) {

        this.customsAdded++;
        allItems.add("Custom Service #" + customsAdded);
        updateTable(allItems);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void patioAction(ActionEvent event) {

        allItems.add("Paver");
        updateTable(allItems);
    }

    /**
     * updates the table of services when the user clicks buttons of services
     */
    private void updateTable(ObservableList items) {

        if (servCol != null) {

            servCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
            table.setItems(items);
        }

        if (allItems.size() > 0 || customsAdded > 0) {

            selectServiceFromList();
            removeBtn.setDisable(false);
            nextBtn2.setDisable(false);

        } else {

            removeBtn.setDisable(true);
            nextBtn2.setDisable(true);
        }
    }

    /**
     *
     * @param pane
     */
    protected void setOuterPane(BorderPane pane) {
        this.outerPane = pane;
    }

    /**
     *
     * @param error
     */
    protected void setErrorMessage(Label error) {
        this.errorMessage = error;
    }

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
        //ArrayList<Client> clients = new ArrayList<Client>();
        //clients.addAll(dbs.getClients(false));
        //ObservableList<String> names = FXCollections.observableArrayList();
        // for (int i = 0; i < clients.size(); i++) {
        //   names.add(clients.get(i).getFirstName());
        // }
        //clientDropdown.setItems((ObservableList) names); //puts names in dropdown
    }

}
