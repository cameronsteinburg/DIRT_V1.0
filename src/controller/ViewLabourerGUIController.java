package controller;

import entity.Labourer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.DBServices;

public class ViewLabourerGUIController implements Initializable {

    private ObservableList<Labourer> labourerList;
    private Labourer selectedLabourer;

    @FXML
    private TableView<Labourer> labourerTable;
    @FXML
    private TextField searchBox;
    @FXML
    private Button viewLabourerBtn;
    @FXML
    private Button editLabourerBtn;
    @FXML
    private Button removeLabourerBtn;
    @FXML
    private TableColumn<?, ?> fNameCol;
    @FXML
    private TableColumn<?, ?> lNameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> firstNumCol;
    @FXML
    private TableColumn<?, ?> secondNumCol;
    @FXML
    private TableColumn<?, ?> emailCol;
    
    
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void removeLabourerAction(ActionEvent event){
        
        if (this.selectedLabourer != null){
        
            DBServices dbs = new DBServices();
            
            Labourer target = dbs.getLabourer(this.selectedLabourer.getFirstName());
//            String name = target.getName();
//            dbs.deleteClient(target);
//            this.updateTable();
//            HomePageGUIController.setBannerMessage("Client " + name + " Successfully Removed");
        }
    }
    
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void getSelectedLabourer(MouseEvent event) {
        
        if (labourerTable.getSelectionModel().getSelectedItem() != null) {
            
            this.selectedLabourer = labourerTable.getSelectionModel().getSelectedItem();
            
            //Enable buttons once client is selected
            viewLabourerBtn.setDisable(false);
            editLabourerBtn.setDisable(false);
            removeLabourerBtn.setDisable(false);
            
            System.out.println(selectedLabourer.getPhone2());
        }
    }
    
    private void updateTable(){
        
        fNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        firstNumCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
        secondNumCol.setCellValueFactory(new PropertyValueFactory<>("phone2"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        DBServices dbs = new DBServices();
        this.labourerList = dbs.getLabourersForTable();
        labourerTable.setItems(labourerList);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.updateTable();
    }

    
}
