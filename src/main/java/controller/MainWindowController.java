package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.EncryptionHelper;

import java.io.File;
import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    private Stage stage;

    /**
     * Initialisiert den Controller
     * @param stage die Buehne worauf das Mainwindow dagestellt wird
     */
    public void initializeMainWindowController(Stage stage){
        this.stage = stage;
        this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                ConnectionBuilder.closeConnection();
                File ziel = new File("db/dblocked.script");
                File quelle = new File("db/nursingHomeDB.script");
                quelle.delete();
                EncryptionHelper.encryptFile(quelle, ziel);
                Platform.exit();
                System.exit(0);
            }
        });
    }


    @FXML
    /**
     * händelt das öffnen des Logijndaten-Erstellen Fensters
     */
    public void onHandleLoginErstellen()
    {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/CreateLoginView.fxml"));
            AnchorPane pane = loader.load();
            CreateLoginController controller = loader.getController();

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            controller.initialize(stage);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();

            this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    File ziel = new File("db/dblocked.script");
                    File quelle = new File("db/nursingHomeDB.script");
                    quelle.delete();
                    EncryptionHelper.encryptFile(quelle, ziel);
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML
    /**
     * Handled das Öffnen des Patientviews
     * @param e uebergebene Eventparams
     */
    private void handleShowAllPatient(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());

            //this.mainBorderPane.setClip(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPatientController controller = loader.getController();
    }

    @FXML
    /**
     * Handled das Öffnen des Treatmentviews
     * @param e uebergebene Eventparams
     */
    private void handleShowAllTreatments(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllTreatmentController controller = loader.getController();
    }


    @FXML
    /**
     * Handled das Öffnen des Nurseviews
     * @param e uebergebene Eventparams
     */
    private void handleShowAllNurses(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllNurseView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllNurseViewController controller = loader.getController();
    }

    @FXML
    /**
     * Handled ausloggen
     * @param event uebergebene Eventparams
     */
    public void sperren(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
        Stage buene = new Stage();
        Scene szene = new Scene(loader.load(), 990, 850);
        buene.setScene(szene);
        buene.show();
        LoginController controller = loader.getController();
        controller.initialize(buene);
        stage.close();


    }
}
