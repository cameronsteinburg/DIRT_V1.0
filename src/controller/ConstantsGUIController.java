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
                        field71,field72,field73,field74,field75,field76,field77,field78,field79,field80;
    
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
        
        //Instantiate Variables
        TextField field = null;
        Double doubleVal;
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
                
                errorMessage.setText("One of the Fiels is Not a Proper Number");
                return;
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DecimalFormat f = new DecimalFormat("#.00");
        
        DBServices dbs = new DBServices();

        field1.setText(f.format(dbs.materials_CrushedRockRetail()));
        field2.setText(f.format(dbs.materials_CrushedRockUnit()));
        field3.setText(dbs.materials_PeaRockRetail() + "");
        field4.setText(dbs.materials_PeaRockUnit() + "");
        field5.setText(dbs.materials_RiverRockRetail() + "");
        field6.setText(dbs.materials_RiverRockUnit() + "");
        field7.setText(dbs.materials_MulchWesternRedCedarRetail() + "");
        field8.setText(dbs.materials_MulchWesternRedCedarUnit() + "");
        field9.setText(dbs.materials_TopSoilPremiumMixRetail() + "");
        field10.setText(dbs.materials_TopSoilPremiumMixUnit() + "");
        field11.setText(dbs.materials_CrusherDustRetail() + "");
        field12.setText(dbs.materials_CrusherDustUnit() + "");
        field13.setText(dbs.materials_RedShaleRetail() + "");
        field14.setText(dbs.materials_RedShaleUnit() + "");
        field15.setText(dbs.materials_SodPer10SQFTRetail() + "");
        field16.setText(dbs.materials_SodPer10SQFTUnit() + "");
        field17.setText(dbs.excavation_ManHoursByHandPerHours() + "");
        field18.setText(dbs.excavation_ManHoursBySkidPerHours() + "");
        field19.setText(dbs.excavation_TruckingFee() + "");
        field20.setText(dbs.excavation_DisposalFee() + "");
        field21.setText(dbs.excavation_ManHoursByHandPerYards() + "");
        field22.setText(dbs.excavation_ManHoursBySkidPerYards() + "");
        field23.setText(dbs.bed_HoursPerHours() + "");
        field24.setText(dbs.bed_InstallPerYards() + "");
        //TODO: field25 - field80 Data Retrieval
        field25.setText(dbs.bed_InstallPerYards() + "");
        field26.setText(dbs.bed_InstallPerYards() + "");
        field27.setText(dbs.bed_InstallPerYards() + "");
        field28.setText(dbs.bed_InstallPerYards() + "");
        field29.setText(dbs.bed_InstallPerYards() + "");
        field30.setText(dbs.bed_InstallPerYards() + "");
        field31.setText(dbs.bed_InstallPerYards() + "");
        field32.setText(dbs.bed_InstallPerYards() + "");
        field33.setText(dbs.bed_InstallPerYards() + "");
        field34.setText(dbs.bed_InstallPerYards() + "");
        field35.setText(dbs.bed_InstallPerYards() + "");
        field36.setText(dbs.bed_InstallPerYards() + "");
        field37.setText(dbs.bed_InstallPerYards() + "");
        field38.setText(dbs.bed_InstallPerYards() + "");
        field39.setText(dbs.bed_InstallPerYards() + "");
        field40.setText(dbs.bed_InstallPerYards() + "");
        field41.setText(dbs.bed_InstallPerYards() + "");
        field42.setText(dbs.bed_InstallPerYards() + "");
        field43.setText(dbs.bed_InstallPerYards() + "");
        field44.setText(dbs.bed_InstallPerYards() + "");
        field45.setText(dbs.bed_InstallPerYards() + "");
        field46.setText(dbs.bed_InstallPerYards() + "");
        field47.setText(dbs.bed_InstallPerYards() + "");
        field48.setText(dbs.bed_InstallPerYards() + "");
        field49.setText(dbs.bed_InstallPerYards() + "");
        field50.setText(dbs.bed_InstallPerYards() + "");
        field51.setText(dbs.bed_InstallPerYards() + "");
        field52.setText(dbs.bed_InstallPerYards() + "");
        field53.setText(dbs.bed_InstallPerYards() + "");
        field54.setText(dbs.bed_InstallPerYards() + "");
        field55.setText(dbs.bed_InstallPerYards() + "");
        field56.setText(dbs.bed_InstallPerYards() + "");
        field57.setText(dbs.bed_InstallPerYards() + "");
        field58.setText(dbs.bed_InstallPerYards() + "");
        field59.setText(dbs.bed_InstallPerYards() + "");
        field60.setText(dbs.bed_InstallPerYards() + "");
        field61.setText(dbs.bed_InstallPerYards() + "");
        field62.setText(dbs.bed_InstallPerYards() + "");
        field63.setText(dbs.bed_InstallPerYards() + "");
        field64.setText(dbs.bed_InstallPerYards() + "");
        field65.setText(dbs.bed_InstallPerYards() + "");
        field66.setText(dbs.bed_InstallPerYards() + "");
        field67.setText(dbs.bed_InstallPerYards() + "");
        field68.setText(dbs.bed_InstallPerYards() + "");
        field69.setText(dbs.bed_InstallPerYards() + "");
        field70.setText(dbs.bed_InstallPerYards() + "");
        field71.setText(dbs.bed_InstallPerYards() + "");
        field72.setText(dbs.bed_InstallPerYards() + "");
        field73.setText(dbs.bed_InstallPerYards() + "");
        field74.setText(dbs.bed_InstallPerYards() + "");
        field75.setText(dbs.bed_InstallPerYards() + "");
        field76.setText(dbs.bed_InstallPerYards() + "");
        field77.setText(dbs.bed_InstallPerYards() + "");
        field78.setText(dbs.bed_InstallPerYards() + "");
        field79.setText(dbs.bed_InstallPerYards() + "");
        field80.setText(dbs.bed_InstallPerYards() + "");

    }
    
    protected void setErrorMessage(Label error) {

        this.errorMessage = error;
    }
}
