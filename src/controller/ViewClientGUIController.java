/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.DBServices;

/**
 * FXML Controller class
 *
 * @author 645011
 */
public class ViewClientGUIController implements Initializable {

    private ObservableList<Client> clientList;
    private Client selectedClient;

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TextField searchBox;
    @FXML
    private Button viewClientBtn;
    @FXML
    private Button editClientBtn;
    @FXML
    private Button removeClientBtn;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> firstNumCol;
    @FXML
    private TableColumn<?, ?> secondNumCol;
    @FXML
    private TableColumn<?, ?> emailCol;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        firstNumCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
        secondNumCol.setCellValueFactory(new PropertyValueFactory<>("phone2"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        DBServices dbs = new DBServices();
        this.clientList = dbs.getClientsForTable();
        clientTable.setItems(clientList);

    }

    @FXML
    private void getSelectedClient(MouseEvent event) {
        if (clientTable.getSelectionModel().getSelectedItem() != null) {
            selectedClient = clientTable.getSelectionModel().getSelectedItem();
            
            //Enable buttons once client is selected
            viewClientBtn.setDisable(false);
            editClientBtn.setDisable(false);
            removeClientBtn.setDisable(false);
        }
    }
}
