package services;

import application.Main;
import entity.Client;
import entity.Labourer;
import entity.Project;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * Methods that have direct access and control to the databases
 */
public class DBServices {

    /**
     * Sends a Client Object to be saved to the database
     *
     * @param obj Object to be saved
     * @return true if successful, false otherwise
     */
    public boolean persistObject(Object obj) {
        return false;
    }

    /**
     * gets a client object from the database via jdbccommands based on name
     *
     * @param name the name of the client to retrieve
     * @return the Client object
     */
    public Client getClient(String name) {
        return Main.jdbcc.getClient(name);
    }

    /**
     * gets all the client objects from the database via jdbccommands
     *
     * @return a Client arraylist object
     */
    public ArrayList<Client> getClients(boolean getDeleted) {
        return Main.jdbcc.getClients(getDeleted);
    }

    /**
     * 
     * @return 
     */
    public ObservableList<Client> getClientsForTable() {
        return Main.jdbcc.getClientsForTable(false);
    }

    /**
     * 
     * @param client 
     */
    public void persistClient(Client client){
        Main.jdbcc.persistClient(client);
    }
    
    /**
     * 
     * @param clientOld
     * @param clientNew 
     */
    public void updateClient(Client clientOld, Client clientNew){
        Main.jdbcc.updateClient(clientOld, clientNew);
    }
    /**
     * Logically deletes a client from the database
     *
     * @param client the client to be logically deleted
     * @return true if no errors occur
     */
    public boolean deleteClient(Client client) {
        return Main.jdbcc.deleteClient(client);
    }

    /**
     * 
     * @param lab
     * @return 
     */
    public boolean deleteLabourer(Labourer lab) {
        return Main.jdbcc.deleteLabourer(lab);
    }

    /**
     * gets a labourer object from the database via jdbccommands based on name
     *
     * @param name the name of the labourer to retrieve
     * @return the Labourer object
     */
    public Labourer getLabourer(String name) {
        return Main.jdbcc.getLabourer(name);
    }
    
    public boolean persistLabourer(Labourer lab){
         return Main.jdbcc.persistLabourer(lab);
    }
    
    /**
     * Updates the labourer passed (new and old info), and updates changes in the database
     * @param labourerOld old instance of labourer object (pre change)
     * @param labourerNew new instance of labourer object (post change)
     * @return true if no errors occur
     */
    public boolean updateLabourer(Labourer labourerOld, Labourer labourerNew){
        return Main.jdbcc.updateLabourer(labourerOld, labourerNew);
    }

    /**
     * 
     * @return 
     */
    public ObservableList<Labourer> getLabourersForTable() {
        return Main.jdbcc.getLabourersForTable(false);
    }

    /**
     * Calls the Projects export method
     *
     * @param project Project Object to export CSV
     * @return true is successful, false otherwise
     */
    public boolean export(Project project) {
        return false;
    }

    /**
     * Request backup from DB, calls overloaded backup method
     *
     * @return true if successful, false otherwise
     */
    public boolean backup() {
        return false;
    }
    
    public double excavation_Trucking(){
        return (int) Main.jdbcc.getConstant("excavation", "trucking /2 yards").get(0);
    }
    
    public double excavation_Disposal(){
        return (int) Main.jdbcc.getConstant("excavation","disposal").get(0); 
    }
    
    public double excavation_ManHoursByHandPerYards(){
        return (int) Main.jdbcc.getConstant("excavation","man hours by hand /yards").get(0);
    }
    
    public double excavation_ManHoursBySkidPerYards(){
        return (int) Main.jdbcc.getConstant("excavation","man hours by skid /yards").get(0); 
    }
    
    public double excavation_ManHoursByHandPerHours(){
        return (int) Main.jdbcc.getConstant("excavation","labour cost by hand /hours").get(0);
    }
    
    public double excavation_ManHoursBySkidPerHours(){
        return (int) Main.jdbcc.getConstant("excavation","labour cost by skid /hours").get(0);
    }
    
    public double bed_HoursPerHours(){
        return (int) Main.jdbcc.getConstant("bed","hours /yards").get(0);
    }
    
    public double bed_InstallPerYards(){
        return (int) Main.jdbcc.getConstant("bed","install /hours").get(0);
    }
    
    public double stonewalkway_EstimatedManHours(){
        return (int) Main.jdbcc.getConstant("stonewalkway","estimated man hours").get(0);
    }
    
    public double stonewalkway_InstallRatePerHours(){
        return (int) Main.jdbcc.getConstant("stonewalkway","install rate /hours").get(0);
    }
    
    public double geotextilewalkway_WeedBarrierCostPerSQFT(){
        return (int) Main.jdbcc.getConstant("geotextilewalkway","weed barrier cost /sq. ft").get(0);
    }
    
    public double geotextilewalkway_FabricStaplesQTYPerOneFifthSQFT(){
        return (int) Main.jdbcc.getConstant("geotextilewalkway","fabric staples qty /1/5 sq. ft").get(0);
    }
    
    public double geotextilewalkway_FabricStaplesCostPerStaple(){
        return (int) Main.jdbcc.getConstant("geotextilewalkway","fabric staples cost /staple").get(0);
    }
    
    public double geotextilewalkway_FabricManHoursPer100SQFT(){
        return (int) Main.jdbcc.getConstant("geotextilewalkway","fabric man hours /100 sq ft").get(0);
    }
    
    public double geotextilewalkway_FabricInstallRatePerHours(){
        return (int) Main.jdbcc.getConstant("geotextilewalkway","fabric install rate /hours").get(0);
    }
    
    public double walkwaybase_CrushedBaseSQFTPerInchPerYard(){
        return (int) Main.jdbcc.getConstant("walkwaybase","crushed base sq. ft/inch/yard").get(0);
    }
    
    public double walkwaybase_CrausedCostPerYard(){
        return (int) Main.jdbcc.getConstant("walkwaybase","crasued cost /yard").get(0);
    }
    
    public double walkwaybase_ManHoursPerYard(){
        return (int) Main.jdbcc.getConstant("walkwaybase","man hours /yard").get(0);
    }
    
    public double walkwaybase_InstallRatePerHours(){
        return (int) Main.jdbcc.getConstant("walkwaybase","install rate / hours").get(0);
    }
    
    public double screedsand_DepthSQFTPerYard(){
        return (int) Main.jdbcc.getConstant("screedsand","depth sq.ft / yard").get(0);
    }
    
    public double screedsand_CostPerYard(){
        return (int) Main.jdbcc.getConstant("screedsand","cost /yard").get(0);
    }
    
    public double screedsand_ManHoursPerYard(){
        return (int) Main.jdbcc.getConstant("screedsand","man hours / yard").get(0);
    }
    
    public double screedsand_InstallPerHour(){
        return (int) Main.jdbcc.getConstant("screedsand","install /hours").get(0);
    }
    
    public double edgerestraint_CostPer8FT(){
        return (int) Main.jdbcc.getConstant("edgerestraint","cost /8 ft").get(0);
    }
    
    public double edgerestraint_NailsPer1Nail(){
        return (int) Main.jdbcc.getConstant("edgerestraint","nails /1nail").get(0);
    }
    
    public double edgerestraint_ManHours(){
        return (int) Main.jdbcc.getConstant("edgerestraint","man hours").get(0);
    }
    
    public double edgerestraint_InstallPerHours(){
        return (int) Main.jdbcc.getConstant("edgerestraint","install /hours").get(0);
    }
    
    public double jointingsand_QTYkgPersfAtOneQuarterInch(){
        return (int) Main.jdbcc.getConstant("jointingsand","QTY (kg/sf) @ 1/4 inch").get(0);
    }
    
    public double jointingsand_CostPerKg(){
        return (int) Main.jdbcc.getConstant("jointingsand","cost /kg").get(0);
    }
    
    public double jointingsand_HoursPerKg(){
        return (int) Main.jdbcc.getConstant("jointingsand","hours /kg").get(0);
    }
    
    public double jointingsand_InstallPerHours(){
        return (int) Main.jdbcc.getConstant("jointingsand","install /hours").get(0);
    }
    
    public double materials_CrushedRock(){
        return (int) Main.jdbcc.getConstant("materials","Crushed Rock").get(0);
    }
    
    public double materials_PeaRock(){
        return (int) Main.jdbcc.getConstant("materials","Pea Rock").get(0);
    }
    
    public double materials_RiverRock(){
        return (int) Main.jdbcc.getConstant("materials","River Rock").get(0);
    }
    
    public double materials_MulchWesternRedCedar(){
        return (int) Main.jdbcc.getConstant("materials","Mulch: Western Red Cedar").get(0);
    }
    
    public double materials_TopSoilPremiumMix(){
        return (int) Main.jdbcc.getConstant("materials","Top Soil: Premium mix").get(0);
    }
    
    public double materials_CrusherDust(){
        return (int) Main.jdbcc.getConstant("materials","Crusher Dust").get(0);
    }
    
    public double materials_RedShale(){
        return (int) Main.jdbcc.getConstant("materials","Red Shale").get(0);
    }
    
    public double materials_SodPer10SQFT(){
        return (int) Main.jdbcc.getConstant("materials","Sod (per 10 s.f.)").get(0);
    }
    
}