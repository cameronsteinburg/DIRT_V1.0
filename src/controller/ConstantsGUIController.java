/**
 * This class controls what happens under Edit-> User Constants in Menu Bar in GUI
 */
package controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.DBServices;

public class ConstantsGUIController extends Controller implements Initializable {

    //these fields are shown left to right in each section of the gui, numericaly ordered this way, first in top left, last in bottom right
    @FXML
    private TextField field1,field2,field3,field4,field5,field6,field7,field8,field9,field10,
                        field11,field12,field13,field14,field15,field16,field17,field18,field19,field20,
                        field21,field22,field23,field24,field25,field26,field27,field28,field29,field30,
                        field31,field32,field33,field34,field35,field36,field37,field38,field39,field40,
                        field41,field42,field43,field44,field45,field46,field47,field48,field49,field50,
                        field51,field52,field53,field54,field55,field56,field57,field58,field59,field60,
                        field61,field62,field63,field64,field65,field66,field67,field68,field69,field70,
                        field71,field72,field73,field74,field75,field76,field77,field78,field79,field80,field81,field82;
    
    @FXML
    private Button saveBtn;
    
    private ArrayList<TextField> list = new ArrayList();
  
    private Label errorMessage;

    @FXML
    private void saveBtnAction(ActionEvent event) {

        //Create ArrayList of values in the TextFields
        list.add(field1);
        list.add(field2);
        list.add(field3);
        list.add(field4);
        list.add(field5);
        list.add(field6);
        list.add(field7);
        list.add(field8);
        list.add(field9);
        list.add(field10);
        list.add(field11);
        list.add(field12);
        list.add(field13);
        list.add(field14);
        list.add(field15);
        list.add(field16);
        list.add(field17);
        list.add(field18);
        list.add(field19);
        list.add(field20);
        list.add(field21);
        list.add(field22);
        list.add(field23);
        list.add(field24);
        list.add(field25);
        list.add(field26);
        list.add(field27);
        list.add(field28);
        list.add(field29);
        list.add(field30);
        list.add(field31);
        list.add(field32);
        list.add(field33);
        list.add(field34);
        list.add(field35);
        list.add(field36);
        list.add(field37);
        list.add(field38);
        list.add(field39);
        list.add(field40);
        list.add(field41);
        list.add(field42);
        list.add(field43);
        list.add(field44);
        list.add(field45);
        list.add(field46);
        list.add(field47);
        list.add(field48);
        list.add(field49);
        list.add(field50);
        list.add(field51);
        list.add(field52);
        list.add(field53);
        list.add(field54);
        list.add(field55);
        list.add(field56);
        list.add(field57);
        list.add(field58);
        list.add(field59);
        list.add(field60);
        list.add(field61);
        list.add(field62);
        list.add(field63);
        list.add(field64);
        list.add(field65);
        list.add(field66);
        list.add(field67);
        list.add(field68);
        list.add(field69);
        list.add(field70);
        list.add(field71);
        list.add(field72);
        list.add(field73);
        list.add(field74);
        list.add(field75);
        list.add(field76);
        list.add(field77);
        list.add(field78);
        list.add(field79);
        list.add(field80);
        list.add(field81);
        list.add(field82);
        
        //Instantiate Variables
        TextField field = null;
        Double doubleVal; //it is being used dont let netbeans deceive you
        String fieldText = null;
        
        //Loop through array to validate information
        for(int i = 0; i < list.size(); i++) {
            
            field = list.get(i);

            try {
                
                fieldText = field.getText();    
            }       
            catch(Exception e) {
                
                errorMessage.setText("One of the Fields is Blank");
                return;
            }
            
            try {
                
                doubleVal = Double.parseDouble(fieldText);
            }
            catch (Exception e) {
                
                errorMessage.setText("One of the Fields is Either Blank Or Not a Proper Number");
                return;
            }//data is valid at this point
        }
            
            DBServices dbs = new DBServices();
            
            //broken up by section in gui
            dbs.setmaterials_CrushedRockRetail(Double.parseDouble(field1.getText()));  
            dbs.setmaterials_CrushedRockUnit(Double.parseDouble(field2.getText()));
            dbs.setmaterials_PeaRockRetail(Double.parseDouble(field3.getText()));
            dbs.setmaterials_PeaRockUnit(Double.parseDouble(field4.getText()));
            dbs.setmaterials_RiverRockUnit(Double.parseDouble(field5.getText()));
            dbs.setmaterials_RiverRockRetail(Double.parseDouble(field6.getText()));
            dbs.setmaterials_MulchWesternRedCedarRetail(Double.parseDouble(field7.getText()));
            dbs.setmaterials_MulchWesternRedCedarUnit(Double.parseDouble(field8.getText()));
            dbs.setmaterials_TopSoilPremiumMixRetail(Double.parseDouble(field9.getText()));
            dbs.setmaterials_TopSoilPremiumMixUnit(Double.parseDouble(field10.getText()));
            dbs.setmaterials_CrusherDustRetail(Double.parseDouble(field11.getText()));
            dbs.setmaterials_CrusherDustUnit(Double.parseDouble(field12.getText()));
            dbs.setmaterials_RedShaleRetail(Double.parseDouble(field13.getText()));
            dbs.setmaterials_RedShaleUnit(Double.parseDouble(field14.getText()));
            dbs.setmaterials_SodPer10SQFTRetail(Double.parseDouble(field15.getText()));
            dbs.setmaterials_SodPer10SQFTUnit(Double.parseDouble(field16.getText()));
            
            dbs.setexcavation_ManHoursBySkidPerYards(Double.parseDouble(field17.getText()));
            dbs.setexcavation_ManHoursBySkidPerHours(Double.parseDouble(field18.getText()));
            dbs.setexcavation_ManHoursByHandPerYards(Double.parseDouble(field19.getText()));
            dbs.setexcavation_ManHoursByHandPerHours(Double.parseDouble(field20.getText()));
            dbs.setexcavation_TruckingFeeByHand(Double.parseDouble(field21.getText()));
            dbs.setexcavation_DisposalFee(Double.parseDouble(field22.getText()));  
            
            dbs.setstonewalkway_EstimatedManHours(Double.parseDouble(field23.getText()));
            dbs.setstonewalkway_InstallRatePerHours(Double.parseDouble(field24.getText()));   
            
            dbs.setgeotextilewalkway_WeedBarrierCostPerSQFT(Double.parseDouble(field25.getText()));
            dbs.setgeotextilewalkway_FabricStaplesQTYPerOneFifthSQFT(Double.parseDouble(field26.getText()));
            dbs.setgeotextilewalkway_FabricStaplesCostPerStaple(Double.parseDouble(field27.getText()));
            dbs.setgeotextilewalkway_FabricManHoursPer100SQFT(Double.parseDouble(field28.getText()));
            dbs.setgeotextilewalkway_FabricInstallRatePerHours(Double.parseDouble(field29.getText()));
            
            dbs.setwalkwaybase_CrushedBaseSQFTPerInchPerYard(Double.parseDouble(field30.getText()));
            dbs.setwalkwaybase_CrausedCostPerYard(Double.parseDouble(field31.getText()));
            dbs.setwalkwaybase_ManHoursPerYard(Double.parseDouble(field32.getText()));
            dbs.setwalkwaybase_InstallRatePerHours(Double.parseDouble(field33.getText()));
            
            dbs.setscreedsand_DepthSQFTPerYard(Double.parseDouble(field34.getText()));
            dbs.setscreedsand_CostPerYard(Double.parseDouble(field35.getText()));
            dbs.setscreedsand_ManHoursPerYard(Double.parseDouble(field36.getText()));
            dbs.setscreedsand_InstallPerHour(Double.parseDouble(field37.getText()));
            
            dbs.setedgerestraint_CostPer8FT(Double.parseDouble(field38.getText()));
            dbs.setedgerestraint_NailsPer1Nail(Double.parseDouble(field39.getText()));
            dbs.setedgerestraint_ManHours(Double.parseDouble(field40.getText()));
            dbs.setedgerestraint_InstallPerHours(Double.parseDouble(field41.getText()));
            
            dbs.setjointingsand_QTYkgPersfAtOneQuarterInch(Double.parseDouble(field42.getText()));
            dbs.setjointingsand_CostPerKg(Double.parseDouble(field43.getText()));
            dbs.setjointingsand_HoursPerKg(Double.parseDouble(field44.getText()));
            dbs.setjointingsand_InstallPerHours(Double.parseDouble(field45.getText())); 
            
            dbs.setbed_HoursPerHours(Double.parseDouble(field46.getText()));
            dbs.setbed_InstallPerYards(Double.parseDouble(field47.getText()));
            
            dbs.setretainingwall_CrushedBaseCostPerYard(Double.parseDouble(field48.getText()));
            dbs.setretainingwall_CrushedBaseInstallHoursPerYard(Double.parseDouble(field49.getText()));
            dbs.setretainingwall_CrushedBaseInstallRatePerYard(Double.parseDouble(field50.getText()));
            dbs.setretainingwall_BaseRowInstallHoursPerLineFeet(Double.parseDouble(field51.getText()));
            dbs.setretainingwall_BaseRowInstallRatePerHour(Double.parseDouble(field52.getText()));
            dbs.setretainingwall_BlockCostPerLineFeet(Double.parseDouble(field53.getText()));
            
            dbs.setsod_SupplyPerYard(Double.parseDouble(field54.getText()));
            dbs.setsod_ManHoursPer10SQFT(Double.parseDouble(field55.getText()));
            dbs.setsod_InstallRatePerHours(Double.parseDouble(field56.getText()));
            
            dbs.setweedbarrier_ManHoursPer500SQFT(Double.parseDouble(field57.getText()));
            dbs.setweedbarrier_StaplesPer500SQFT(Double.parseDouble(field58.getText()));
            dbs.setweedbarrier_CostPerStaples(Double.parseDouble(field59.getText()));
            dbs.setweedbarrier_BarrierSupplyPer500SQFT(Double.parseDouble(field60.getText()));
             
            dbs.setirrigation_HoseBibsMaterial(Double.parseDouble(field61.getText()));
            dbs.setirrigation_HoseBibsLabour(Double.parseDouble(field62.getText()));
            dbs.setirrigation_ShutOffValveMaterial(Double.parseDouble(field63.getText()));
            dbs.setirrigation_ShutOffValveLabour(Double.parseDouble(field64.getText()));
            dbs.setirrigation_RotaryHeadMaterial(Double.parseDouble(field65.getText()));
            dbs.setirrigation_RotaryHeadLabour(Double.parseDouble(field66.getText()));
            dbs.setirrigation_SprayHeadMaterial(Double.parseDouble(field67.getText()));
            dbs.setirrigation_SprayHeadLabour(Double.parseDouble(field68.getText()));
                       
            dbs.setirrigation_Drip1QuarterInchPerFootMaterial(Double.parseDouble(field69.getText()));
            dbs.setirrigation_Drip1QuarterInchPerFootLabour(Double.parseDouble(field70.getText()));
            dbs.setirrigation_DripEmitterMaterial(Double.parseDouble(field71.getText()));
            dbs.setirrigation_DripEmitterLabour(Double.parseDouble(field72.getText()));
            dbs.setirrigation_TimerControlMaterial(Double.parseDouble(field73.getText()));
            dbs.setirrigation_TimerControlLabour(Double.parseDouble(field74.getText()));
            dbs.setirrigation_ControlWirePer100FeetMaterial(Double.parseDouble(field75.getText()));
            dbs.setirrigation_ControlWirePer100FeetLabour(Double.parseDouble(field76.getText()));
            
            dbs.setirrigation_ValveBoxMaterial(Double.parseDouble(field77.getText()));
            dbs.setirrigation_ValveBoxLabour(Double.parseDouble(field78.getText()));
            dbs.setirrigation_ControlValveMaterial(Double.parseDouble(field79.getText()));
            dbs.setirrigation_ControlValveLabour(Double.parseDouble(field80.getText()));
            dbs.setirrigation_3QuarterLiningMaterial(Double.parseDouble(field81.getText()));
            dbs.setirrigation_3QuarterLiningLabour(Double.parseDouble(field82.getText()));
            
            System.out.println(saveBtn.getUserData());
           
            errorMessage.setText("Changes to Constants Saved!");  
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        saveBtn.setUserData(1);

        DecimalFormat f = new DecimalFormat("0.00");
        
        DBServices dbs = new DBServices();
        
        //materials tab
        field1.setText(f.format(dbs.materials_CrushedRockRetail()));
        field2.setText(f.format(dbs.materials_CrushedRockUnit()));
        field3.setText(f.format(dbs.materials_PeaRockRetail()));
        field4.setText(f.format(dbs.materials_PeaRockUnit()));
        field5.setText(f.format(dbs.materials_RiverRockRetail()));
        field6.setText(f.format(dbs.materials_RiverRockUnit()));
        field7.setText(f.format(dbs.materials_MulchWesternRedCedarRetail()));
        field8.setText(f.format(dbs.materials_MulchWesternRedCedarUnit()));
        field9.setText(f.format(dbs.materials_TopSoilPremiumMixRetail()));
        field10.setText(f.format(dbs.materials_TopSoilPremiumMixUnit()));
        field11.setText(f.format(dbs.materials_CrusherDustRetail()));
        field12.setText(f.format(dbs.materials_CrusherDustUnit()));
        field13.setText(f.format(dbs.materials_RedShaleRetail()));
        field14.setText(f.format(dbs.materials_RedShaleUnit()));
        field15.setText(f.format(dbs.materials_SodPer10SQFTRetail()));
        field16.setText(f.format(dbs.materials_SodPer10SQFTUnit()));
        
        //excavation tab
        field17.setText(f.format(dbs.excavation_ManHoursBySkidPerYards()));  
        field18.setText(f.format(dbs.excavation_ManHoursBySkidPerHours()));
        field19.setText(f.format(dbs.excavation_ManHoursByHandPerYards()));
        field20.setText(f.format(dbs.excavation_ManHoursByHandPerHours()));
        field21.setText(f.format(dbs.excavation_TruckingFeeByHand()));
        field22.setText(f.format(dbs.excavation_DisposalFee()));

        //walkway tab
            //stone
        field23.setText(f.format(dbs.stonewalkway_EstimatedManHours()));
        field24.setText(f.format(dbs.stonewalkway_InstallRatePerHours()));
        
            //geotextile
        field25.setText(f.format(dbs.geotextilewalkway_WeedBarrierCostPerSQFT()));
        field26.setText(f.format(dbs.geotextilewalkway_FabricStaplesQTYPerOneFifthSQFT()));
        field27.setText(f.format(dbs.geotextilewalkway_FabricStaplesCostPerStaple()));
        field28.setText(f.format(dbs.geotextilewalkway_FabricManHoursPer100SQFT()));
        field29.setText(f.format(dbs.geotextilewalkway_FabricInstallRatePerHours()));
        
            //base
        field30.setText(f.format(dbs.walkwaybase_CrushedBaseSQFTPerInchPerYard()));
        field31.setText(f.format(dbs.walkwaybase_CrausedCostPerYard()));
        field32.setText(f.format(dbs.walkwaybase_ManHoursPerYard()));
        field33.setText(f.format(dbs.walkwaybase_InstallRatePerHours()));
        
            //screed sand
        field34.setText(f.format(dbs.screedsand_DepthSQFTPerYard()));
        field35.setText(f.format(dbs.screedsand_CostPerYard()));
        field36.setText(f.format(dbs.screedsand_ManHoursPerYard()));
        field37.setText(f.format(dbs.screedsand_InstallPerHour()));
        
            //edge restraint
        field38.setText(f.format(dbs.edgerestraint_CostPer8FT()));
        field39.setText(f.format(dbs.edgerestraint_CostPer1Nail()));
        field40.setText(f.format(dbs.edgerestraint_ManHours()));
        field41.setText(f.format(dbs.edgerestraint_InstallPerHours()));
        
            //jointing sand
        field42.setText(f.format(dbs.jointingsand_QTYkgPersfAtOneQuarterInch()));
        field43.setText(f.format(dbs.jointingsand_CostPerKg()));
        field44.setText(f.format(dbs.jointingsand_HoursPerKg()));
        field45.setText(f.format(dbs.jointingsand_InstallPerHours()));
        
        //bed tab
        field46.setText(f.format(dbs.bed_LabourPerHour()));
        field47.setText(f.format(dbs.bed_ManHoursPerYard()));
        
        //retaining wall tab
        field48.setText(f.format(dbs.retainingwall_CrushedBaseCostPerYard()));
        field49.setText(f.format(dbs.retainingwall_CrushedBaseInstallHoursPerYard()));
        field50.setText(f.format(dbs.retainingwall_CrushedBaseInstallRatePerHour()));
        field51.setText(f.format(dbs.retainingwall_BaseRowInstallHoursPerLineFeet()));
        field52.setText(f.format(dbs.retainingwall_BaseRowInstallRatePerHour()));
        field53.setText(f.format(dbs.retainingwall_BlockCostPerLineFeet()));
        
        //sod tab
        field54.setText(f.format(dbs.sod_SupplyPerYard()));
        field55.setText(f.format(dbs.sod_ManHoursPer10SQFT()));
        field56.setText(f.format(dbs.sod_InstallRatePerHours()));
        
        //weed barrier tab
        field57.setText(f.format(dbs.weedbarrier_ManHoursPer500SQFT()));
        field58.setText(f.format(dbs.weedbarrier_StaplesPer500SQFT()));
        field59.setText(f.format(dbs.weedbarrier_CostPerStaples()));
        field60.setText(f.format(dbs.weedbarrier_BarrierSupplyPer500SQFT()));
             
        //Irrigation Tab
            //first sectin
        field61.setText(f.format(dbs.irrigation_HoseBibsMaterial()));
        field62.setText(f.format(dbs.irrigation_HoseBibsLabour()));
        field63.setText(f.format(dbs.irrigation_ShutOffValveMaterial()));
        field64.setText(f.format(dbs.irrigation_ShutOffValveLabour()));
        field65.setText(f.format(dbs.irrigation_RotaryHeadMaterial()));
        field66.setText(f.format(dbs.irrigation_RotaryHeadLabour()));
        field67.setText(f.format(dbs.irrigation_SprayHeadMaterial()));
        field68.setText(f.format(dbs.irrigation_SprayHeadLabour()));
        
            //second section
        field69.setText(f.format(dbs.irrigation_Drip1QuarterInchPerFootMaterial()));
        field70.setText(f.format(dbs.irrigation_Drip1QuarterInchPerFootLabour()));
        field71.setText(f.format(dbs.irrigation_DripEmitterMaterial()));
        field72.setText(f.format(dbs.irrigation_DripEmitterLabour()));
        field73.setText(f.format(dbs.irrigation_TimerControlMaterial()));
        field74.setText(f.format(dbs.irrigation_TimerControlLabour()));
        field75.setText(f.format(dbs.irrigation_ControlWirePer100FeetMaterial()));
        field76.setText(f.format(dbs.irrigation_ControlWirePer100FeetLabour()));
        
            //third section
        field82.setText(f.format(dbs.irrigation_3QuarterLiningLabour()));
        field81.setText(f.format(dbs.irrigation_3QuarterLiningMaterial()));
        field77.setText(f.format(dbs.irrigation_ValveBoxMaterial()));
        field78.setText(f.format(dbs.irrigation_ValveBoxLabour()));
        field79.setText(f.format(dbs.irrigation_ControlValveMaterial()));
        field80.setText(f.format(dbs.irrigation_ControlValveLabour()));   
        
    }
    
    protected void setErrorMessage(Label error) {

        this.errorMessage = error;
    }
}
