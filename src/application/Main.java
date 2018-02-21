/*
 *  This is where the app begins, main calls start and instantiates the single global stage variable for all the control files to interact with
 */
package application;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.DBAccessor;
import persistence.JDBCCommands;

/**
 *
 * @author Kevin Brown 645011, Cameron Steinburg 734972, Matthew MacDonald 728918, Kyle Hendrickson 628344 
 */
public class Main extends Application {

    public static Stage stage;//singleton stage object i.e the apps window definied by fxml files controlled by controller files
    public static JDBCCommands jdbcc;
    //  ArrayList<FXMLLoader> ugh; tentative

    @Override
    public void start(Stage stage) throws Exception { //gets this show on the road
        // FXMLLoader loaderr = new FXMLLoader();

        DBAccessor dba = new DBAccessor();
        dba.connectToMySQL();
        this.jdbcc = new JDBCCommands(dba);

        Parent root = FXMLLoader.load(getClass().getResource("/ui/HomePageGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        this.stage = stage;
        stage.show();
        stage.setTitle("DIRT - Dynamic Interface Regarding Terrain");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
