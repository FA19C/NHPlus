package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.EncryptionHelper;

import java.io.IOException;
import java.sql.*;


public class LoginController {

    @FXML
    public TextField username;


    @FXML
    public TextField password;

    @FXML
    public Button einloggen;

    private Stage stage;

    public void initialize(Stage stage){
        this.stage = stage;
    }

    /**
     * Handlet das bestätigen der Einloggdaten über den Einloggenknopf
     * @throws SQLException SQLException bei abrufen ob Einloggdaten stimmen
     */
    public void einloggenEvent() throws SQLException {

        Connection connection;
        PreparedStatement ps;

        String name = EncryptionHelper.encryptString(username.getText());
        String passwort = EncryptionHelper.encryptString(password.getText());

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));


        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:db/nursingHomeDB", "SA", "SA");
            ps = connection.prepareStatement("SELECT username, password FROM user WHERE username = ? AND password = ?");
            ps.setString(1, name);
            ps.setString(2, passwort);
            ResultSet result = ps.executeQuery();

            if (!result.next()) {

                Stage buene = new Stage();
                HBox horizonalesBox = new HBox();
                Scene szene = new Scene(horizonalesBox, 400, 400);
                Label ergebnis = new Label("Anmeldung Fehlgeschlagen :( \n Password oder Username ist falsch");

                horizonalesBox.getChildren().add(ergebnis);

                buene.setTitle("Ergebnis");
                buene.setScene(szene);
                buene.show();

            }else {

                Stage buene = new Stage();
                Scene szene = new Scene(loader.load(), 990, 850);
                MainWindowController controller = loader.getController();
                buene.setScene(szene);
                buene.show();
                stage.close();
                controller.initializeMainWindowController(buene);



            }
        } catch (IOException ex) {
                ex.printStackTrace();
            }

        MainWindowController controller = loader.getController();

    }

    /**
     * Handles closing the window if the user doesn't want to log in
     */
    @FXML
    public void handleCancel() {
        stage.close();
    }


}

