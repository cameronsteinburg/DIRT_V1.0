/**
 * This class controls what happens under Edit-> User Constants in Menu Bar in GUI
 */
package controller;

import java.net.URL;
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
    private TextField field1;
    @FXML
    private TextField field2;
    @FXML
    private TextField field3;
    @FXML
    private TextField field4;
    @FXML
    private TextField field5;
    @FXML
    private TextField field6;
    @FXML
    private TextField field7;
    @FXML
    private TextField field8;
    @FXML
    private TextField field9;
    @FXML
    private TextField field10;
    @FXML
    private TextField field11;
    @FXML
    private TextField field12;
    @FXML
    private TextField field13;
    @FXML
    private TextField field14;
    @FXML
    private TextField field15;
    @FXML
    private TextField field16;
    @FXML
    private TextField field17;
    @FXML
    private TextField field18;
    @FXML
    private TextField field19;
    @FXML
    private TextField field20;
    @FXML
    private TextField field21;
    @FXML
    private TextField field22;
    @FXML
    private TextField field23;
    @FXML
    private TextField field24;
    
    private Label errorMessage;
    
    @FXML
    private void saveBtnAction(ActionEvent event){
    
        String f1s = field1.getText();
        String f2s = field2.getText();
        String f3s = field3.getText();
        String f4s = field4.getText();
        String f5s = field5.getText();
        String f6s = field6.getText();
        String f7s = field7.getText();
        String f8s = field8.getText();
        String f9s = field9.getText();
        String f10s = field10.getText();
        String f11s = field11.getText();
        String f12s = field12.getText();
        String f13s = field13.getText();
        String f14s = field14.getText();
        String f15s = field15.getText();
        String f16s = field16.getText();
        String f17s = field17.getText();
        String f18s = field18.getText();
        String f19s = field19.getText();
        String f20s = field20.getText();
        String f21s = field21.getText();
        String f22s = field22.getText();
        String f23s = field23.getText();
        String f24s = field24.getText();
        //theres gonna be more
        
        //broken up for readbiliy, not fields are allowed to be empty on this page
        if(f1s == null || f2s == null || f3s == null || f4s == null || f5s == null || f6s == null || f7s == null || f8s == null || f9s == null || f10s == null || f11s == null || f12s == null){
            
            errorMessage.setText("No Field Can Be Left Blank");
            return;
        }
        
        if(f13s == null || f14s == null || f15s == null || f16s == null || f17s == null || f18s == null || f19s == null || f20s == null || f21s == null || f22s == null || f23s == null || f24s == null){
            
            errorMessage.setText("No Field Can Be Left Blank");
            return;
        }
        
        double f1d;
        
        try{
        
            f1d = Double.parseDouble(f1s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f2d;
        
        try{
        
            f2d = Double.parseDouble(f2s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f3d;
        
        try{
        
            f3d = Double.parseDouble(f3s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f4d;
        
        try{
        
            f4d = Double.parseDouble(f4s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f5d;
        
        try{
        
            f5d = Double.parseDouble(f5s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f6d;
        
        try{
        
            f6d = Double.parseDouble(f6s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f7d;
        
        try{
        
            f7d = Double.parseDouble(f7s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f8d;
        
        try{
        
            f8d = Double.parseDouble(f8s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f9d;
        
        try{
        
            f9d = Double.parseDouble(f9s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f10d;
        
        try{
        
            f10d = Double.parseDouble(f10s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f11d;
        
        try{
        
            f11d = Double.parseDouble(f11s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f12d;
        
        try{
        
            f12d = Double.parseDouble(f12s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f13d;
        
        try{
        
            f13d = Double.parseDouble(f13s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f14d;
        
        try{
        
            f14d = Double.parseDouble(f14s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f15d;
        
        try{
        
            f15d = Double.parseDouble(f15s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f16d;
        
        try{
        
            f16d = Double.parseDouble(f16s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f17d;
        
        try{
        
            f17d = Double.parseDouble(f17s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f18d;
        
        try{
        
            f18d = Double.parseDouble(f18s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f19d;
        
        try{
        
            f19d = Double.parseDouble(f19s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f20d;
        
        try{
        
            f20d = Double.parseDouble(f20s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f21d;
        
        try{
        
            f21d = Double.parseDouble(f21s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f22d;
        
        try{
        
            f22d = Double.parseDouble(f22s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f23d;
        
        try{
        
            f23d = Double.parseDouble(f23s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        double f24d;
        
        try{
        
            f24d = Double.parseDouble(f24s);
            
        } catch (Exception e){
        
            errorMessage.setText("One of the Fields is Not a Proper Number");
            return;
        }
        
        
        if(f1s.length() > 6 || f2s.length() > 6 || f3s.length() > 6 || f4s.length() > 6 || f5s.length() > 6 || f6s.length() > 6 || f7s.length() > 6 || f8s.length() > 6 || f9s.length() > 6 || f10s.length() > 6 || f11s.length() > 6 || f12s.length() > 6 ){
         
            errorMessage.setText("One of the Numbers is Too Big");
            return;
        }
        
        if(f13s.length() > 6 || f14s.length() > 6 || f15s.length() > 6 || f16s.length() > 6 || f17s.length() > 6 || f18s.length() > 6 || f19s.length() > 6 || f20s.length() > 6 || f21s.length() > 6 || f22s.length() > 6 || f23s.length() > 6 || f24s.length() > 6 ){
            
            errorMessage.setText("One of the Numbers is Too Big");
            return;
        }
        //data is valid at this point
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        DBServices dbs = new DBServices();
        
        field1.setText(dbs.materials_CrushedRockRetail()+ "");
        field2.setText(dbs.materials_CrushedRockUnit()+ "");
        field3.setText(dbs.materials_PeaRockRetail()+ "");
        field4.setText(dbs.materials_PeaRockUnit()+ "");
        field5.setText(dbs.materials_RiverRockRetail()+ "");
        field6.setText(dbs.materials_RiverRockUnit() + "");
        field7.setText(dbs.materials_MulchWesternRedCedarRetail() + "");
        field8.setText(dbs.materials_MulchWesternRedCedarUnit() + "");
        field9.setText(dbs.materials_TopSoilPremiumMixRetail()+ "");
        field10.setText(dbs.materials_TopSoilPremiumMixUnit()+ "");
        field11.setText(dbs.materials_CrusherDustRetail()+ "");
        field12.setText(dbs.materials_CrusherDustUnit()+ "");
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
        field23.setText(dbs.bed_InstallPerYards() + "");
        field24.setText(dbs.materials_SodPer10SQFTRetail() + "");
        
    }     

   
    protected void setErrorMessage(Label error) {
        
        this.errorMessage = error;
    }
}
