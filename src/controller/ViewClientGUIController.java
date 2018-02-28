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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> addressCol;
    @FXML
    private TableColumn<?, ?> numberCol;

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
        numberCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
        DBServices dbs = new DBServices();
        this.clientList = dbs.getClientsForTable();
        clientTable.setItems(clientList);

    }

    @FXML
    private void getSelectedClient(MouseEvent event) {
        if (clientTable.getSelectionModel().getSelectedItem() != null) {
            Client client = clientTable.getSelectionModel().getSelectedItem();
            System.out.println(client.getName());
        }
    }
}