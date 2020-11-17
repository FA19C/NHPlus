package controller;

import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import datastorage.DAOFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.util.Callback;
import utils.PDFWriter;


/**
 * The <code>AllPatientController</code> contains the entire logic of the patient view. It determines which data is displayed and how to react to events.
 */
public class AllPatientController {
    @FXML
    private TableView<Patient> tableView;
    @FXML
    private TableColumn<Patient, Integer> colID;
    @FXML
    private TableColumn<Patient, String> colFirstName;
    @FXML
    private TableColumn<Patient, String> colSurname;
    @FXML
    private TableColumn<Patient, String> colDateOfBirth;
    @FXML
    private TableColumn<Patient, String> colCareLevel;
    @FXML
    private TableColumn<Patient, String> colRoom;
    @FXML
    private TableColumn<Patient, Boolean> colLocked;

    @FXML
    Button btnExport;
    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtBirthday;
    @FXML
    TextField txtCarelevel;
    @FXML
    TextField txtRoom;

    private ObservableList<Patient> tableviewContent = FXCollections.observableArrayList();
    private PatientDAO dao;
    private static final int mindestLoeschAlter = 10;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();
        //CellFactory, die den Lockedstatus beachtet um Zellen uneditierbar zu machen
        Callback<TableColumn<Patient, String>, TableCell<Patient, String>> cellFactoryNoticingLockedState = col -> {
            TableCell<Patient, String> cell = TextFieldTableCell.<Patient>forTableColumn().call(col);
            cell.itemProperty().addListener((obs, oldValue, newValue) -> {
                TableRow row = cell.getTableRow();
                if (row == null) {
                    cell.setEditable(false);
                } else {
                    Patient item = (Patient) cell.getTableRow().getItem();
                    if (item == null) {
                        cell.setEditable(false);
                    } else {
                        cell.setEditable(!item.getLocked());
                    }
                }
            });
            return cell ;
        };

        this.colID.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("pid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle die nun auch Locked beachtet
        this.colFirstName.setCellFactory(cellFactoryNoticingLockedState);

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        this.colSurname.setCellFactory(cellFactoryNoticingLockedState);

        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Patient, String>("dateOfBirth"));
        this.colDateOfBirth.setCellFactory(cellFactoryNoticingLockedState);

        this.colCareLevel.setCellValueFactory(new PropertyValueFactory<Patient, String>("careLevel"));
        this.colCareLevel.setCellFactory(cellFactoryNoticingLockedState);

        this.colRoom.setCellValueFactory(new PropertyValueFactory<Patient, String>("roomnumber"));
        this.colRoom.setCellFactory(cellFactoryNoticingLockedState);

        this.colLocked.setCellValueFactory(new PropertyValueFactory<Patient, Boolean>("locked"));
        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
    }

    /**
     * handles new firstname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Patient, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new surname value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Patient, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new birthdate value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditDateOfBirth(TableColumn.CellEditEvent<Patient, String> event){
        event.getRowValue().setDateOfBirth(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new carelevel value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditCareLevel(TableColumn.CellEditEvent<Patient, String> event){
        event.getRowValue().setCareLevel(event.getNewValue());
        doUpdate(event);
    }

    /**
     * handles new roomnumber value
     * @param event event including the value that a user entered into the cell
     */
    @FXML
    public void handleOnEditRoomnumber(TableColumn.CellEditEvent<Patient, String> event)
    {
            event.getRowValue().setRoomnumber(event.getNewValue());
            doUpdate(event);
    }



    /**
     * updates a patient by calling the update-Method in the {@link PatientDAO}
     * @param t row to be updated by the user (includes the patient)
     */
    private void doUpdate(TableColumn.CellEditEvent<Patient, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * calls readAll in {@link PatientDAO} and shows patients in the table
     */
    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createPatientDAO();
        List<Patient> allPatients;
        try {
            allPatients = dao.readAll();
            for (Patient p : allPatients) {
                if(p.getLocked() == true)
                {
                    p.setCareLevel("Gesperrt");
                    p.setDateOfBirth("0000-01-01");
                    p.setRoomnumber("Gesperrt");
                }
                this.tableviewContent.add(p);
                this.tableView.refresh();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a delete-click-event. Calls the delete methods in the {@link PatientDAO} and {@link TreatmentDAO}
     */
    @FXML
    public void handleDeleteRow() {
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        boolean darfGeloeschtWerden = DarfPatientGeloeschtWerden(selectedItem.getPid());

        if(darfGeloeschtWerden)
        {
            this.tableView.getItems().remove(selectedItem);
            try {
                tDao.deleteByPid((int) selectedItem.getPid());
                dao.deleteById((int) selectedItem.getPid());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else
        {
            if(selectedItem.getLocked())
            {
                utils.PopUpHelper.OpenPopUp("Patient darf noch nicht gelöscht werden");
            }
            else
            {
                handleLock();
            }

        }
    }

    /**
     * Prueft ob Patient geloescht werden darf nach pid
     * @param pid der Patient in Frage
     * @return true wenn er geloescht werden darf sonst false
     */
    public static boolean DarfPatientGeloeschtWerden(long pid)
    {
        boolean darfGeloeschtWerden = true;
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            Treatment treatment = tDao.readNewestTreatmentByPid(pid);

            if(treatment != null)
            {
                String date = treatment.getDate();
                LocalDate newestDate = DateConverter.convertStringToLocalDate(date);
                LocalDate deleteDate = LocalDate.now().minusYears(mindestLoeschAlter);

                if(newestDate.isAfter(deleteDate))
                {
                    darfGeloeschtWerden = false;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return darfGeloeschtWerden;
    }

    /**
     * handles export-click-event
     */
    @FXML
    public void handleExport()
    {
        Stage chooserStage = new Stage();
        FileChooser chooser = new FileChooser();
        TreatmentDAO tDao = DAOFactory.getDAOFactory().createTreatmentDAO();
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();

        if(selectedItem.getLocked())
        {
            utils.PopUpHelper.OpenPopUp("Patient ist gesperrt");
            return;
        }

        chooser.setInitialFileName(selectedItem.getSurname() + ".pdf");
        File file = chooser.showSaveDialog(chooserStage);
        try
        {
            List<Treatment> treatments = tDao.readTreatmentsByPidLockedSensitiv(selectedItem.getPid());
            PDFWriter.writePatientData(file, selectedItem, treatments);
            if(file.exists())
            {
                try
                {
                    Desktop.getDesktop().open(file);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException throwables)
        {

            throwables.printStackTrace();
        }
    }

    /**
     * handles a add-click-event. Creates a patient and calls the create method in the {@link PatientDAO}
     */
    @FXML
    public void handleAdd() {
        String surname = this.txtSurname.getText();
        String firstname = this.txtFirstname.getText();
        String birthday = this.txtBirthday.getText();
        LocalDate date = DateConverter.convertStringToLocalDate(birthday);
        String carelevel = this.txtCarelevel.getText();
        String room = this.txtRoom.getText();
        try {
            Patient p = new Patient(firstname, surname, date, carelevel, room, false);
            dao.create(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * entsperrt den aktiven Patienten
     */
    @FXML
    public void handleUnlock()
    {
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        selectedItem.setLocked(false);
        try {

            dao.changeLockedStatusByPId(selectedItem.getPid(), selectedItem.getLocked());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
    }

    /**
     * sperrt den aktiven Patienten
     */
    @FXML
    public void handleLock()
    {
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        selectedItem.setLocked(true);
        try {
            dao.changeLockedStatusByPId(selectedItem.getPid(), selectedItem.getLocked());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
    }
    /**
     * removes content from all textfields
     */
    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtBirthday.clear();
        this.txtCarelevel.clear();
        this.txtRoom.clear();
    }
}