package controller;

import datastorage.ConnectionBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {

        MainStage.primaryStage = primaryStage;
        // this.primaryStage = primaryStage;
        mainWindow();
    }

    public void mainWindow() {
        try {
            if(!copieDBFiles())
                return;

            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/dlgLoginView.fxml"));
            Parent pane = loader.load();

            Scene scene = new Scene(pane);

            scene.getStylesheets().addAll(this.getClass().getResource("/Application.css").toExternalForm());
            MainStage.primaryStage.setTitle("Login");
            MainStage.primaryStage.setScene(scene);
            MainStage.primaryStage.setResizable(false);
            MainStage.primaryStage.show();

            MainStage.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    ConnectionBuilder.closeConnection();
                    Platform.exit();
                    System.exit(0);
                }
            });
            CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        MainStage.StartCancer();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
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