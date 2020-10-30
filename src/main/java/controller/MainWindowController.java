package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.UserType;

import java.io.IOException;

public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private void ShowNurseData(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/UserView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        UserController controller = loader.getController();

        ObservableList<UserType> options =
                FXCollections.observableArrayList(
                        UserType.Normal,
                        UserType.Nurse
                );
        controller.setUserTypes(options);
        MainStage.StartCancer();

    }

    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        AllPatientController controller = loader.getController();
        MainStage.StartCancer();

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
        MainStage.StartCancer();

    }

    @FXML
    private void btSperrenAction(ActionEvent event) {
        System.out.println("btSperrenAction");

        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/dlgLoginView.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            MainStage.primaryStage.setTitle("Login");
            MainStage.primaryStage.setScene(scene);
            MainStage.primaryStage.setResizable(false);
            MainStage.StartCancer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
