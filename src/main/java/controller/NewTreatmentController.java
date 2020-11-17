package controller;

import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Nurse;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private Label lblNurseID;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;


    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;
    private Nurse nurse;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        showPatientData();

    }

    public void initializePfleger(AllTreatmentController controller, Stage stage, Nurse nurse) {
        this.controller= controller;
        this.nurse = nurse;
        this.stage = stage;
        showNurseData();
    }


    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    private void showNurseData(){
        this.lblNurseID.setText(String.valueOf(this.nurse.getNid()));
    }

    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();

        Treatment treatment = new Treatment(patient.getPid(), date,
                begin, end, description, remarks, nurse.getNid());
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }

}