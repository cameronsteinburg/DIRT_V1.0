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

    public ObservableList<Client> getClientsForTable() {
        return Main.jdbcc.getClientsForTable(false);
    }
    
    /**
     * Logically deletes a client from the database
     * @param client the client to be logically deleted
     * @return true if no errors occur
     */
    public boolean deleteClient(Client client){
        return Main.jdbcc.deleteClient(client);
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
}
