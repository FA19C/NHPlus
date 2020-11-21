package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import utils.EncryptionHelper;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        File ziel = new File("db/nursingHomeDB.script");
        File quelle = new File("db/dblocked.script");
        EncryptionHelper.decryptFile(quelle, ziel);
        mainWindow();
    }



    public void mainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
            AnchorPane pane = loader.load();
            LoginController loginController = loader.getController();
            loginController.initialize(primaryStage);

            Scene scene = new Scene(pane);
            this.primaryStage.setTitle("NHPlus");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();

            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    File ziel = new File("db/dblocked.script");
                    File quelle = new File("db/nursingHomeDB.script");

                    EncryptionHelper.encryptFile(quelle, ziel);
                    quelle.delete();
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}