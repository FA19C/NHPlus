package controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.EncryptionHelper;

import java.io.IOException;
import java.sql.*;
/**
 * Hilfsfenster um Logins einfach zu erstellen
 */

import javafx.fxml.FXML;
import org.w3c.dom.Text;

public class CreateLoginController
{
    @FXML
    private Label lblText;

    @FXML
    private Button btnOk;

    @FXML
    private PasswordField pfPasswort;

    @FXML
    private TextField tfName;

    private Stage stage;

    public void initialize(Stage stage)
    {
        this.stage = stage;
    }

    @FXML
    /**
     * Speichert die Eingaben
     */
    public void onHandleOk()
    {
        Connection connection;
        PreparedStatement ps;

        String name = EncryptionHelper.encrypt(tfName.getText());
        String passwort = EncryptionHelper.encrypt(pfPasswort.getText());
        try
        {
            connection = DriverManager.getConnection("jdbc:hsqldb:db/nursingHomeDB", "SA", "SA");
            ps = connection.prepareStatement("INSERT INTO USER (USERNAME, PASSWORD) VALUES (?, ?)");
            ps.setString(1, name);
            ps.setString(2, passwort);
            ps.execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        this.stage.close();
    }

}
