package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ClientProfileGUIController implements Initializable {

    //FXML elements
    @FXML
    private Label nameFill;

    @FXML
    private TextArea notesFillArea;

    @FXML
    private TableView completed;

    @FXML
    private TableView ongoing;

    @FXML
    private Text phone1Fill;

    @FXML
    private Text phone2Fill;

    @FXML
    private Text emailFill;

    @FXML
    private Text addressFill;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public Label getNameFill() {
        return nameFill;
    }

    public void setNameFill(String value) {
        this.nameFill.setText(value);
    }

    public TextArea getNotesFillArea() {
        return notesFillArea;
    }

    public void setNotesFillArea(String value) {
        this.notesFillArea.setText(value);
    }

    //public TableView getCompleted() {
    //    return completed;
    //}

    //public void setCompleted(String value) {
      //  this.completed.setText(value);
    //}

    //public TableView getOngoing() {
      //  return ongoing;
    //}

    //public void setOngoing(TableView ongoing) {
      //  this.ongoing = ongoing;
    //}

    public Text getPhone1Fill() {
        return phone1Fill;
    }

    public void setPhone1Fill(String value) {
        this.phone1Fill.setText(value);
    }

    public Text getPhone2Fill() {
        return phone2Fill;
    }

    public void setPhone2Fill(String value) {
        this.phone2Fill.setText(value);
    }

    public Text getEmailFill() {
        return emailFill;
    }

    public void setEmailFill(String value) {
        this.emailFill.setText(value);
    }

    public Text getAddressFill() {
        return addressFill;
    }

    public void setAddressFill(String value) {
        this.addressFill.setText(value);
    }
}
