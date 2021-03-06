package controller;

import entity.Project;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.layout.BorderPane;

/**
 * 
 * This is the class that controls what happens when the user is on the page that lets them make a new client i.e /ui/CreateProjectGUI.fxml
 * Also controls /ui/CreateProjectGUI_2.fxml
 * @author 734972
 */
public class CreateProjectGUIController extends Controller implements Initializable {

    //try to keeps this in the relative order they appear on the page
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

    private Label errorMessage;

    private static BorderPane outerPane;

    private static Project inProgress; //is actually used. netbeans is a liar

    private int customsAdded;

    private String selectedItem;

    private ObservableList<String> allItems = FXCollections.observableArrayList();

    /**
     * When user clicks next button on _1, system begins creating the new
     * project object, saves users data that they wish to define for this
     * project, then navigates to next page for creating the rest of the project
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

            setMessage("One or More of the Fields is Too Long", errorMessage);
            return;
        }

        if (start != null && end != null && start.compareTo(end) > 0) { //in can user set the first date to be after the end date

            setMessage("Start Date Must Be Before End Date", errorMessage);
            return;
        }//all data is valid at this point

        inProgress = new Project(name, start, end, description, address, true);

        FXMLLoader loader = navigateTo("/ui/CreateProjectGUI_2.fxml", this.outerPane);
        CreateProjectGUIController cont = loader.getController();
        cont.setErrorMessage(errorMessage);

    } //the project object is not committed to db until the quote has been produced

    /**
     * When user clicks Next button on _2, takes selected services from _2 and
     * data/object from _1 and navigates to _3 with previously created data
     *
     * @param event
     */
    @FXML
    private void nextBtnAction2(ActionEvent event) throws IOException {

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
     * Takes off selected service from table on _2
     *
     * @param event
     */
    @FXML
    private void removeServiceAction(ActionEvent event) {

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
     * Gets service that user selected from table
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
     * adds an Excavation By Hand service to table
     * @param event
     */
    @FXML
    private void byHandAction(ActionEvent event) {

        allItems.add("Excavation by Hand");
        updateTable(allItems);
    }

    /**
     * adds an Excavation By Skid Steer service to table
     * @param event
     */
    @FXML
    private void bySkidAction(ActionEvent event) {

        allItems.add("Excavation by Skid");
        updateTable(allItems);
    }

    /**
     * adds a Weed Barrier service to the table
     * @param event 
     */
    @FXML
    private void barrierAction(ActionEvent event) {

        allItems.add("Weed Barrier");
        updateTable(allItems);
    }

    /**
     * adds a Retaining Wall service to the table
     * @param event
     */
    @FXML
    private void wallAction(ActionEvent event) {

        allItems.add("Retaining Wall");
        updateTable(allItems);
    }

    /**
     * adds Aggregate bed service to table
     * @param event
     */
    @FXML
    private void bedAction(ActionEvent event) {

        allItems.add("Bed");
        updateTable(allItems);
    }

    /**
     * adds Sod service to table
     * @param event
     */
    @FXML
    private void sodAction(ActionEvent event) {

        allItems.add("Sod");
        updateTable(allItems);
    }

    /**
     * adds Custom service to table
     * @param event
     */
    @FXML
    private void addCustomAction(ActionEvent event) {

        this.customsAdded++;
        allItems.add("Custom Service #" + customsAdded);
        updateTable(allItems);
    }

    /**
     * adds Underground Irrigation service to table
     * @param event
     */
    @FXML
    private void irrigAction(ActionEvent event) {

        allItems.add("Underground Irrigation");
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
     * resets reference to outer pane and it's elements
     * @param pane
     */
    protected void setOuterPane(BorderPane pane) {
        this.outerPane = pane;
    }

    /**
     * Resets reference to outer error message label
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
