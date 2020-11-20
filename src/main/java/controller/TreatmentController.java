package controller;

import datastorage.DAOFactory;
import datastorage.NurseDAO;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Nurse;
import model.Patient;
import model.Treatment;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;

public class TreatmentController {
    @FXML
    private Label lblPatientName;
    @FXML
    private Label lblCarelevel;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private Label lblNursID;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnCancel;

    private AllTreatmentController controller;
    private Stage stage;
    private Patient patient;
    private Treatment treatment;
    private Nurse nurse;

    /**
     * Innitialisert den Treatmentcontroller
     * @param controller der AllTreatmentcontroller woraus das Fenster geoffnet wird
     * @param stage die Stage worauf das Fenster ausgefuehrt werden soll
     * @param treatment das anzuzeigende Treatment
     */
    public void initializeController(AllTreatmentController controller, Stage stage, Treatment treatment) {
        this.stage = stage;
        this.controller= controller;
        PatientDAO pDao = DAOFactory.getDAOFactory().createPatientDAO();
        NurseDAO nDao = DAOFactory.getDAOFactory().createNurseDAD();

        try {
            this.nurse = nDao.read((int) treatment.getNid());
            this.patient = pDao.read((int) treatment.getPid());

            this.treatment = treatment;
            showData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * läd die Daten zum anzeigen rein
     */
    private void showData(){
        this.lblPatientName.setText(patient.getSurname()+", "+patient.getFirstName());
        this.lblCarelevel.setText(patient.getCareLevel());
        LocalDate date = DateConverter.convertStringToLocalDate(treatment.getDate());
        this.datepicker.setValue(date);
        this.txtBegin.setText(this.treatment.getBegin());
        this.txtEnd.setText(this.treatment.getEnd());
        this.txtDescription.setText(this.treatment.getDescription());
        this.taRemarks.setText(this.treatment.getRemarks());
        this.lblNursID.setText(String.valueOf(this.treatment.getNid()));

    }

    @FXML
    /**
     * Handle fuer Datenaenderung
     */
    public void handleChange(){
        this.treatment.setDate(this.datepicker.getValue().toString());
        this.treatment.setBegin(txtBegin.getText());
        this.treatment.setEnd(txtEnd.getText());
        this.treatment.setDescription(txtDescription.getText());
        this.treatment.setRemarks(taRemarks.getText());
        doUpdate();
        controller.readAllAndShowInTableView();
        stage.close();
    }

    /**
     * Methode zum Ausfuehren von Updates vom Treatment was gerade im COntroller ist
     */
    private void doUpdate(){
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.update(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    /**
     * Handle zum schließen über Close
     */
    public void handleCancel(){
        stage.close();
    }
}