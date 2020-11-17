package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    private Stage stage;

    public void initializeMainWindowController(Stage stage){
        this.stage = stage;
    }


    @FXML
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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
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
    public void sperren(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
        Stage bühne = new Stage();
        Scene szene = new Scene(loader.load(), 990, 850);
        bühne.setScene(szene);
        bühne.show();
        LoginController controller = loader.getController();
        controller.initialize(bühne);
        stage.close();


    }
}
