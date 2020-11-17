package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.User;
import model.UserType;

public class AllUserController {

    @FXML
    private TableView<User> tbUser;
    @FXML
    private TableColumn<User, Integer> colID;

    @FXML
    private ChoiceBox<UserType> cbUserType;

    public AllUserController(){
        ObservableList<UserType> options = FXCollections.observableArrayList(UserType.values());
        options.add(0, null);
        cbUserType.setItems(options);
        populateTable();
    }

    public void populateTable(){



    }
}
