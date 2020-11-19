package controller;

import datastorage.DAOFactory;
import datastorage.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Treatment;
import model.User;
import model.UserType;
import java.sql.SQLException;

/**
 * Controller containing all logic for the AllUser View
 */
public class AllUserController {

    @FXML
    private TableView<User> tbUser;

    @FXML
    private TableColumn<User, Integer> colID;

    @FXML
    private TableColumn<User, String> colVorname;

    @FXML
    private TableColumn<User, String> colNachname;

    @FXML
    private TableColumn<User, String> colTelefonnummer;

    @FXML
    private TableColumn<User, UserType> colUserType;

    @FXML
    private Button btCreate;

    @FXML
    private Button btDelete;

    @FXML
    private Label lUserType;

    @FXML
    private ChoiceBox<UserType> cbAllUserType;

    @FXML
    private Button btRefresh;

    private UserDAO userDao;

    private ObservableList<User> tableviewContent = FXCollections.observableArrayList();

    /**
     * Default Constructor
     */
    public AllUserController(){


    }

    public void init(){
        userDao = DAOFactory.getDAOFactory().createUserDAO();

        ObservableList<UserType> options = FXCollections.observableArrayList(UserType.values());
        options.add(0, null);
        cbAllUserType.setItems(options);
        populateTable();

        this.colID.setCellValueFactory(new PropertyValueFactory<User, Integer>("ID"));
        this.colVorname.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        this.colNachname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
        this.colTelefonnummer.setCellValueFactory(new PropertyValueFactory<User, String>("telephoneNumber"));
        this.colUserType.setCellValueFactory(new PropertyValueFactory<User, UserType>("UserType"));

        this.tbUser.setItems(tableviewContent);
    }

    @FXML
    void btCreate_Action(ActionEvent event) {

    }

    @FXML
    void btDelete_Action(ActionEvent event) {

    }

    @FXML
    void btRefresh_Action(ActionEvent event) {
        populateTable();
    }

    public void populateTable(){
        try {
            tableviewContent.clear();
            UserType userFilter = cbAllUserType.getValue();
            for(User user : userDao.readAll())
            {
                if(userFilter == null || userFilter == user.getUserType()){

                    tableviewContent.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
