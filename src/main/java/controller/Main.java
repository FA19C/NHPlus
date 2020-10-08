package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.Optional;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow() {
        try {
            if(!copieDBFiles())
                return;

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
            BorderPane pane = loader.load();

            Scene scene = new Scene(pane);
            this.primaryStage.setTitle("NHPlus");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();

            this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(!copieDBFiles())
            return;
    }

    private boolean copieDBFiles(){
        try{
            File databaseFile = new File("./db_backup/");
            String copieToPath = "./db/";
            for (File file : databaseFile.listFiles()) {
                if(file.isFile()) {
                    File copieToFile = new File(copieToPath + file.getName());

                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copieToFile));

                    byte[] buffer = new byte[4096];
                    int amount = 0;
                    while((amount = bis.read(buffer)) > 0) {
                        bos.write(buffer, 0, amount);
                    }
                    bis.close();
                    bos.flush();
                    bos.close();
                }
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}