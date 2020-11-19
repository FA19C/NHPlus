package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.UserType;
import model.User;
import java.sql.SQLException;

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

    @FXML
    private Button btSave;

    private User user;
    private UserDAO userDao;

    /**
     * Method for setting the UserTypes
     * @param options
     */
    public void setUserTypes(ObservableList<UserType> options){
        userDao = DAOFactory.getDAOFactory().createUserDAO();
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
        tbTel.setText(user.getTelephoneNumber());
    }

    @FXML
    void btSave_Click(MouseEvent event) {
        if(user != null){
            try {
                // tbUserName.getText());
                user.setFirstName(tbFirstName.getText());
                user.setSurname(tbLastName.getText());
                user.setLoginPasswort(tbPasswordField.getText());
                user.setUserType(cbUserType.getValue());
                user.setTelephoneNumber(tbTel.getText());

                userDao.update(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
