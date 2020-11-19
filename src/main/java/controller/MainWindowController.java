package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.User;
import model.UserType;

import java.io.IOException;

/**
 * Controller class for the MainWindow View
 */
public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    /**
     * Eventmethod for showing the current Users Nurse Data
     * @param e Event params
     */
    @FXML
    private void ShowNurseData(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/UserView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        UserController controller = loader.getController();

        ObservableList<UserType> options = FXCollections.observableArrayList(UserType.values());
        controller.setUserTypes(options);
        controller.setUser(User.LogginUser);
    }

    /**
     * Eventmethod for showing the AllPatient View
     * @param e
     */
    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        AllPatientController controller = loader.getController();

    }

    /**
     * EventMethod for showing the All Caregiver View
     * @param e
     */
    @FXML
    private void handleShowAllNurses(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllCaregiverController controller = loader.getController();
    }

    /**
     * EventhMethod for showing the AllTreatment View
     * @param e
     */
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

    /**
     * EventMethod for logging out of The application if the lock button is pressed
     * @param event
     */
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

            User.LogginUser = null;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void btAllUser_Action(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllUserView.fxml"));
            mainBorderPane.setCenter(loader.load());

            AllUserController controller = loader.getController();
            controller.init();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
