package controller;

import entity.Project;
import entity.Services.WO_Bed;
import entity.Services.WO_Excavation;
import entity.WorkOrder;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import services.DBServices;

/**
 * FXML Controller class
 *
 * @author 734972
 */
public class CreateProjectGUI_3Controller extends Controller implements Initializable {

    public ArrayList<ArrayList> elements = new ArrayList<ArrayList>(); //list of each list of elements that will be used to get info for each task

    @FXML
    private AnchorPane anc;
    @FXML
    private Label bottomLine;
    @FXML
    private Button saveBtn;

    private Label errorMessage;
    private static BorderPane outerPane;

    private static int fieldCount = 0;
    private double projectTotal = 0;
    private ArrayList<WorkOrder> orders = new ArrayList();
    private DecimalFormat f = new DecimalFormat("#.00");
    private final DBServices dbs = new DBServices();
    private double taxMultiplier = dbs.tax_GST() + dbs.tax_PST() + 1.0;

    private ObservableList<String> allItems;
    private Project inProgress;

    /**
     *
     * @param allItems
     */
    private CreateProjectGUI_3Controller(ObservableList<String> allItems) {
        this.allItems = allItems;
    }

    /**
     *
     */
    protected CreateProjectGUI_3Controller() {
        this(null);
    }

    /**
     *
     * @param event
     */
    @FXML
    private void saveBtnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Save New Project?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            inProgress.setWorkOrders(orders);
            inProgress.setQuote(projectTotal);
            inProgress.setActualCost(projectTotal);
            inProgress.setClient(null);

            dbs.persistProject(inProgress);

            setMessage("Project Successfully Created!", errorMessage);

            navigateTo("/ui/OngoingProjectsGUI.fxml", this.outerPane);
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void cancelBtnAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Cancel Entire Project Creation?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            navigateTo("/ui/OngoingProjectsGUI.fxml", this.outerPane);
        }
    }

    /**
     *
     */
    private void addToList() {

        for (int i = 0; i < allItems.size(); i++) {

            if (allItems.get(i).contains("Excavation")) {

                if (allItems.get(i).contains("Hand")) {

                    elements.add(addByHand());

                } else if (allItems.get(i).contains("Skid")) {

                    elements.add(addBySkid());
                }

            } else if (allItems.get(i).contains("Sod")) {
                
                //todo elements.add(addSod());
                
            } else if (allItems.get(i).contains("Bed")) {

                elements.add(addBed());

            } else if (allItems.get(i).contains("Irrigation")) {
                
                //todo elements.add(addIrrigation());

            } else if (allItems.get(i).contains("Barrier")) {
                
                //todo elements.add(addBarrier());

            } else if (allItems.get(i).contains("Wall")) {

            } else if (allItems.get(i).contains("Custom")) {

            }
        }
    }

    /**
     * 
     * @return 
     */
    private ArrayList<Control> addBed() {

        WO_Bed newBed = new WO_Bed(true);
        orders.add(newBed);

        ArrayList<Control> bed = new ArrayList();

        Label label = new Label("Aggregate Bed:");
        label.setUnderline(true);
        label.setPadding(new Insets(0, 3, 0, 0));//top, right, bottom, left
        label.setFont(new Font(16));
        bed.add(label);

        ObservableList<String> options = FXCollections.observableArrayList(
                        "Crushed Rock",
                        "Pea Rock",
                        "River Rock",
                        "Western Red Cedar Mulch",
                        "Premium Top Soil",
                        "Crusher Dust",
                        "Red Shale",
                        "Sod /10 sq.ft");

        ComboBox aggs = new ComboBox(options);
        aggs.setMaxHeight(10);
        aggs.setMaxWidth(50);

        bed.add(aggs);

        bed.add(addLabel("SQ.FT"));
        bed.add(addField(true));

        bed.add(addLabel("Depth In Inches"));
        bed.add(addField(true));

        label = new Label("    | ");
        label.setFont(new Font(20));
        bed.add(label);

        bed.add(addLabel("Required Yards"));
        bed.add(addField(false));

        bed.add(addLabel("Est. Man Hours"));
        bed.add(this.addField(false));

        bed.add(addLabel("Aggregate Cost"));
        bed.add(this.addField(false));

        bed.add(addLabel("Labour Cost"));
        bed.add(addField(false));

        label = addLabel("Service Total");
        label.setUnderline(true);
        label.setFont(new Font(16));
        label.setPadding(new Insets(0, 0, 0, 100));
        bed.add(label);
        bed.add(addField(false));
        
        bed.add(new Label(""));
        bed.add(new Label(""));
        
        double labourRate = dbs.bed_LabourPerHour();
        double hoursPeryard = dbs.bed_ManHoursPerYard();
        
        //String aggChosen = "" + aggs.getValue();

        fieldCount = 0;
        return bed;
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addByHand() {

        WO_Excavation newHand = new WO_Excavation('h', true);
        orders.add(newHand);

        ArrayList<Control> hand = new ArrayList();

        Label label = new Label("Excavation By Hand:");
        label.setUnderline(true);
        label.setPadding(new Insets(0, 3, 0, 0));//top, right, bottom, left
        label.setFont(new Font(16));
        hand.add(label);

        hand.add(addLabel("SQ.FT"));
        hand.add(addField(true));

        hand.add(addLabel("Depth In Inches"));
        hand.add(addField(true));

        label = new Label("   |");
        label.setFont(new Font(20));
        hand.add(label);

        hand.add(addLabel("Required Yards"));
        hand.add(addField(false));

        hand.add(addLabel("Est. Man Hours"));
        hand.add(this.addField(false));

        hand.add(addLabel("Excavation Labour Cost"));
        hand.add(addField(false));

        hand.add(addLabel("Trucking Fees"));
        hand.add(addField(false));

        hand.add(addLabel("Disposal Fees"));
        hand.add(addField(false));

        label = addLabel("Service Total");
        label.setUnderline(true);
        label.setFont(new Font(16));
        label.setPadding(new Insets(0, 0, 0, 100));
        hand.add(label);
        hand.add(addField(false));

        double labourhours = dbs.excavation_ManHoursByHandPerYards();
        double labourRate = dbs.excavation_ManHoursByHandPerHours();
        double truckingRate = dbs.excavation_TruckingFeeByHand();
        double disposalRate = dbs.excavation_DisposalFee();

        for (int i = 0; i < hand.size(); i++) {

            String currentEl = hand.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1")) {

                TextField sqft = (TextField) hand.get(2);

                int place;

                if (currentEl.equals("0")) {
                    place = 2;
                } else {
                    place = 4;
                }

                hand.get(place).setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        //update required yards
                        double sqftdbl;
                        double depthdbl;

                        TextField depth = (TextField) hand.get(4);
                        TextField reqyards = (TextField) hand.get(7);

                        try {
                            sqftdbl = Double.parseDouble(sqft.getText());
                        } catch (Exception e) {
                            sqftdbl = 0;
                        }

                        try {
                            depthdbl = Double.parseDouble(depth.getText());
                        } catch (Exception e) {
                            depthdbl = 0;
                        }

                        Double reqyardsdbl = (depthdbl / 12.0) * (sqftdbl / 27.0);
                        newHand.setEstReqYards(reqyardsdbl);

                        reqyards.setText(f.format(reqyardsdbl));

                        //update estimated man hours per yard
                        TextField estManHours = (TextField) hand.get(9);
                        Double estimatedManHourDbl = (labourhours * reqyardsdbl);
                        newHand.setEstHours(estimatedManHourDbl);
                        estManHours.setText(f.format(estimatedManHourDbl));

                        //update labour cost
                        TextField labourCost = (TextField) hand.get(11);
                        Double labourCostDouble = (estimatedManHourDbl * labourRate);
                        newHand.setEstLabour(labourCostDouble);
                        labourCost.setText(f.format(labourCostDouble));

                        //update trucking fee
                        TextField trucking = (TextField) hand.get(13);
                        Double truckingDbl = ((reqyardsdbl / 2) * truckingRate);
                        newHand.setEstTrucking(truckingDbl);
                        trucking.setText(f.format(truckingDbl));

                        //update disposl
                        TextField disp = (TextField) hand.get(15);
                        Double disDbl = (reqyardsdbl * disposalRate);
                        newHand.setEstDisposal(0);
                        disp.setText(f.format(disDbl));

                        TextField serTotal = (TextField) hand.get(17);
                        Double serTotalDbl = disDbl + labourCostDouble + truckingDbl;
                        newHand.setQuotedTotal(serTotalDbl);
                        serTotal.setText(f.format(serTotalDbl));

                        double ttl = 0;

                        for (int i = 0; i < orders.size(); i++) {

                            ttl += orders.get(i).getQuotedTotal();
                        }

                        projectTotal = ttl;

                        bottomLine.setText(f.format(projectTotal * taxMultiplier));

                        botCheck();
                    }
                });
            }
        }

        fieldCount = 0;
        return hand;
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addBySkid() {

        WO_Excavation newSkid = new WO_Excavation('s', true);
        orders.add(newSkid);

        ArrayList<Control> skid = new ArrayList();

        Label label = new Label("Excavation By Skid Steer:");
        label.setUnderline(true);
        label.setPadding(new Insets(0, 3, 0, 0));//top, right, bottom, left
        label.setFont(new Font(16));
        skid.add(label);

        skid.add(addLabel("SQ.FT"));
        skid.add(addField(true));

        skid.add(addLabel("Depth In Inches"));
        skid.add(addField(true));

        label = new Label("   | ");
        label.setFont(new Font(20));
        skid.add(label);

        skid.add(addLabel("Required Yards"));
        skid.add(addField(false));

        skid.add(addLabel("Est. Man Hours"));
        skid.add(this.addField(false));

        skid.add(addLabel("Excavation Labour Cost"));
        skid.add(addField(false));

        skid.add(addLabel("Trucking Fees"));
        skid.add(addField(false));

        skid.add(addLabel(""));
        skid.add(addLabel(""));

        label = addLabel("Service Total");
        label.setUnderline(true);
        label.setFont(new Font(16));
        label.setPadding(new Insets(0, 0, 0, 100));
        skid.add(label);
        skid.add(addField(false));

        double labourhours = dbs.excavation_ManHoursBySkidPerYards();
        double labourRate = dbs.excavation_ManHoursBySkidPerHours();
        double truckingRate = dbs.excavation_TruckingFeeBySkid();

        for (int i = 0; i < skid.size(); i++) {

            String currentEl = skid.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1")) {

                TextField sqft = (TextField) skid.get(2);

                int place;

                if (currentEl.equals("0")) {
                    place = 2;
                } else {
                    place = 4;
                }

                skid.get(place).setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        //update required yards
                        double sqftdbl;
                        double depthdbl;

                        TextField depth = (TextField) skid.get(4);
                        TextField reqyards = (TextField) skid.get(7);

                        try {
                            sqftdbl = Double.parseDouble(sqft.getText());
                        } catch (Exception e) {
                            sqftdbl = 0;
                        }

                        try {
                            depthdbl = Double.parseDouble(depth.getText());
                        } catch (Exception e) {
                            depthdbl = 0;
                        }

                        Double reqyardsdbl = (depthdbl / 12.0) * (sqftdbl / 27.0);
                        newSkid.setEstReqYards(reqyardsdbl);

                        reqyards.setText(f.format(reqyardsdbl));

                        //update estimated man hours per yard
                        TextField estManHours = (TextField) skid.get(9);
                        Double estimatedManHourDbl = (labourhours * reqyardsdbl);
                        newSkid.setEstHours(estimatedManHourDbl);
                        estManHours.setText(f.format(estimatedManHourDbl));

                        //update labour cost
                        TextField labourCost = (TextField) skid.get(11);
                        Double labourCostDouble = (estimatedManHourDbl * labourRate);
                        newSkid.setEstLabour(labourCostDouble);
                        labourCost.setText(f.format(labourCostDouble));

                        //update trucking fee
                        TextField trucking = (TextField) skid.get(13);
                        Double truckingDbl = ((reqyardsdbl / 2) * truckingRate);
                        newSkid.setEstTrucking(truckingDbl);
                        trucking.setText(f.format(truckingDbl));

                        TextField serTotal = (TextField) skid.get(17);
                        Double serTotalDbl = labourCostDouble + truckingDbl;
                        newSkid.setQuotedTotal(serTotalDbl);
                        serTotal.setText(f.format(serTotalDbl));

                        double ttl = 0;

                        for (int i = 0; i < orders.size(); i++) {

                            ttl += orders.get(i).getQuotedTotal();
                        }

                        projectTotal = ttl;

                        double taxMultiplier = dbs.tax_GST() + dbs.tax_PST() + 1.0;

                        bottomLine.setText(f.format(projectTotal * taxMultiplier));

                        botCheck();
                    }
                });
            }
        }

        fieldCount = 0;
        return skid;
    }

    /**
     *
     * @return
     */
    private TextField addField(boolean editable) {

        TextField field = new TextField("00.00");
        field.setMaxWidth(60);
        field.setMinWidth(60);
        field.setId(fieldCount + "");
        field.setAlignment(Pos.CENTER);
        field.setEditable(editable);
        fieldCount++;
        return field;
    }

    /**
     *
     * @param text
     * @return
     */
    private Label addLabel(String text) {

        Label label = new Label(text);
        label.setPadding(new Insets(0, 0, 0, 5));
        return label;
    }

    /**
     *
     */
    private void botCheck() {

        double eh = -1;

        try {
            eh = Double.parseDouble(bottomLine.getText());
        } catch (Exception e) {
            saveBtn.setDisable(true);
        }

        if (eh == 0 || eh == -1) {
            saveBtn.setDisable(true);
        } else {
            saveBtn.setDisable(false);
        }
    }

    /**
     *
     * @param error
     */
    @Override
    protected void setErrorMessage(Label error) {
        this.errorMessage = error;
    }

    /**
     *
     * @param els
     */
    protected void setEls(ObservableList<String> els) {
        allItems = els;
    }

    /**
     *
     * @param prog
     */
    protected void setInProgress(Project prog) {
        this.inProgress = prog;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        addToList();

        GridPane newGrid = new GridPane();

        for (int i = 0; i < elements.size(); i++) {

            for (int j = 0; j < elements.get(i).size(); j++) {

                newGrid.addRow(i, (Node) elements.get(i).get(j));
            }
        }

        newGrid.setTranslateX(5);
        newGrid.setTranslateY(5);

        ColumnConstraints cc = new ColumnConstraints();

        //AnchorPane.setLeftAnchor(newGrid, Double.NaN);
        //AnchorPane.setTopAnchor(newGrid, Double.NaN);
        //AnchorPane.setRightAnchor(newGrid, Double.NaN);
        //AnchorPane.setBottomAnchor(newGrid, Double.NaN); 
        //todo anchor constraints
        newGrid.setMaxWidth(1850);
        newGrid.getColumnConstraints().add(cc);
        newGrid.getColumnConstraints().add(new ColumnConstraints(60));
        newGrid.setHgap(5);
        newGrid.setVgap(5);
        newGrid.setPadding(new Insets(0, 0, 15, 0));

        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(50);
        newGrid.getRowConstraints().add(rc);

        anc.getChildren().add(newGrid);
    }
}
