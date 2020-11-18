package controller;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.OutputStream;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lib.OSDetector;
import model.Patient;
import utils.DateConverter;
import datastorage.DAOFactory;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


/**
 * The <code>AllPatientController</code> contains the entire logic of the patient view. It determines which data is displayed and how to react to events.
 */
public class AllPatientController {

    private Patient currentSelection;

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
    @FXML
    Button btnLock;
    @FXML
    Button btnPDFExport;

    private ObservableList<Patient> tableviewContent = FXCollections.observableArrayList();
    private PatientDAO dao;

    /**
     * Initializes the corresponding fields. Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        readAllAndShowInTableView();

        this.colID.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("pid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstName.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Patient, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colDateOfBirth.setCellValueFactory(new PropertyValueFactory<Patient, String>("dateOfBirth"));
        this.colDateOfBirth.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colCareLevel.setCellValueFactory(new PropertyValueFactory<Patient, String>("careLevel"));
        this.colCareLevel.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colRoom.setCellValueFactory(new PropertyValueFactory<Patient, String>("roomnumber"));
        this.colRoom.setCellFactory(TextFieldTableCell.forTableColumn());

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);
        this.tableView.getSelectionModel().selectedItemProperty().addListener(this::onSelectionChanged);

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
    public void handleOnEditRoomnumber(TableColumn.CellEditEvent<Patient, String> event){
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
                if (!p.isLocked()){
                    this.tableviewContent.add(p);
                }
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
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        this.tableView.getItems().remove(selectedItem);
        try {
            selectedItem.lockPatient();
            dao.update(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.handleAdd();
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
            Patient p = new Patient(firstname, surname, date, carelevel, room);
            dao.create(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    /**
     * Mehthod for handling the button press for the PDF export btn
     */
    @FXML
    private void handleBtnPDFExport(){
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            convertToPDFAndOpen(selectedItem);
        }
        catch (Exception e){}
    }

    /**
     * Method for creating and opening a PDF made from Patient data
     * @param selectedItem the Patient data to convert
     * @throws URISyntaxException
     * @throws IOException
     */
    private void convertToPDFAndOpen(Patient selectedItem)  throws URISyntaxException, IOException {
        URL pathURl = Thread.currentThread().getContextClassLoader().getResource("Templates/PDFTemplate.html");
        Path path = Paths.get(pathURl.toURI());
        String html = Files.readString(path, StandardCharsets.UTF_8);
        html = String.format(html,
                "Patient: "+ selectedItem.getPid(),
                selectedItem.getPid(),
                selectedItem.getFirstName()+ " " + selectedItem.getSurname(),
                selectedItem.getDateOfBirth(),
                selectedItem.getCareLevel(),
                selectedItem.getRoomnumber());
        File pdf = new File("P"+selectedItem.getPid()+".pdf");
        pdf.createNewFile();
        HtmlConverter.convertToPdf(html, new FileOutputStream(pdf));
        openFile(pdf);
    }

    /**
     * Opens a File
     * @param file the File that should be opened
     * @throws IOException
     */
    private void openFile(File file) throws IOException {
        String path = file.getAbsolutePath();
        if (OSDetector.isWindows())
        {
            Runtime.getRuntime().exec(new String[]
                    {"rundll32", "url.dll,FileProtocolHandler",
                            path});

        } else if (OSDetector.isLinux() || OSDetector.isMac())
        {
            Runtime.getRuntime().exec(new String[]{"/usr/bin/opener",
                    path});

        } else
        {
            // Unknown OS, try with desktop
            if (Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().open(file);

            }
        }
    }

    @FXML
    private void handleBtnLock(){
        Patient selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        setLockState(selectedItem, true);
    }

    /**
     * Mehtod for setting the current lockstate of a Patient
     * @param p the Patient
     * @param lockState the new lockstate
     */
    private void setLockState(Patient p, boolean lockState){
        p.setLockState(lockState);
        try {
            dao.update(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.onSelectionChanged(null, null, p);
        this.readAllAndShowInTableView();
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

    /**
     * Event Mehthod that gets called the the current row selections has changed
     * @param obs the ObservableValue
     * @param oldValue the old value
     * @param newValue the new Value
     */
    private void onSelectionChanged(ObservableValue<? extends Patient> obs, Patient oldValue, Patient newValue){
        if (newValue != null){
            currentSelection = newValue;
            System.out.println(!currentSelection.isLocked());
            System.out.println(currentSelection.isLocked());
            this.btnLock.setDisable(currentSelection.isLocked());
            this.btnPDFExport.setDisable(false);
        }
        else {
            this.btnLock.setDisable(true);
            this.btnLock.setDisable(true);
        }
    }
}