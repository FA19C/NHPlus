package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import model.UserType;

public class UserController {

    @FXML
    private TextField tbUserName;

    @FXML
    private TextField tbFirstName;

    @FXML
    private TextField tbLastName;

    @FXML
    private TextField tbTel;

    @FXML
    private PasswordField tbPasswordField;

    @FXML
    private ChoiceBox<UserType> cbUserType;

    public void setUserTypes(ObservableList<UserType> options){
        cbUserType.setItems(options);
    }
}
