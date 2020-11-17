package controller;

import datastorage.NurseDAO;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Nurse;
import model.Patient;
import utils.DateConverter;
import datastorage.DAOFactory;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


/**
 * The <code>AllPatientController</code> contains the entire logic of the patient view. It determines which data is displayed and how to react to events.
 */
public class AllNurseViewController {
    @FXML
    private TableView<Nurse> tableView;
    @FXML
    private TableColumn<Nurse, Integer> colID;
    @FXML
    private TableColumn<Nurse, String> colFirstName;
    @FXML
    private TableColumn<Nurse, String> colSurname;
    @FXML
    private TableColumn<Nurse, String> colHandynumber;


    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtHandynumber;


    private ObservableList<Nurse> tableviewContent = FXCollections.observableArrayList();
    private NurseDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Nurse, Integer>("nid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Nurse, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Nurse, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colHandynumber.setCellValueFactory(new PropertyValueFactory<Nurse, String>("phonenumber"));
        this.colHandynumber.setCellFactory(TextFieldTableCell.forTableColumn());

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Nurse, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Nurse, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }


    /**
     * handles new phonenumber value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditPhonenumber(TableColumn.CellEditEvent<Nurse, String> event){
        event.getRowValue().setPhonenumber(event.getNewValue());
        doUpdate(event);
    }

    /**
     * updates a nurse by calling the update-Method in the
     * @param t row to be updated by the user (includes the nurse)
     */
    private void doUpdate(TableColumn.CellEditEvent<Nurse, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**

     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createNurseDAD();
        List<Nurse> allNurses;
        try {
            allNurses = dao.readAll();
            for (Nurse n : allNurses) {
                this.tableviewContent.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link NurseDAO} and {@link TreatmentDAO}
     */
    @FXML
    public void handleDeleteRow() {
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
       Nurse selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        this.tableView.getItems().remove(selectedItem);
        try {
            tDao.deleteByNid((int) selectedItem.getNid());
            dao.deleteById((int) selectedItem.getNid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a Nurse and calls the create method in the {@link NurseDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txtSurname.getText();
        String firstname = this.txtFirstname.getText();
        String phonenumber = this.txtHandynumber.getText();
        try {
            Nurse n = new Nurse(firstname, surname, phonenumber);
            dao.create(n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtHandynumber.clear();
    }
}