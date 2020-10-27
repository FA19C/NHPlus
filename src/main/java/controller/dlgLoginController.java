package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class dlgLoginController {

    @FXML
    void cancel_Click(MouseEvent event) {
        System.out.println("cancel_Click");
    }

    @FXML
    void ok_Click(MouseEvent event) {
        System.out.println("ok_Click");
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
        Parent pane = null;
        try {
            pane = loader.load();
            Scene scene = new Scene(pane);
            MainStage.primaryStage.setTitle("NHPlus");
            MainStage.primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}