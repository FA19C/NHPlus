package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.EncryptionHelper;

import java.sql.*;
/**
 * Hilfsfenster um Logins einfach zu erstellen
 */

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

        String name = EncryptionHelper.encryptString(tfName.getText());
        String passwort = EncryptionHelper.encryptString(pfPasswort.getText());
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
