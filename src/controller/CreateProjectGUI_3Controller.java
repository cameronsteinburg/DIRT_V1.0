package controller;

import entity.Project;
import entity.Services.WO_Bed;
import entity.Services.WO_Custom;
import entity.Services.WO_Excavation;
import entity.Services.WO_Irrigation;
import entity.Services.WO_Sod;
import entity.Services.WO_WeedBarrier;
import entity.Services.WO_RetWall;
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
import javafx.geometry.Pos;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import services.DBServices;

/**
 * FXML Controller class
 *
 * @author 734972
 */
public class CreateProjectGUI_3Controller extends Controller implements Initializable {

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
    private DecimalFormat f = new DecimalFormat("#.00");

    private final DBServices dbs = new DBServices();
    private double taxMultiplier = dbs.tax_GST() + dbs.tax_PST();

    private ArrayList<WorkOrder> orders = new ArrayList(); //work orders made by addToList which will receive data from elements and save to objects and to db by extensions if the user saves the project at the end

    private ArrayList<ArrayList> elements = new ArrayList<ArrayList>(); //list of each list of elements that will be used to get info for each task as well as labels
    private ObservableList<String> allItems; //the list of strings from page 2, indicates which services were selected for the project

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

            DBServices dbs = new DBServices();
            
           double tax = dbs.tax_GST() + dbs.tax_PST();
            
            inProgress.setWorkOrders(orders);
            inProgress.setQuote(projectTotal + (tax * projectTotal));
            inProgress.setActualCost(projectTotal + (tax * projectTotal));
            inProgress.setCompleted(false);
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

                elements.add(addSod());

            } else if (allItems.get(i).contains("Bed")) {

                elements.add(addBed());

            } else if (allItems.get(i).contains("Irrigation")) {

                elements.add(addIrrigation());

            } else if (allItems.get(i).contains("Barrier")) {

                elements.add(addBarrier());

            } else if (allItems.get(i).contains("Wall")) {

                elements.add(addRetWall());

            } else if (allItems.get(i).contains("Custom")) {

                String number = allItems.get(i);

                elements.add(addCustom(number));
            }
        }
    }

    /**
     * 
     * @param list 
     */
    private void addPad(ArrayList<Control> list) {

        double take = 1090.0 - takenUp(list);
        Label pad = new Label();
        pad.setMinWidth(take);
        pad.setMaxWidth(take);
        list.add(pad);
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addCustom(String number) {

        WO_Custom newCustom = new WO_Custom(true);
        orders.add(newCustom);

        ArrayList<Control> custom = new ArrayList();

        Label label = new Label(number + ":");
        label.setUnderline(true);
        label.setFont(new Font(16));
        custom.add(label);//0

        custom.add(addLabel("    Name/Description  ")); //1
        TextField descF = new TextField();
        descF.setMaxSize(100, 25);
        descF.setMinSize(100, 25);
        custom.add(descF); //2
        custom.get(custom.size() - 1).setId("0");

        custom.add(addLabel("     Expense $  "));//3
        custom.add(addField(true));//4
        custom.get(custom.size() - 1).setId("1");
        custom.add(addLabel(" x "));
        custom.add(addField(true));//6
        custom.get(custom.size() - 1).setId("2");

        addPad(custom);

        addServTotal(custom);

        for (int i = 0; i < custom.size(); i++) {

            String currentEl = custom.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1") || currentEl.equals("2")) {

                int place = 0;

                if (currentEl.equals("0")) {
                    place = 2;
                } else if (currentEl.equals("1")) {
                    place = 4;
                } else if (currentEl.equals("2")) {
                    place = 6;
                }

                custom.get(place).setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        double expense;
                        TextField expenseF = (TextField) custom.get(4);
                        double multi;
                        TextField multiF = (TextField) custom.get(6);

                        try {
                            expense = Double.parseDouble(expenseF.getText());
                        } catch (Exception e) {
                            expense = 0;
                        }

                        try {
                            multi = Double.parseDouble(multiF.getText());
                        } catch (Exception e) {
                            multi = 0;
                        }

                        TextField servTotalF = (TextField) custom.get(custom.size() - 1);
                        Double total = multi * expense;
                        servTotalF.setText(f.format(total));

                        TextField descF = (TextField) custom.get(2);
                        newCustom.setDescription(descF.getText());
                        newCustom.setMulti(multi);
                        newCustom.setExpense(expense);
                        newCustom.setQuotedTotal(total);
                        newCustom.setActualTotal(total);

                        botCheck();
                    }

                });

            }

        }

        fieldCount = 0;
        return custom;
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addRetWall() {

        WO_RetWall newWall = new WO_RetWall(true);
        orders.add(newWall);

        ArrayList<Control> wall = new ArrayList();

        Label label = new Label("Retaining Wall:");
        label.setUnderline(true);
        label.setFont(new Font(16));
        wall.add(label);

        wall.add(addLabel("Line Feet"));
        wall.add(addField(true));//2

        wall.add(addLabel("Height"));
        wall.add(addField(true));//4

        wall.add(addLabel("Crushed Base Depth"));
        wall.add(addField(true));//6

        wall.add(addLabel("Crushed Base Width"));
        wall.add(addField(true));//8

        label = new Label("    | ");
        label.setFont(new Font(20));
        wall.add(label);

        wall.add(addLabel("SQ.FT"));
        wall.add(addField(false));//11

        wall.add(addLabel("Base Req Yards"));
        wall.add(addField(false));

        wall.add(addLabel("Base Supply"));
        wall.add(addField(false));

        wall.add(addLabel("Base Hours"));
        wall.add(addField(false));

        wall.add(addLabel("Base Labour"));
        wall.add(addField(false));

        wall.add(addLabel("Base Row Hours"));
        wall.add(addField(false));

        wall.add(addLabel("Base Row Labour"));
        wall.add(addField(false));

        wall.add(addLabel("Block"));
        wall.add(addField(false));

        addServTotal(wall);

        double baseSupplyPerYard = dbs.retainingwall_CrushedBaseCostPerYard();
        double baseHoursPerYard = dbs.retainingwall_CrushedBaseInstallHoursPerYard();
        double baseInstallPerHour = dbs.retainingwall_CrushedBaseInstallRatePerHour();
        double rowInstallPerLF = dbs.retainingwall_BaseRowInstallHoursPerLineFeet();
        double rowLabourPerHour = dbs.retainingwall_BaseRowInstallRatePerHour();
        double blockPerLF = dbs.retainingwall_BlockCostPerLineFeet();

        for (int i = 0; i < wall.size(); i++) {

            String currentEl = wall.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1") || currentEl.equals("2") || currentEl.equals("3")) {

                int place = -1;

                if (currentEl.equals("0")) {
                    place = 2;
                } else if (currentEl.equals("1")) {
                    place = 4;
                } else if (currentEl.equals("2")) {
                    place = 6;
                } else if (currentEl.equals("3")) {
                    place = 8;
                }

                wall.get(place).setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        double lfDbl, heightDbl, baseDepthDbl, baseWidthDbl;

                        TextField lfF = (TextField) wall.get(2);
                        TextField heightF = (TextField) wall.get(4);
                        TextField baseDepthF = (TextField) wall.get(6);
                        TextField baseWidthF = (TextField) wall.get(8);

                        try {
                            lfDbl = Double.parseDouble(lfF.getText());
                        } catch (Exception e) {
                            lfDbl = 0;
                        }

                        try {
                            heightDbl = Double.parseDouble(heightF.getText());
                        } catch (Exception e) {
                            heightDbl = 0;
                        }

                        try {
                            baseDepthDbl = Double.parseDouble(baseDepthF.getText());
                        } catch (Exception e) {
                            baseDepthDbl = 0;
                        }

                        try {
                            baseWidthDbl = Double.parseDouble(baseWidthF.getText());
                        } catch (Exception e) {
                            baseWidthDbl = 0;
                        }

                        TextField sqftF = (TextField) wall.get(11);
                        Double sqftDbl = (heightDbl / 12.0) * lfDbl;
                        sqftF.setText(f.format(sqftDbl));

                        TextField baseYardsF = (TextField) wall.get(13);
                        Double baseReqYardsDbl = (baseDepthDbl / 12.0) * (lfDbl * (baseWidthDbl / 12.0) / 27.0);
                        baseYardsF.setText(f.format(baseReqYardsDbl));

                        TextField baseSupplyF = (TextField) wall.get(15);
                        Double baseSupplyDbl = baseReqYardsDbl * baseSupplyPerYard;
                        baseSupplyF.setText(f.format(baseSupplyDbl));

                        TextField baseHoursF = (TextField) wall.get(17);
                        Double baseHoursDbl = baseHoursPerYard * baseReqYardsDbl;
                        baseHoursF.setText(f.format(baseHoursDbl));

                        TextField baseLabourF = (TextField) wall.get(19);
                        Double baseLabourDbl = baseInstallPerHour * baseHoursDbl;
                        baseLabourF.setText(f.format(baseLabourDbl));

                        TextField rowHoursF = (TextField) wall.get(21);
                        Double rowHoursDbl = lfDbl * rowInstallPerLF;
                        rowHoursF.setText(f.format(rowHoursDbl));

                        TextField rowLabourF = (TextField) wall.get(23);
                        Double rowLabourDbl = rowHoursDbl * rowLabourPerHour;
                        rowLabourF.setText(f.format(rowLabourDbl));

                        TextField blockF = (TextField) wall.get(25);
                        Double blockDbl = lfDbl * blockPerLF;
                        blockF.setText(f.format(blockDbl));

                        TextField servTotal = (TextField) wall.get(27);
                        Double servTotalDbl = rowLabourDbl + blockDbl + baseLabourDbl + baseSupplyDbl;
                        servTotal.setText(f.format(servTotalDbl));

                        newWall.setEstBaseDepth(baseDepthDbl);
                        newWall.setEstBaseHours(baseHoursDbl);
                        newWall.setEstBaseLabour(baseLabourDbl);
                        newWall.setEstBaseReqYards(baseReqYardsDbl);
                        newWall.setEstBaseRowHours(rowHoursDbl);
                        newWall.setEstBaseRowLabour(rowLabourDbl);
                        newWall.setEstBaseSupply(baseSupplyDbl);
                        newWall.setEstBaseWidth(baseWidthDbl);
                        newWall.setEstBlock(blockDbl);
                        newWall.setEstHeight(heightDbl);
                        newWall.setEstLineFT(lfDbl);
                        newWall.setEstSQFT(sqftDbl);
                        newWall.setQuotedTotal(servTotalDbl);

                        newWall.setActBaseDepth(baseDepthDbl);
                        newWall.setActBaseHours(baseHoursDbl);
                        newWall.setActBaseLabour(baseLabourDbl);
                        newWall.setActBaseReqYards(baseReqYardsDbl);
                        newWall.setActBaseRowHours(rowHoursDbl);
                        newWall.setActBaseRowLabour(rowLabourDbl);
                        newWall.setActBaseSupply(baseSupplyDbl);
                        newWall.setActBaseWidth(baseWidthDbl);
                        newWall.setActBlock(blockDbl);
                        newWall.setActHeight(heightDbl);
                        newWall.setActLineFT(lfDbl);
                        newWall.setActSQFT(sqftDbl);
                        newWall.setActualTotal(servTotalDbl);

                        botCheck();

                    }
                });

            }

        }

        fieldCount = 0;

        return wall;
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addBarrier() {

        WO_WeedBarrier newWeed = new WO_WeedBarrier(true);
        orders.add(newWeed);

        ArrayList<Control> weed = new ArrayList<Control>();

        Label label = new Label("Weed Barrier:");
        label.setUnderline(true);
        label.setFont(new Font(16));
        weed.add(label);

        weed.add(addLabel("SQ.FT"));
        weed.add(addField(true));

        label = new Label("    | ");
        label.setFont(new Font(20));
        weed.add(label);

        weed.add(addLabel("Est. Man Hours"));
        weed.add(addField(false));

        weed.add(addLabel("Fabric Staples Required"));
        weed.add(addField(false));

        weed.add(addLabel("Fabric Staples Supply"));
        weed.add(addField(false));

        weed.add(addLabel("Barrier Supply"));
        weed.add(addField(false));

        weed.add(addLabel("Labour Cost"));
        weed.add(addField(false));

        addServTotal(weed);

        double barrierSupply = dbs.weedbarrier_BarrierSupplyPer500SQFT();
        double stapleRate = dbs.weedbarrier_CostPerStaples();
        double estHoursRate = dbs.weedbarrier_ManHoursPer500SQFT();
        double amountOfStaples = dbs.weedbarrier_StaplesPer500SQFT();
        double installrate = dbs.weedbarrier_InstallPer500SQFT();

        weed.get(2).setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                double sqftDbl;

                TextField sqft = (TextField) weed.get(2);

                try {
                    sqftDbl = Double.parseDouble(sqft.getText());
                } catch (Exception e) {
                    sqftDbl = 0;
                }

                TextField estHours = (TextField) weed.get(5);
                Double hoursDbl = (sqftDbl / 500) * estHoursRate;
                estHours.setText(f.format(hoursDbl));

                TextField staples = (TextField) weed.get(7);
                Double staplesDbl = (sqftDbl / 500) * amountOfStaples;
                staples.setText(f.format(staplesDbl));

                TextField staplesCost = (TextField) weed.get(9);
                Double staplesCostDbl = (sqftDbl / 500) * stapleRate;
                staplesCost.setText(f.format(staplesCostDbl));

                TextField barrierSupplyF = (TextField) weed.get(11);
                Double barrierSupplyDbl = (sqftDbl / 500) * barrierSupply;
                barrierSupplyF.setText(f.format(barrierSupplyDbl));

                TextField barrierInstallF = (TextField) weed.get(13);
                Double installDbl = (sqftDbl / 500) * installrate;
                barrierInstallF.setText(f.format(installDbl));

                TextField serTotal = (TextField) weed.get(15);
                Double serTotalDbl = installDbl + staplesCostDbl + barrierSupplyDbl;
                serTotal.setText(f.format(serTotalDbl));

                newWeed.setEstBarrierSupply(barrierSupplyDbl);
                newWeed.setEstHours(hoursDbl);
                newWeed.setEstLabour(installDbl);
                newWeed.setEstStaples(staplesDbl);
                newWeed.setEstStaplesSupply(staplesCostDbl);
                newWeed.setQuotedTotal(serTotalDbl);

                newWeed.setActBarrierSupply(barrierSupplyDbl);
                newWeed.setActHours(hoursDbl);
                newWeed.setActLabour(installDbl);
                newWeed.setActStaples(staplesDbl);
                newWeed.setActStaplesSupply(staplesCostDbl);
                newWeed.setActualTotal(serTotalDbl);

                botCheck();

            }
        });

        fieldCount = 0;
        return weed;
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addIrrigation() {

        WO_Irrigation newIrrig = new WO_Irrigation(true);
        orders.add(newIrrig);

        ArrayList<Control> irrig = new ArrayList();
        //ArrayList<Control> irrig2 = new ArrayList();

        Label label = new Label("Underground Irrigation:");
        label.setUnderline(true);
        //label.setPadding(new Insets(0, 3, 0, 0));//top, right, bottom, left
        label.setFont(new Font(16));
        irrig.add(label);// 0

        irrig.add(addLabel("3/4\" Line: "));
        irrig.add(addField(true));//2
        irrig.add(new Label("Feet   "));

        irrig.add(addLabel("Hose Bibs  x  "));
        irrig.add(addField(true));//5

        irrig.add(addLabel("Shut Off Valve  x  "));
        irrig.add(addField(true));//7

        irrig.add(addLabel("Rotary Heads  x  "));
        irrig.add(addField(true));//9

        irrig.add(addLabel("Pattern Heads  x  "));
        irrig.add(addField(true)); //11

        irrig.add(addLabel("1/4\" Drip Line  x  "));
        irrig.add(addField(true)); //13
        irrig.add(new Label("Feet"));// 14 split here

        irrig.add(addLabel("Drip Emitter  x  "));
        irrig.add(addField(true));//16

        irrig.add(addLabel("Timer Control  x  "));
        irrig.add(addField(true));//18

        irrig.add(addLabel("Control Wire: "));
        irrig.add(addField(true));//20
        irrig.add(new Label("  x 100 Feet   "));

        irrig.add(addLabel("Valve Box  x  "));
        irrig.add(addField(true));//23

        irrig.add(addLabel("Control Valve  x  "));
        irrig.add(addField(true));//25

        addServTotal(irrig); //size - 1

        double threeQM = dbs.irrigation_3QuarterLiningMaterial();
        double threeQL = dbs.irrigation_3QuarterLiningLabour();
        double hoseM = dbs.irrigation_HoseBibsMaterial();
        double hoseL = dbs.irrigation_RotaryHeadLabour();
        double shutM = dbs.irrigation_ShutOffValveMaterial();
        double shutL = dbs.irrigation_ShutOffValveLabour();
        double rotM = dbs.irrigation_RotaryHeadMaterial();
        double rotL = dbs.irrigation_RotaryHeadLabour();
        double patM = dbs.irrigation_SprayHeadMaterial();
        double patL = dbs.irrigation_SprayHeadLabour();
        double oneQM = dbs.irrigation_Drip1QuarterInchPerFootMaterial();
        double oneQL = dbs.irrigation_Drip1QuarterInchPerFootLabour();
        double emmM = dbs.irrigation_DripEmitterMaterial();
        double emmL = dbs.irrigation_DripEmitterLabour();
        double timerM = dbs.irrigation_TimerControlMaterial();
        double timerL = dbs.irrigation_TimerControlLabour();
        double wireM = dbs.irrigation_ControlWirePer100FeetMaterial();
        double wireL = dbs.irrigation_ControlWirePer100FeetLabour();
        double valveM = dbs.irrigation_ValveBoxMaterial();
        double valveL = dbs.irrigation_ValveBoxLabour();
        double contM = dbs.irrigation_ControlValveMaterial();
        double contL = dbs.irrigation_ControlValveLabour();

        for (int i = 0; i < irrig.size(); i++) {

            String currentEl = irrig.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl != null && currentEl != "-1") {

                irrig.get(i).setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        TextField threeQF = (TextField) irrig.get(2);
                        TextField hoseF = (TextField) irrig.get(5);
                        TextField shutF = (TextField) irrig.get(7);
                        TextField rotF = (TextField) irrig.get(9);
                        TextField patF = (TextField) irrig.get(11);
                        TextField oneQF = (TextField) irrig.get(13);
                        TextField emmF = (TextField) irrig.get(16);
                        TextField timerF = (TextField) irrig.get(18);
                        TextField wireF = (TextField) irrig.get(20);
                        TextField valveF = (TextField) irrig.get(23);
                        TextField contF = (TextField) irrig.get(25);

                        double threeQ, hose, shut, rot, pat, oneQ, emm, timer, wire, valve, cont;

                        try {
                            cont = Double.parseDouble(contF.getText());
                        } catch (Exception e) {
                            cont = 0.0;
                        }

                        try {
                            valve = Double.parseDouble(valveF.getText());
                        } catch (Exception e) {
                            valve = 0.0;
                        }

                        try {
                            wire = Double.parseDouble(wireF.getText());
                        } catch (Exception e) {
                            wire = 0.0;
                        }

                        try {
                            timer = Double.parseDouble(timerF.getText());
                        } catch (Exception e) {
                            timer = 0.0;
                        }

                        try {
                            emm = Double.parseDouble(emmF.getText());
                        } catch (Exception e) {
                            emm = 0.0;
                        }

                        try {
                            oneQ = Double.parseDouble(oneQF.getText());
                        } catch (Exception e) {
                            oneQ = 0.0;
                        }

                        try {
                            pat = Double.parseDouble(patF.getText());
                        } catch (Exception e) {
                            pat = 0.0;
                        }

                        try {
                            rot = Double.parseDouble(rotF.getText());
                        } catch (Exception e) {
                            rot = 0.0;
                        }

                        try {
                            shut = Double.parseDouble(shutF.getText());
                        } catch (Exception e) {
                            shut = 0.0;
                        }

                        try {
                            hose = Double.parseDouble(hoseF.getText());
                        } catch (Exception e) {
                            hose = 0.0;
                        }

                        try {
                            threeQ = Double.parseDouble(threeQF.getText());
                        } catch (Exception e) {
                            threeQ = 0.0;
                        }

                        double total = 0.0;

                        total += (threeQM * threeQ) + (threeQL * threeQ);
                        total += (hoseM * hose) + (hoseL * hose);
                        total += (shutM * shut) + (shutL * shut);
                        total += (rotM * rot) + (rotL * rot);
                        total += (patM * pat) + (patL * pat);
                        total += (oneQM * oneQ) + (oneQL * oneQ);
                        total += (emmM * emm) + (emmL * emm);
                        total += (timerM * timer) + (timerL * timer);
                        total += (wireM * wire) + (wireL * wire);
                        total += (valveM * valve) + (valveL * valve);
                        total += (contM * cont) + (contL * cont);

                        TextField servTotal = (TextField) irrig.get(irrig.size() - 1);
                        servTotal.setText(f.format(total));

                        newIrrig.setEstControlValve(cont);
                        newIrrig.setEstControlWire(wire);
                        newIrrig.setEstDripEmitter(emm);
                        newIrrig.setEstDripLine(oneQ);
                        newIrrig.setEstHoseBibs(hose);
                        newIrrig.setEstOffValves(shut);
                        newIrrig.setEstRotaryHeads(rot);
                        newIrrig.setEstSprayHaeds(pat);
                        newIrrig.setEstThreeQuarterLine(threeQ);
                        newIrrig.setEstTimerControl(cont);
                        newIrrig.setEstValveBox(valve);
                        newIrrig.setQuotedTotal(total);

                        newIrrig.setActControlValve(cont);
                        newIrrig.setActControlWire(wire);
                        newIrrig.setActDripEmitter(emm);
                        newIrrig.setActDripLine(oneQ);
                        newIrrig.setActHoseBibs(hose);
                        newIrrig.setActOffValves(shut);
                        newIrrig.setActRotaryHeads(rot);
                        newIrrig.setActSprayHaeds(pat);
                        newIrrig.setActThreeQuarterLine(threeQ);
                        newIrrig.setActTimerControl(cont);
                        newIrrig.setActValveBox(valve);
                        newIrrig.setActualTotal(total);

                        botCheck();
                    }
                });
            }
        }

        fieldCount = 0;
        return irrig;
    }

    /**
     *
     * @return
     */
    private ArrayList<Control> addSod() {

        WO_Sod newSod = new WO_Sod(true);
        orders.add(newSod);

        ArrayList<Control> sod = new ArrayList();

        Label label = new Label("Sod:");
        label.setUnderline(true);
        label.setFont(new Font(16));
        sod.add(label);

        sod.add(addLabel("SQ.FT"));
        sod.add(addField(true));

        label = new Label("    | ");
        label.setFont(new Font(20));
        sod.add(label);

        sod.add(addLabel("Supply Cost"));
        sod.add(addField(false));

        sod.add(addLabel("Est. Man Hours"));
        sod.add(addField(false));

        sod.add(addLabel("Labour Cost"));
        sod.add(addField(false));

        addPad(sod);

        addServTotal(sod);

        double supplyPerYard = dbs.sod_SupplyPerYard();
        double estManPer10 = dbs.sod_ManHoursPer10SQFT();
        double labourPerHours = dbs.sod_InstallRatePerHours();

        for (int i = 0; i < sod.size(); i++) {

            String currentEl = sod.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0")) {

                sod.get(2).setOnKeyReleased(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {

                        double sqftDbl;
                        TextField sqft = (TextField) sod.get(2);

                        try {
                            sqftDbl = Double.parseDouble(sqft.getText());
                        } catch (Exception e) {
                            sqftDbl = 0;
                        }

                        TextField supplyField = (TextField) sod.get(5);
                        Double supplyCostDbl = supplyPerYard * sqftDbl;
                        supplyField.setText(f.format(supplyCostDbl));

                        TextField hoursField = (TextField) sod.get(7);
                        Double hoursDbl = (sqftDbl / 10) * estManPer10;
                        hoursField.setText(f.format(hoursDbl));

                        TextField labourCostField = (TextField) sod.get(9);
                        Double labDbl = labourPerHours * hoursDbl;
                        labourCostField.setText(f.format(labDbl));

                        TextField totField = (TextField) sod.get(sod.size() - 1);
                        Double totDbl = labDbl + supplyCostDbl;
                        totField.setText(f.format(totDbl));

                        newSod.setEstSQFT(sqftDbl);
                        newSod.setEstSupplyCost(supplyCostDbl);
                        newSod.setEstManHours(hoursDbl);
                        newSod.setEstInstallCost(labDbl);
                        newSod.setQuotedTotal(totDbl);

                        newSod.setActSQFT(sqftDbl);
                        newSod.setActSupplyCost(supplyCostDbl);
                        newSod.setActManHours(hoursDbl);
                        newSod.setActInstallCost(labDbl);
                        newSod.setActualTotal(totDbl);

                        botCheck();
                    }
                });
            }
        }

        fieldCount = 0;
        return sod;
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
        label.setFont(new Font(16));
        bed.add(label);

        bed.add(addLabel("Material"));
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

        //aggs.setMaxHeight(10);
        aggs.setMaxWidth(100);

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

        bed.add(addLabel("Labour Cost"));
        bed.add(addField(false));

        bed.add(addLabel("Aggregate Cost"));
        bed.add(this.addField(false));

        addServTotal(bed);

        double labourRate = dbs.bed_LabourPerHour();
        double hoursPeryard = dbs.bed_ManHoursPerYard();

        for (int i = 0; i < bed.size(); i++) {

            String currentEl = bed.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1")) {

                int place;

                if (currentEl.equals("0")) {
                    place = 4;
                } else {
                    place = 6;
                }

                bed.get(place).setOnKeyReleased(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        double sqftdbl;
                        double depthdbl;

                        TextField sqft = (TextField) bed.get(4);
                        TextField depth = (TextField) bed.get(6);

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

                        //update required yards
                        TextField reqyards = (TextField) bed.get(9);
                        Double reqyardsdbl = (depthdbl / 12.0) * (sqftdbl / 27.0);
                        reqyards.setText(f.format(reqyardsdbl));

                        TextField estManHours = (TextField) bed.get(11);
                        Double manHoursDbl = hoursPeryard * reqyardsdbl;
                        estManHours.setText(f.format(manHoursDbl));

                        TextField labourCost = (TextField) bed.get(13);
                        Double labourCostDouble = (manHoursDbl * labourRate);
                        labourCost.setText(f.format(labourCostDouble));

                        TextField aggCost = (TextField) bed.get(15);
                        String aggChosen = "" + aggs.getValue();
                        Double aggDbl = 0.0;

                        if (aggChosen.contains("Crushed")) {

                            aggDbl = dbs.materials_CrushedRockUnit();

                        } else if (aggChosen.contains("Pea")) {

                            aggDbl = dbs.materials_PeaRockUnit();

                        } else if (aggChosen.contains("River")) {

                            aggDbl = dbs.materials_RiverRockUnit();

                        } else if (aggChosen.contains("Mulch")) {

                            aggDbl = dbs.materials_MulchWesternRedCedarUnit();

                        } else if (aggChosen.contains("Dust")) {

                            aggDbl = dbs.materials_CrusherDustUnit();

                        } else if (aggChosen.contains("Shale")) {

                            aggDbl = dbs.materials_RedShaleUnit();

                        } else if (aggChosen.contains("Sod")) {

                            aggDbl = dbs.materials_TopSoilPremiumMixUnit();

                        }

                        Double aggMatCost = reqyardsdbl * aggDbl;
                        aggCost.setText(f.format(aggMatCost));

                        TextField serTotal = (TextField) bed.get(17);
                        Double serTotalDbl = aggMatCost + labourCostDouble;
                        serTotal.setText(f.format(serTotalDbl));

                        newBed.setEstSQFT(sqftdbl);
                        newBed.setEstHours(manHoursDbl);
                        newBed.setEstLabour(labourCostDouble);
                        newBed.setEstReqYards(reqyardsdbl);
                        newBed.setQuotedTotal(serTotalDbl);

                        newBed.setActSQFT(sqftdbl);
                        newBed.setActHours(manHoursDbl);
                        newBed.setActLabour(labourCostDouble);
                        newBed.setActReqYards(reqyardsdbl);
                        newBed.setActualTotal(serTotalDbl);

                        botCheck();
                    }
                });

            }
        }

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

        addServTotal(hand);

        hand.add(addLabel(""));
        hand.add(addLabel(""));

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

                        TextField sqft = (TextField) hand.get(2);
                        TextField depth = (TextField) hand.get(4);

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

                        //update required yards
                        TextField reqyards = (TextField) hand.get(7);
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
                        Double truckingDbl = ((reqyardsdbl / 2) * truckingRate);
                        trucking.setText(f.format(truckingDbl));

                        //update disposal
                        TextField disp = (TextField) hand.get(15);
                        Double disDbl = (reqyardsdbl * disposalRate);
                        disp.setText(f.format(disDbl));

                        TextField serTotal = (TextField) hand.get(17);
                        Double serTotalDbl = disDbl + labourCostDouble + truckingDbl;
                        serTotal.setText(f.format(serTotalDbl));

                        newHand.setEstDepth(depthdbl);
                        newHand.setEstSQFT(sqftdbl);
                        newHand.setEstReqYards(reqyardsdbl);
                        newHand.setEstHours(estimatedManHourDbl);
                        newHand.setEstLabour(labourCostDouble);
                        newHand.setEstTrucking(truckingDbl);
                        newHand.setEstDisposal(disDbl);
                        newHand.setQuotedTotal(serTotalDbl);

                        newHand.setActDepth(depthdbl);
                        newHand.setActSQFT(sqftdbl);
                        newHand.setActReqYards(reqyardsdbl);
                        newHand.setActLabour(labourCostDouble);
                        newHand.setActHours(estimatedManHourDbl);
                        newHand.setActTrucking(truckingDbl);
                        newHand.setActDisposal(disDbl);
                        newHand.setActualTotal(serTotalDbl);

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

        ArrayList<Control> skid = new ArrayList();

        WO_Excavation newSkid = new WO_Excavation('s', true);
        orders.add(newSkid);

        Label label = new Label("Excavation By Skid Steer:");
        label.setUnderline(true);
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

        addServTotal(skid);

        double labourhours = dbs.excavation_ManHoursBySkidPerYards();
        double labourRate = dbs.excavation_ManHoursBySkidPerHours();
        double truckingRate = dbs.excavation_TruckingFeeBySkid();

        for (int i = 0; i < skid.size(); i++) {

            String currentEl = skid.get(i).getId();

            if (currentEl == null) {
                currentEl = "-1";
            }

            if (currentEl.equals("0") || currentEl.equals("1")) {

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

                        TextField sqft = (TextField) skid.get(2);
                        TextField depth = (TextField) skid.get(4);

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

                        //update required yards
                        TextField reqyards = (TextField) skid.get(7);
                        Double reqyardsdbl = (depthdbl / 12.0) * (sqftdbl / 27.0);
                        reqyards.setText(f.format(reqyardsdbl));

                        //update estimated man hours per yard
                        TextField estManHours = (TextField) skid.get(9);
                        Double estimatedManHourDbl = (labourhours * reqyardsdbl);
                        estManHours.setText(f.format(estimatedManHourDbl));

                        //update labour cost
                        TextField labourCost = (TextField) skid.get(11);
                        Double labourCostDouble = (estimatedManHourDbl * labourRate);
                        labourCost.setText(f.format(labourCostDouble));

                        //update trucking fee
                        TextField trucking = (TextField) skid.get(13);
                        Double truckingDbl = ((reqyardsdbl / 2) * truckingRate);
                        trucking.setText(f.format(truckingDbl));

                        TextField serTotal = (TextField) skid.get(17);
                        Double serTotalDbl = labourCostDouble + truckingDbl;
                        serTotal.setText(f.format(serTotalDbl));

                        newSkid.setEstDepth(depthdbl);
                        newSkid.setEstSQFT(sqftdbl);
                        newSkid.setEstReqYards(reqyardsdbl);
                        newSkid.setEstHours(estimatedManHourDbl);
                        newSkid.setEstLabour(labourCostDouble);
                        newSkid.setEstTrucking(truckingDbl);
                        newSkid.setQuotedTotal(serTotalDbl);

                        newSkid.setActDepth(depthdbl);
                        newSkid.setActSQFT(sqftdbl);
                        newSkid.setActReqYards(reqyardsdbl);
                        newSkid.setActLabour(labourCostDouble);
                        newSkid.setActHours(estimatedManHourDbl);
                        newSkid.setActTrucking(truckingDbl);
                        newSkid.setActualTotal(serTotalDbl);

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
     * @param list
     */
    private void addServTotal(ArrayList<Control> list) {

        Label label = new Label("Service Total  ");
        label.setUnderline(true);
        label.setFont(new Font(16));
        list.add(label);

        TextField totalField = new TextField("0");
        totalField.setId(fieldCount + "");
        totalField.setMaxWidth(100);
        totalField.setMinWidth(100);
        totalField.setAlignment(Pos.CENTER);
        totalField.setEditable(false);
        list.add(totalField);
    }

    /**
     *
     * @return
     */
    private TextField addField(boolean editable) {

        TextField field = new TextField("0");
        field.setMaxWidth(60);
        field.setMinWidth(60);
        field.setId(fieldCount + "");
        field.setAlignment(Pos.CENTER);
        field.setEditable(editable);
        fieldCount++;
        return field;
    }

    private double takenUp(ArrayList<Control> list) {

        double total = 0;

        for (int i = 0; i < list.size(); i++) {

            total += list.get(i).getWidth();

        }
        ArrayList<Control> servSpace = new ArrayList();
        addServTotal(servSpace);

        for (int i = 0; i < servSpace.size(); i++) {

            total += servSpace.get(i).getWidth();

        }

        return total;
    }

    /**
     *
     * @param text
     * @return
     */
    private Label addLabel(String text) {

        Label label = new Label(text);
        return label;
    }

    /**
     *
     */
    private void botCheck() {

        double ttl = 0;

        for (int i = 0; i < orders.size(); i++) {

            ttl += orders.get(i).getQuotedTotal();
        }

        projectTotal = ttl;

        bottomLine.setText(f.format(projectTotal + (taxMultiplier * projectTotal)));

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

        FlowPane flo = new FlowPane();

        for (int i = 0; i < elements.size(); i++) {

            flo.getChildren().addAll(elements.get(i));
        }

        flo.setPrefWrapLength(1850);
        flo.setMinWidth(1850);
        flo.setMaxWidth(1850);

        anc.getChildren().add(flo);
    }
}
