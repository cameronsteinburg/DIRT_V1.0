package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import services.DBServices;

/**
 * FXML Controller class
 *
 * @author 734972
 */
public class CreateProjectGUI_3Controller implements Initializable {

    public ArrayList<ArrayList> elements = new ArrayList<ArrayList>(); //list of each list of elements that will be used to get info for each task

    @FXML
    private AnchorPane anc;
    @FXML
    private TextField bottomLine;

    private static int fieldCount = 0;
    private static BorderPane outerPane;
    private ObservableList<String> allItems;
    private ArrayList<Double> allTotals = new ArrayList<Double>();
    private double projectTotal = 0;
    
    protected CreateProjectGUI_3Controller(ObservableList<String> allItems) {
        this.allItems = allItems;
    }

    protected CreateProjectGUI_3Controller() {
        this(null);
    }

    private void addToList() {

        for (int i = 0; i < allItems.size(); i++) {

            if (allItems.get(i).contains("Excavation")) {

                if (allItems.get(i).contains("Hand")) {

                    elements.add(addByHand());

                } else if (allItems.get(i).contains("Skid")) {

                    //todo addBySkid
                }

            } else if (allItems.get(i).contains("Custom")) {

                //todo addCustom
            } else if (allItems.get(i).contains("Soil")) {

                //todo addSoil
            } else if (allItems.get(i).contains("Sod")) {

            } else if (allItems.get(i).contains("Snow")) {

            } else if (allItems.get(i).contains("Irrigation")) {

            } else if (allItems.get(i).contains("Barrier")) {

            } else if (allItems.get(i).contains("Wall")) {

            }
        }
    }

    private ArrayList<Control> addByHand() {
        
        DecimalFormat f = new DecimalFormat("#.00");

        ArrayList<Control> hand = new ArrayList();

        Label label = new Label("Excavation By Hand:");
        label.setUnderline(true);
        label.setPadding(new Insets(0, 7, 0, 0));//top, right, bottom, left
        label.setFont(new Font(20));
        hand.add(label);

        hand.add(addLabel("SQ.FT"));
        hand.add(addField(true));

        hand.add(addLabel("Depth In Inches"));
        hand.add(addField(true));

        label = new Label("    | ");
        label.setFont(new Font(20));
        hand.add(label);

        hand.add(addLabel("Required Yards"));
        hand.add(addField(false));

        hand.add(addLabel("Est. Man Hours /Yard"));
        hand.add(this.addField(false));

        hand.add(addLabel("Excavation Labour Cost"));
        hand.add(addField(false));

        hand.add(addLabel("Trucking Fees /2 Yards"));
        hand.add(addField(false));

        hand.add(addLabel("Disposal Fees"));
        hand.add(addField(false));

        label = new Label("   |");
        label.setFont(new Font(20));
        hand.add(label);

        label = addLabel("Service Total");
        label.setUnderline(true);
        label.setFont(new Font(16));
        hand.add(label);
        hand.add(addField(false));

        hand.add(new Label("   "));

        DBServices dbs = new DBServices();

        double labourhours = dbs.excavation_ManHoursByHandPerYards();
        double labourRate = dbs.excavation_ManHoursByHandPerHours();
        double truckingRate = dbs.excavation_TruckingFee();
        double disposalRate = dbs.excavation_DisposalFee();

        for (int i = 0; i < hand.size(); i++) {

            String currentEl = hand.get(i).getId();


            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1")) {

                TextField sqft = (TextField) hand.get(2);
                
                int place;
                
                if(currentEl.equals("0")){
                    place = 2;
                } else{
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

                        reqyards.setText(f.format(reqyardsdbl));

                        //update estimated man hours per yard
                        TextField estManHours = (TextField) hand.get(9);
                        Double estimatedManHourDbl = (labourhours * reqyardsdbl);
                        estManHours.setText(f.format(estimatedManHourDbl));

                        //update labour cost
                        TextField labourCost = (TextField) hand.get(11);
                        Double labourCostDouble = (estimatedManHourDbl * labourRate);
                        labourCost.setText(f.format(labourCostDouble));
                        
                        //update trucking fee
                        TextField trucking = (TextField) hand.get(13);
                        Double truckingDbl = ((reqyardsdbl/2) * truckingRate);
                        trucking.setText(f.format(truckingDbl));
                        
                        //update disposl
                        TextField disp = (TextField) hand.get(15);
                        Double disDbl = (reqyardsdbl * disposalRate);
                        disp.setText(f.format(disDbl));
                        
                        TextField serTotal = (TextField) hand.get(18);
                        Double serTotalDbl = disDbl + labourCostDouble + truckingDbl;
                        serTotal.setText(f.format(serTotalDbl));
                        
                        projectTotal = projectTotal + serTotalDbl;
                        bottomLine.setText(projectTotal + "");
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
    private TextField addField(boolean editable) {

        TextField field = new TextField("00.00");
        field.setMaxWidth(75);
        field.setMinWidth(75);
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
        label.setPadding(new Insets(0, 0, 0, 8));
        return label;
    }

    /**
     *
     * @param els
     */
    protected void setEls(ObservableList<String> els) {
        allItems = els;

    }

    protected void setOuterPane(BorderPane pane) {
        this.outerPane = pane;
    }

    protected AnchorPane getPane() {
        return this.anc;
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

        //AnchorPane.setLeftAnchor(newGrid, Double.NaN);
        //AnchorPane.setTopAnchor(newGrid, Double.NaN);
        //AnchorPane.setRightAnchor(newGrid, Double.NaN);
        //AnchorPane.setBottomAnchor(newGrid, Double.NaN); 
        //todo anchor constraints
        newGrid.setMaxWidth(1800);
        newGrid.setHgap(5);
        newGrid.setVgap(5);
        newGrid.setPadding(new Insets(0, 0, 15, 0));

        anc.getChildren().add(newGrid);
    }
}
