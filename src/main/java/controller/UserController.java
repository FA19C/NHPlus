package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
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
    private ChoiceBox<UserType> cbUserType;
}
