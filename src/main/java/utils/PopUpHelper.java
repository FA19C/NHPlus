package utils;

import controller.Main;
import controller.NewTreatmentController;
import controller.PopUpViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Klasse um von überall Popupnarchichten anzuzeigen
 */
public abstract class PopUpHelper {

    public static void OpenPopUp(String text)
    {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/PopUpView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();

            PopUpViewController controller = loader.getController();
            controller.initialize(text, stage);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
