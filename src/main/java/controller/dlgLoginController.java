package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class dlgLoginController {

    private UserDAO uDao;

    @FXML
    private TextField tbUserName;

    @FXML
    private PasswordField tbPassword;

    public dlgLoginController(){
        uDao = DAOFactory.getDAOFactory().createUserDAO();
    }

    @FXML
    void cancel_Action(ActionEvent event) {
        System.out.println("cancel_Click");
        MainStage.primaryStage.close();
    }

    @FXML
    void ok_Action(ActionEvent event) {
        System.out.println("ok_Click " + tbUserName.getText() + " " + tbPassword.getText());

        if(tbUserName.getText() != ""){

            try {
                User user = uDao.getUserByName(tbUserName.getText());
                if(user != null && user.getLogginPasswort().equals(tbPassword.getText())){

                    User.LogginUser = user;

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

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }
}