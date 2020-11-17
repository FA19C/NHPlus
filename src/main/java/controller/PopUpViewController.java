package controller;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import datastorage.DAOFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.util.Callback;

/**
 * eine Klasse die ein einfaches Popup bereitstellt
 */
public class PopUpViewController
{
    @FXML
    private Label lblText;

    @FXML
    private Button btnOk;

    private Stage stage;

    public void initialize(String text, Stage stage)
    {
        lblText.setText(text);
        this.stage = stage;
    }

    @FXML
    public void onHandleOk()
    {
        this.stage.close();
    }
}
