package controller;

import datastorage.NurseDAO;
import datastorage.PatientDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Nurse;
import model.Patient;
import model.Treatment;
import datastorage.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllTreatmentController {
    @FXML
    private TableView<Treatment> tableView;
    @FXML
    private TableColumn<Treatment, Integer> colID;
    @FXML
    private TableColumn<Treatment, Integer> colPid;
    @FXML
    private TableColumn<Treatment, String> colDate;
    @FXML
    private TableColumn<Treatment, String> colBegin;
    @FXML
    private TableColumn<Treatment, String> colEnd;
    @FXML
    private TableColumn<Treatment, String> colDescription;
    @FXML
    private TableColumn<Treatment, Integer> colNID;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private ComboBox<String> comboBoxPfleger;
    @FXML
    private Button btnNewTreatment;
    @FXML
    private Button btnDelete;

    private ObservableList<Treatment> tableviewContent =
            FXCollections.observableArrayList();
    private TreatmentDAO dao;
    private ObservableList<String> myComboBoxData =
            FXCollections.observableArrayList();

    private ObservableList<String> myComboBoxDataPfleger =
            FXCollections.observableArrayList();
    private ArrayList<Patient> patientList;

    private ArrayList<Nurse> nurseList;

    private Main main;

    public void initialize() {
        readAllAndShowInTableView();
        comboBox.setItems(myComboBoxData);
        comboBox.getSelectionModel().select(0);

        comboBoxPfleger.setItems(myComboBoxDataPfleger);
        comboBoxPfleger.getSelectionModel().select(1);

        this.main = main;

        this.colID.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("tid"));
        this.colPid.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("pid"));
        this.colDate.setCellValueFactory(new PropertyValueFactory<Treatment, String>("date"));
        this.colBegin.setCellValueFactory(new PropertyValueFactory<Treatment, String>("begin"));
        this.colEnd.setCellValueFactory(new PropertyValueFactory<Treatment, String>("end"));
        this.colDescription.setCellValueFactory(new PropertyValueFactory<Treatment, String>("description"));
        this.colNID.setCellValueFactory(new PropertyValueFactory<Treatment, Integer>("nid"));
        this.tableView.setItems(this.tableviewContent);
        createComboBoxData();
        createComboBoxDataPfleger();
    }

    public void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        try {
            allTreatments = dao.readAllLockedSensitiv();
            for (Treatment treatment : allTreatments) {
                this.tableviewContent.add(treatment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createComboBoxData(){
        PatientDAO dao = DAOFactory.getDAOFactory().createPatientDAO();
        try {
            patientList = (ArrayList<Patient>) dao.readAll();
            this.myComboBoxData.add("alle");
            for (Patient patient: patientList) {
                if(!patient.getLocked())
                {
                    this.myComboBoxData.add(patient.getSurname());
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void createComboBoxDataPfleger(){
        NurseDAO dao = DAOFactory.getDAOFactory().createNurseDAD();
        try {
            nurseList = (ArrayList<Nurse>) dao.readAll();
            this.myComboBoxDataPfleger.add("alle");
            for (Nurse nurse: nurseList) {
                this.myComboBoxDataPfleger.add(nurse.getSurname());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    @FXML
    public void handleComboBox(){
        String p = this.comboBox.getSelectionModel().getSelectedItem();
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        if(p.equals("alle")){
            try {
                allTreatments= this.dao.readAllLockedSensitiv();
                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Patient patient = searchInList(p);
        if(patient !=null){
            try {
                allTreatments = dao.readTreatmentsByPidLockedSensitiv(patient.getPid());
                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private Patient searchInList(String surname){
        for (int i =0; i<this.patientList.size();i++){
            if(this.patientList.get(i).getSurname().equals(surname)){
                return this.patientList.get(i);
            }
        }
        return null;
    }


    @FXML
    public void handleComboBoxPfleger(){
        String n = this.comboBoxPfleger.getSelectionModel().getSelectedItem();
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        List<Treatment> allTreatments;
        if(n.equals("alle")){
            try {
                allTreatments= this.dao.readAllLockedSensitiv();
                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        Nurse nurse = searchInListNurse(n);
        if(nurse !=null){
            try {
                allTreatments = dao.readTreatmentsByNid(nurse.getNid());
                for (Treatment treatment : allTreatments) {
                    this.tableviewContent.add(treatment);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Nurse searchInListNurse(String surname){
        for (int i =0; i<this.nurseList.size();i++){
            if(this.nurseList.get(i).getSurname().equals(surname)){
                return this.nurseList.get(i);
            }
        }
        return null;
    }


    @FXML
    public void handleDelete(){
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment t = this.tableviewContent.remove(index);
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.deleteById((int) t.getTid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleNewTreatment() {
        try{
            String p = this.comboBox.getSelectionModel().getSelectedItem();
            Patient patient = searchInList(p);

            String n = this.comboBoxPfleger.getSelectionModel().getSelectedItem();
            Nurse nurse= searchInListNurse(n);

            newTreatmentWindow(patient, nurse);


        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Patient oder pfleger für die Behandlung fehlt!");
            alert.setContentText("Wählen Sie über die Combobox einen Patienten und einen pfleger aus!");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleMouseClick(){
        int index = this.tableView.getSelectionModel().getSelectedIndex();

        if (index > -1) {
            Treatment treatment = this.tableviewContent.get(index);
            treatmentWindow(treatment);
        }

    }





    private Stage stage;

    public void initializeNewTreatmentWindow(Stage stage){
        this.stage = stage;
    }

    public void newTreatmentWindow(Patient patient, Nurse nurse){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/NewTreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //da die primaryStage noch im Hintergrund bleiben soll
            Stage stage = new Stage();

           /* NewTreatmentController controller = loader.getController();
            controller.initialize(this, stage, patient);


            */

            NewTreatmentController controller2 = loader.getController();
            controller2.initialize(this, stage,patient ,nurse);



            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public void treatmentWindow(Treatment treatment){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/TreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //da die primaryStage noch im Hintergrund bleiben soll
            Stage stage = new Stage();
            TreatmentController controller = loader.getController();

            controller.initializeController(this, stage, treatment);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}