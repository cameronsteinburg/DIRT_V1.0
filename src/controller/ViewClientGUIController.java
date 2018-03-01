package controller;

import entity.Client;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.DBServices;

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

    @FXML
    private void editClientAction(ActionEvent event) {

    }

    /**
     *
     * @param event
     */
    @FXML
    private void removeClientAction(ActionEvent event) {

        if (this.selectedClient != null) {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Client Confirmation");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Delete client with the name: " + selectedClient.getName() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                DBServices dbs = new DBServices();

                Client target = dbs.getClient(this.selectedClient.getName());
                String name = target.getName();
                dbs.deleteClient(target);
                this.updateTable();
                HomePageGUIController.setBannerMessage("Client " + name + " Successfully Removed");
            } else {
                alert.close();
            }
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void getSelectedClient(MouseEvent event) {

        if (clientTable.getSelectionModel().getSelectedItem() != null) {

            this.selectedClient = clientTable.getSelectionModel().getSelectedItem();

            //Enable buttons once client is selected
            viewClientBtn.setDisable(false);
            editClientBtn.setDisable(false);
            removeClientBtn.setDisable(false);
        }
    }

    private void updateTable() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        firstNumCol.setCellValueFactory(new PropertyValueFactory<>("phone1"));
        secondNumCol.setCellValueFactory(new PropertyValueFactory<>("phone2"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        DBServices dbs = new DBServices();
        this.clientList = dbs.getClientsForTable();
        clientTable.setItems(clientList);
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
