package controller;

import datastorage.DAOFactory;
import datastorage.NurseDAO;
import datastorage.PatientDAO;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Nurse;

import java.sql.SQLException;
import java.util.List;

/**
 * Controller class for the AllCaregiver View
 */
public class AllCaregiverController {

    private Nurse currentSelection;


    @FXML
    private TableView<Nurse> tableView;
    @FXML
    private TableColumn<Nurse, Integer> colID;
    @FXML
    private TableColumn<Nurse, String> colFirstName;
    @FXML
    private TableColumn<Nurse, String> colSurname;
    @FXML
    private TableColumn<Nurse, String> colTelephone;
    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtTelephone;


    private ObservableList<Nurse> tableviewContent = FXCollections.observableArrayList();
    private NurseDAO dao;



    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("pid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Nurse, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Nurse, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTelephone.setCellValueFactory(new PropertyValueFactory<Nurse, String>("telephoneNumber"));
        this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(this::onSelectionChanged);

    }

    /**
     * calls readAll in {@link PatientDAO} and shows patients in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createNurseDAO();
        List<Nurse> allNurses;
        try {
            allNurses = dao.readAll();
            for (Nurse p : allNurses) {
                this.tableviewContent.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Event Method that gets called when the current row selections has changed
     * @param obs the ObservableValue
     * @param oldValue the old value
     * @param newValue the new Value
     */
    private void onSelectionChanged(ObservableValue<? extends Nurse> obs, Nurse oldValue, Nurse newValue){
        if (newValue != null){
            currentSelection = newValue;
        }
    }
}
