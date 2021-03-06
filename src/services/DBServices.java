package services;

import application.Main;
import entity.Client;
import entity.Labourer;
import entity.Project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 * Methods that have direct access and control to the databases
 */
public class DBServices {


    /**
     * gets a client object from the database via jdbccommands based on name
     *
     * @param num 
     * @return the Client object
     */
    public Client getClient(int num) {
        return Main.jdbcc.getClient(num);
    }
    
    /**
     * gets a client from the database based ont he first and last name via jdbccommands
     * @param first the first name of the client to look for
     * @param last the last name of the client to look for
     * @return  the client object retrieved
     */
    public Client getClient(String first, String last) {
        return Main.jdbcc.getClient(first, last);
    }

    /**
     * gets all clients from the database as an arraylist via jdbccommands
     * @param getDeleted if true also gets logically deleted clients
     * @return an arraylist of the clients found
     */
    public ArrayList<Client> getClients(boolean getDeleted) {
        return Main.jdbcc.getClients(getDeleted);
    }

    /**
     * gets all clients from the database as an observable list which can be parsed by jfxml via jdbccommands
     * @return the list of clients found
     */
    public ObservableList<Client> getClientsForTable() {
        return Main.jdbcc.getClientsForTable(false);
    }

    /**
     * persists the passed client to the database via jdbccommands
     * @param client the client to be saved
     */
    public void persistClient(Client client) {
        Main.jdbcc.persistClient(client);
    }

    /**
     * updates a client in the database via jdbccommands
     * @param clientOld the data of the old client object
     * @param clientNew the data from the edited client object being saved
     */
    public void updateClient(Client clientOld, Client clientNew) {
        Main.jdbcc.updateClient(clientOld, clientNew);
    }
    
    /**
     *
     * @param old
     * @param newProj
     */
    public void updateProject(Project old, Project newProj){
        Main.jdbcc.updateProject(old, newProj);
    }

    /**
     * Logically deletes a client from the database via jdbccommands
     *
     * @param client the client to be logically deleted
     * @return true if no errors occur
     */
    public boolean deleteClient(Client client) {
        return Main.jdbcc.deleteClient(client);
    }

    /**
     * deletes a labourer from the database via jdbccommands
     * @param lab the labourer to be deleted
     * @return true if no errors occur
     */
    public boolean deleteLabourer(Labourer lab) {
        return Main.jdbcc.deleteLabourer(lab);
    }

    /**
     * gets a labourer object from the database based on the name via jdbccommands
     *
     * @param name the name of the labourer to retrieve
     * @return the Labourer object
     */
    public Labourer getLabourer(String name) {
        return Main.jdbcc.getLabourer(name);
    }

    /**
     * persists a labourer to the database via jdbccommands
     * @param lab the labourer to be persisted
     * @return true if no errors occur
     */
    public boolean persistLabourer(Labourer lab) {
        return Main.jdbcc.persistLabourer(lab);
    }

    /**
     * Updates the labourer passed (new and old info), and updates changes in
     * the database
     *
     * @param labourerOld old instance of labourer object (pre change)
     * @param labourerNew new instance of labourer object (post change)
     * @return true if no errors occur
     */
    public boolean updateLabourer(Labourer labourerOld, Labourer labourerNew) {
        return Main.jdbcc.updateLabourer(labourerOld, labourerNew);
    }

    /**
     * gets all labourers in a list that can be parsed by jfxml via jdbccommands
     * @return the list of labourers found
     */
    public ObservableList<Labourer> getLabourersForTable() {
        return Main.jdbcc.getLabourersForTable(false);
    }

    /**
     * persists a project to the database via jdbccommands
     * @param proj the project object to be persisted
     * @return true if no errors occur
     */
    public boolean persistProject(Project proj){
        return Main.jdbcc.persistProject(proj);
    }
    
    /**
     * gets all projects in a list that can be parsed by jfxml via jdbccommands
     * @return the list of projects found
     */
    public ObservableList<Project> getActiveProjectsForTable(){
        return Main.jdbcc.getProjectsForTable(false,false);
    }
    
    /**
     * gets all projects in a list including logically deleted ones that can parsed by jfxml via jdbccommands
     * @return the list of projects found
     */
    public ObservableList<Project> getAllProjectsForTable(){
        return Main.jdbcc.getProjectsForTable(true,false);
    }
    
    /**
     * gets all projects in a list excluding logically deleted ones and one's that have not been completed
     * @return 
     */
    public ObservableList<Project> getCompletedProjectsForTable(){
        return Main.jdbcc.getProjectsForTable(false, true);
    }
    
    /**
     * deletes a project from the database via jdbccommands
     * @param proj the project to be deleted
     * @return true if no errors occur
     */
    public boolean deleteProject(Project proj){
        return Main.jdbcc.deleteProject(proj);
    }

    /**
     * restores the database from the chosen file path
     *
     * @param path the file path belonging to the backup
     * @return true if successful
     */
    public boolean restore(String path) {

        try {
            Runtime rt = Runtime.getRuntime();
            String d1 = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe";
            String d2 = "-uroot";
            String d3 = "-ppassword";
            String d4 = "DIRT";
            String d5 = "<";
            String d6 = path;
            Process proc = rt.exec(new String[]{"cmd.exe", "/C", d1, d2, d3, d4, d5, d6});
            int waitforme = proc.waitFor();

            return true;
        } catch (IOException ex) {
            Logger.getLogger(DBServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DBServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * backs up the database to the directory chosen by the user
     *
     * @param path the path of the directory chosen
     * @return true if successful
     */
    public boolean backup(String path) {
        try {
            Runtime rt = Runtime.getRuntime();
            String d1 = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump.exe";
            String d2 = "-uroot";
            String d3 = "-ppassword";
            String d4 = "DIRT";
            String d5 = ">";
            String d6 = path + "\\DIRTBackup.sql";

            Process proc = rt.exec(new String[]{"cmd.exe", "/C", d1, d2, d3, d4, d5, d6});
            int waitforme = proc.waitFor();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(DBServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(DBServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * the following methods are getters for the constants table using jdbccommands
     * @return the constant based on the method
     */
    public double excavation_TruckingFeeByHand() {
        return (double) Main.jdbcc.getConstant("excavation", "trucking by hand /2 yards").get(0);
    }

    /**
     *
     * @return
     */
    public double excavation_TruckingFeeBySkid() {
        return (double) Main.jdbcc.getConstant("excavation", "trucking by skid /2 yards").get(0);
    }
    
    /**
     *
     * @return
     */
    public double excavation_DisposalFee() {
        return (double) Main.jdbcc.getConstant("excavation", "disposal").get(0);
    }

    /**
     *
     * @return
     */
    public double excavation_ManHoursByHandPerYards() {
        return (double) Main.jdbcc.getConstant("excavation", "man hours by hand /yards").get(0);
    }

    /**
     *
     * @return
     */
    public double excavation_ManHoursBySkidPerYards() {
        return (double) Main.jdbcc.getConstant("excavation", "man hours by skid /yards").get(0);
    }

    /**
     *
     * @return
     */
    public double excavation_ManHoursByHandPerHours() {
        return (double) Main.jdbcc.getConstant("excavation", "labour cost by hand /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double excavation_ManHoursBySkidPerHours() {
        return (double) Main.jdbcc.getConstant("excavation", "labour cost by skid /hours").get(0);
    }
    
    /**
     *
     * @return
     */
    public double bed_ManHoursPerYard(){
        return (double) Main.jdbcc.getConstant("bed","hours /yards").get(0);
    }
    
    /**
     *
     * @return
     */
    public double bed_LabourPerHour(){
        return (double) Main.jdbcc.getConstant("bed","install /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double stonewalkway_EstimatedManHours() {
        return (double) Main.jdbcc.getConstant("stonewalkway", "estimated man hours").get(0);
    }

    /**
     *
     * @return
     */
    public double stonewalkway_InstallRatePerHours() {
        return (double) Main.jdbcc.getConstant("stonewalkway", "install rate /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double geotextilewalkway_WeedBarrierCostPerSQFT() {
        return (double) Main.jdbcc.getConstant("geotextilewalkway", "weed barrier cost /sq. ft").get(0);
    }

    /**
     *
     * @return
     */
    public double geotextilewalkway_FabricStaplesQTYPerOneFifthSQFT() {
        return (double) Main.jdbcc.getConstant("geotextilewalkway", "fabric staples qty /1/5 sq. ft").get(0);
    }

    /**
     *
     * @return
     */
    public double geotextilewalkway_FabricStaplesCostPerStaple() {
        return (double) Main.jdbcc.getConstant("geotextilewalkway", "fabric staples cost /staple").get(0);
    }

    /**
     *
     * @return
     */
    public double geotextilewalkway_FabricManHoursPer100SQFT() {
        return (double) Main.jdbcc.getConstant("geotextilewalkway", "fabric man hours /100 sq ft").get(0);
    }

    /**
     *
     * @return
     */
    public double geotextilewalkway_FabricInstallRatePerHours() {
        return (double) Main.jdbcc.getConstant("geotextilewalkway", "fabric install rate /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double walkwaybase_CrushedBaseSQFTPerInchPerYard() {
        return (double) Main.jdbcc.getConstant("walkwaybase", "crushed base sq. ft/inch/yard").get(0);
    }

    /**
     *
     * @return
     */
    public double walkwaybase_CrausedCostPerYard() {
        return (double) Main.jdbcc.getConstant("walkwaybase", "crasued cost /yard").get(0);
    }

    /**
     *
     * @return
     */
    public double walkwaybase_ManHoursPerYard() {
        return (double) Main.jdbcc.getConstant("walkwaybase", "man hours /yard").get(0);
    }

    /**
     *
     * @return
     */
    public double walkwaybase_InstallRatePerHours() {
        return (double) Main.jdbcc.getConstant("walkwaybase", "install rate / hours").get(0);
    }

    /**
     *
     * @return
     */
    public double screedsand_DepthSQFTPerYard() {
        return (double) Main.jdbcc.getConstant("screedsand", "depth sq.ft / yard").get(0);
    }

    /**
     *
     * @return
     */
    public double screedsand_CostPerYard() {
        return (double) Main.jdbcc.getConstant("screedsand", "cost /yard").get(0);
    }

    /**
     *
     * @return
     */
    public double screedsand_ManHoursPerYard() {
        return (double) Main.jdbcc.getConstant("screedsand", "man hours / yard").get(0);
    }

    /**
     *
     * @return
     */
    public double screedsand_InstallPerHour() {
        return (double) Main.jdbcc.getConstant("screedsand", "install /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double edgerestraint_CostPer8FT() {
        return (double) Main.jdbcc.getConstant("edgerestraint", "cost /8 ft").get(0);
    }
    
    /**
     *
     * @return
     */
    public double edgerestraint_CostPer1Nail(){
        return (double) Main.jdbcc.getConstant("edgerestraint","nails /1nail").get(0);
    }

    /**
     *
     * @return
     */
    public double edgerestraint_ManHours() {
        return (double) Main.jdbcc.getConstant("edgerestraint", "man hours").get(0);
    }

    /**
     *
     * @return
     */
    public double edgerestraint_InstallPerHours() {
        return (double) Main.jdbcc.getConstant("edgerestraint", "install /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double jointingsand_QTYkgPersfAtOneQuarterInch() {
        return (double) Main.jdbcc.getConstant("jointingsand", "QTY (kg/sf) @ 1/4 inch").get(0);
    }

    /**
     *
     * @return
     */
    public double jointingsand_CostPerKg() {
        return (double) Main.jdbcc.getConstant("jointingsand", "cost /kg").get(0);
    }

    /**
     *
     * @return
     */
    public double jointingsand_HoursPerKg() {
        return (double) Main.jdbcc.getConstant("jointingsand", "hours /kg").get(0);
    }

    /**
     *
     * @return
     */
    public double jointingsand_InstallPerHours() {
        return (double) Main.jdbcc.getConstant("jointingsand", "install /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_CrushedRockUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Crushed Rock").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_CrushedRockRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Crushed Rock").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_PeaRockUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Pea Rock").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_PeaRockRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Pea Rock").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_RiverRockUnit() {
        return (double) Main.jdbcc.getConstant("materials", "River Rock").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_RiverRockRetail() {
        return (double) Main.jdbcc.getConstant("materials", "River Rock").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_MulchWesternRedCedarUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Mulch: Western Red Cedar").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_MulchWesternRedCedarRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Mulch: Western Red Cedar").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_TopSoilPremiumMixUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Top Soil: Premium mix").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_TopSoilPremiumMixRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Top Soil: Premium mix").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_CrusherDustUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Crusher Dust").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_CrusherDustRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Crusher Dust").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_RedShaleUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Red Shale").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_RedShaleRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Red Shale").get(1);
    }

    /**
     *
     * @return
     */
    public double materials_SodPer10SQFTUnit() {
        return (double) Main.jdbcc.getConstant("materials", "Sod (per 10 s.f.)").get(0);
    }

    /**
     *
     * @return
     */
    public double materials_SodPer10SQFTRetail() {
        return (double) Main.jdbcc.getConstant("materials", "Sod (per 10 s.f.)").get(1);
    }

    /**
     *
     * @return
     */
    public double retainingwall_CrushedBaseCostPerYard() {
        return (double) Main.jdbcc.getConstant("retainingwall", "crushed base cost / yard").get(0);
    }

    /**
     *
     * @return
     */
    public double retainingwall_CrushedBaseInstallHoursPerYard() {
        return (double) Main.jdbcc.getConstant("retainingwall", "crushed base install hours / yard").get(0);
    }

    /**
     *
     * @return
     */
    public double retainingwall_CrushedBaseInstallRatePerHour() {
        return (double) Main.jdbcc.getConstant("retainingwall", "crushed base install rate / hour").get(0);
    }

    /**
     *
     * @return
     */
    public double retainingwall_BaseRowInstallHoursPerLineFeet() {
        return (double) Main.jdbcc.getConstant("retainingwall", "base row install hours /line feet").get(0);
    }

    /**
     *
     * @return
     */
    public double retainingwall_BaseRowInstallRatePerHour() {
        return (double) Main.jdbcc.getConstant("retainingwall", "base row install rate / hour").get(0);
    }

    /**
     *
     * @return
     */
    public double retainingwall_BlockCostPerLineFeet() {
        return (double) Main.jdbcc.getConstant("retainingwall", "block cost /line feet").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_3QuarterLiningMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "3/4 lining").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_3QuarterLiningLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "3/4 lining").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_HoseBibsMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "hose bibs").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_HoseBibsLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "hose bibs").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_ShutOffValveMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "shut off valve").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_ShutOffValveLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "shut off valve").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_RotaryHeadMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "rotary head").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_RotaryHeadLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "rotary head").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_SprayHeadMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "spray head").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_SprayHeadLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "spray head").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_Drip1QuarterInchPerFootMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "drip 1/4inch /foot").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_Drip1QuarterInchPerFootLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "drip 1/4inch /foot").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_DripEmitterMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "drip emmiter").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_DripEmitterLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "drip emmiter").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_TimerControlMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "timer control").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_TimerControlLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "timer control").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_ControlWirePer100FeetMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "control wire /100 feet").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_ControlWirePer100FeetLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "control wire /100 feet").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_ValveBoxMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "valve box").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_ValveBoxLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "valve box").get(1);
    }

    /**
     *
     * @return
     */
    public double irrigation_ControlValveMaterial() {
        return (double) Main.jdbcc.getConstant("irrigation", "control valve").get(0);
    }

    /**
     *
     * @return
     */
    public double irrigation_ControlValveLabour() {
        return (double) Main.jdbcc.getConstant("irrigation", "control valve").get(1);
    }

    /**
     *
     * @return
     */
    public double weedbarrier_ManHoursPer500SQFT() {
        return (double) Main.jdbcc.getConstant("weedbarrier", "man hours /500 sqft").get(0);
    }

    /**
     *
     * @return
     */
    public double weedbarrier_StaplesPer500SQFT() {
        return (double) Main.jdbcc.getConstant("weedbarrier", "staples /500 sqft").get(0);
    }

    /**
     *
     * @return
     */
    public double weedbarrier_CostPerStaples() {
        return (double) Main.jdbcc.getConstant("weedbarrier", "cost per staples").get(0);
    }

    /**
     *
     * @return
     */
    public double weedbarrier_BarrierSupplyPer500SQFT() {
        return (double) Main.jdbcc.getConstant("weedbarrier", "barrier supply /500 sqft").get(0);
    }

    /**
     *
     * @return
     */
    public double weedbarrier_InstallPer500SQFT(){
        return (double) Main.jdbcc.getConstant("weedbarrier", "barrier install /500 sqft").get(0);
    }

    /**
     *
     * @return
     */
    public double sod_SupplyPerYard() {
        return (double) Main.jdbcc.getConstant("sod", "supply /yard").get(0);
    }

    /**
     *
     * @return
     */
    public double sod_ManHoursPer10SQFT() {
        return (double) Main.jdbcc.getConstant("sod", "man hours /10 sqft").get(0);
    }

    /**
     *
     * @return
     */
    public double sod_InstallRatePerHours() {
        return (double) Main.jdbcc.getConstant("sod", "install rate /hours").get(0);
    }

    /**
     *
     * @return
     */
    public double snowremoval_MonthlyRate() {
        return (double) Main.jdbcc.getConstant("snowremoval", "monthly rate").get(0);
    }

    /**
     *
     * @return
     */
    public double snowremoval_AdditionalArea() {
        return (double) Main.jdbcc.getConstant("snowremoval", "additional area").get(0);
    }
    
    /**
     *
     * @return
     */
    public double tax_GST() {
        return (double) Main.jdbcc.getConstant("tax", "GST").get(0);
    }

    /**
     *
     * @return
     */
    public double tax_PST() {
        return (double) Main.jdbcc.getConstant("tax", "PST").get(0);
    }
    
   

    //Constant Setters
    /**
     * the following methods are setters for editing the constants in the constants table in mysql, this is done via a method in jdbccommands
     * @param constant the value to change the constant to
     * @return true if no errors occur
     */
    public boolean setexcavation_TruckingFeeByHand(double constant) {
        return Main.jdbcc.setConstant("excavation", "trucking by hand /2 yards", constant);
    }
    
    /**
     *
     * @param constant
     * @return
     */
    public boolean setexcavation_TruckingFeeBySkid(double constant) {
        return Main.jdbcc.setConstant("excavation", "trucking by skid /2 yards", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setexcavation_DisposalFee(double constant) {
        return Main.jdbcc.setConstant("excavation", "disposal", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setexcavation_ManHoursByHandPerYards(double constant) {
        return Main.jdbcc.setConstant("excavation", "man hours by hand /yards", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setexcavation_ManHoursBySkidPerYards(double constant) {
        return Main.jdbcc.setConstant("excavation", "man hours by skid /yards", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setexcavation_ManHoursByHandPerHours(double constant) {
        return Main.jdbcc.setConstant("excavation", "labour cost by hand /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setexcavation_ManHoursBySkidPerHours(double constant) {
        return Main.jdbcc.setConstant("excavation", "labour cost by skid /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setbed_HoursPerHours(double constant) {
        return Main.jdbcc.setConstant("bed", "hours /yards", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setbed_InstallPerYards(double constant) {
        return Main.jdbcc.setConstant("bed", "install /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setstonewalkway_EstimatedManHours(double constant) {
        return Main.jdbcc.setConstant("stonewalkway", "estimated man hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setstonewalkway_InstallRatePerHours(double constant) {
        return Main.jdbcc.setConstant("stonewalkway", "install rate /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setgeotextilewalkway_WeedBarrierCostPerSQFT(double constant) {
        return Main.jdbcc.setConstant("geotextilewalkway", "weed barrier cost /sq. ft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setgeotextilewalkway_FabricStaplesQTYPerOneFifthSQFT(double constant) {
        return Main.jdbcc.setConstant("geotextilewalkway", "fabric staples qty /1/5 sq. ft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setgeotextilewalkway_FabricStaplesCostPerStaple(double constant) {
        return Main.jdbcc.setConstant("geotextilewalkway", "fabric staples cost /staple", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setgeotextilewalkway_FabricManHoursPer100SQFT(double constant) {
        return Main.jdbcc.setConstant("geotextilewalkway", "fabric man hours /100 sq ft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setgeotextilewalkway_FabricInstallRatePerHours(double constant) {
        return Main.jdbcc.setConstant("geotextilewalkway", "fabric install rate /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setwalkwaybase_CrushedBaseSQFTPerInchPerYard(double constant) {
        return Main.jdbcc.setConstant("walkwaybase", "crushed base sq. ft/inch/yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setwalkwaybase_CrausedCostPerYard(double constant) {
        return Main.jdbcc.setConstant("walkwaybase", "crasued cost /yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setwalkwaybase_ManHoursPerYard(double constant) {
        return Main.jdbcc.setConstant("walkwaybase", "man hours /yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setwalkwaybase_InstallRatePerHours(double constant) {
        return Main.jdbcc.setConstant("walkwaybase", "install rate / hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setscreedsand_DepthSQFTPerYard(double constant) {
        return Main.jdbcc.setConstant("screedsand", "depth sq.ft / yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setscreedsand_CostPerYard(double constant) {
        return Main.jdbcc.setConstant("screedsand", "cost /yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setscreedsand_ManHoursPerYard(double constant) {
        return Main.jdbcc.setConstant("screedsand", "man hours / yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setscreedsand_InstallPerHour(double constant) {
        return Main.jdbcc.setConstant("screedsand", "install /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setedgerestraint_CostPer8FT(double constant) {
        return Main.jdbcc.setConstant("edgerestraint", "cost /8 ft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setedgerestraint_NailsPer1Nail(double constant) {
        return Main.jdbcc.setConstant("edgerestraint", "nails /1nail", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setedgerestraint_ManHours(double constant) {
        return Main.jdbcc.setConstant("edgerestraint", "man hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setedgerestraint_InstallPerHours(double constant) {
        return Main.jdbcc.setConstant("edgerestraint", "install /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setjointingsand_QTYkgPersfAtOneQuarterInch(double constant) {
        return Main.jdbcc.setConstant("jointingsand", "QTY (kg/sf) @ 1/4 inch", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setjointingsand_CostPerKg(double constant) {
        return Main.jdbcc.setConstant("jointingsand", "cost /kg", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setjointingsand_HoursPerKg(double constant) {
        return Main.jdbcc.setConstant("jointingsand", "hours /kg", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setjointingsand_InstallPerHours(double constant) {
        return Main.jdbcc.setConstant("jointingsand", "install /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_CrushedRockUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Crushed Rock", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_CrushedRockRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Crushed Rock", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_PeaRockUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Pea Rock", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_PeaRockRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Pea Rock", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_RiverRockUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "River Rock", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_RiverRockRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "River Rock", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_MulchWesternRedCedarUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Mulch: Western Red Cedar", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_MulchWesternRedCedarRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Mulch: Western Red Cedar", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_TopSoilPremiumMixUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Top Soil: Premium mix", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_TopSoilPremiumMixRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Top Soil: Premium mix", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_CrusherDustUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Crusher Dust", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_CrusherDustRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Crusher Dust", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_RedShaleUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Red Shale", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_RedShaleRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Red Shale", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_SodPer10SQFTUnit(double constant) {
        return Main.jdbcc.setConstant("materials", "Sod (per 10 s.f.)", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setmaterials_SodPer10SQFTRetail(double constant) {
        return Main.jdbcc.setConstant("materials", "Sod (per 10 s.f.)", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setretainingwall_CrushedBaseCostPerYard(double constant) {
        return Main.jdbcc.setConstant("retainingwall", "crushed base cost / yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setretainingwall_CrushedBaseInstallHoursPerYard(double constant) {
        return Main.jdbcc.setConstant("retainingwall", "crushed base install hours / yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setretainingwall_CrushedBaseInstallRatePerYard(double constant) {
        return Main.jdbcc.setConstant("retainingwall", "crushed base install rate / yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setretainingwall_BaseRowInstallHoursPerLineFeet(double constant) {
        return Main.jdbcc.setConstant("retainingwall", "base row install hours /line feet", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setretainingwall_BaseRowInstallRatePerHour(double constant) {
        return Main.jdbcc.setConstant("retainingwall", "base row install rate / hour", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setretainingwall_BlockCostPerLineFeet(double constant) {
        return Main.jdbcc.setConstant("retainingwall", "block cost /line feet", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_3QuarterLiningMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "3/4 lining", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_3QuarterLiningLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "3/4 lining", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_HoseBibsMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "hose bibs", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_HoseBibsLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "hose bibs", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ShutOffValveMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "shut off valve", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ShutOffValveLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "shut off valve", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_RotaryHeadMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "rotary head", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_RotaryHeadLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "rotary head", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_SprayHeadMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "spray head", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_SprayHeadLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "spray head", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_Drip1QuarterInchPerFootMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "drip 1/4inch /foot", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_Drip1QuarterInchPerFootLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "drip 1/4inch /foot", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_DripEmitterMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "drip emmiter", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_DripEmitterLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "drip emmiter", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_TimerControlMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "timer control", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_TimerControlLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "timer control", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ControlWirePer100FeetMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "control wire /100 feet", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ControlWirePer100FeetLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "control wire /100 feet", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ValveBoxMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "valve box", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ValveBoxLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "valve box", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ControlValveMaterial(double constant) {
        return Main.jdbcc.setConstant("irrigation", "control valve", "constantLow", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setirrigation_ControlValveLabour(double constant) {
        return Main.jdbcc.setConstant("irrigation", "control valve", "constantHigh", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setweedbarrier_ManHoursPer500SQFT(double constant) {
        return Main.jdbcc.setConstant("weedbarrier", "man hours /500 sqft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setweedbarrier_StaplesPer500SQFT(double constant) {
        return Main.jdbcc.setConstant("weedbarrier", "staples /500 sqft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setweedbarrier_CostPerStaples(double constant) {
        return Main.jdbcc.setConstant("weedbarrier", "cost per staples", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setweedbarrier_BarrierSupplyPer500SQFT(double constant) {
        return Main.jdbcc.setConstant("weedbarrier", "barrier supply /500 sqft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setsod_SupplyPerYard(double constant) {
        return Main.jdbcc.setConstant("sod", "supply /yard", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setsod_ManHoursPer10SQFT(double constant) {
        return Main.jdbcc.setConstant("sod", "man hours /10 sqft", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setsod_InstallRatePerHours(double constant) {
        return Main.jdbcc.setConstant("sod", "install rate /hours", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setsnowremoval_MonthlyRate(double constant) {
        return Main.jdbcc.setConstant("snowremoval", "monthly rate", constant);
    }

    /**
     *
     * @param constant
     * @return
     */
    public boolean setsnowremoval_AdditionalArea(double constant) {
        return Main.jdbcc.setConstant("snowremoval", "additional area", constant);
    }

}
