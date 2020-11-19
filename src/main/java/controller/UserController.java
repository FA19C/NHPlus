package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import model.UserType;
import model.User;

/**
 * Controller class containing all logic for the User View
 */
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

    private User user;

    /**
     * Method for setting the UserTypes
     * @param options
     */
    public void setUserTypes(ObservableList<UserType> options){
        cbUserType.setItems(options);
    }

    /**
     * Method for setting the Current User
     * @param user
     */
    public void setUser(User user){
        this.user = user;
        tbUserName.setText(user.getLogginName());
        tbFirstName.setText(user.getFirstName());
        tbLastName.setText(user.getSurname());
        tbPasswordField.setText(user.getLoginPasswort());
        cbUserType.setValue(user.getUserType());
    }

}
