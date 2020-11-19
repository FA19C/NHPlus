package datastorage;

import model.User;
import model.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A Class providing methods to manage users in the Database
 */
public class UserDAO extends DAOimp<User> {
    /**
     * Default constructor for the UserDAO class
     * @param conn
     */
    public UserDAO(Connection conn) {
        super(conn);
    }

    /**
     * Returns a SQL command for creating a User
     * @param User the User data to be inserted into the Database
     * @return the SQL command string
     */
    @Override
    protected String getCreateStatementString(User User) {
        return String.format("INSERT INTO treatment (ID, USERNAME, PASS, FIRSTNAME, LASTNAME, TEL, USER_TYPE) VALUES " +
                        "(%d, '%s', '%s', '%s', '%s', '%s', %d)",
                User.getID(),User.getLogginName(),User.getLoginPasswort(),User.getFirstName(),
                User.getSurname(), User.getTelephoneNumber(), User.getUserType().dataBaseValue);
    }

    /**
     * Returns a SQL command string for getting all users with the given ID
     * @param key the ID used in the search
     * @return the SQL command string
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM user WHERE ID = %d", key);
    }

    /**
     * Returns a User by searching the table with the given username
     * @param name the name used for the search
     * @return the found User
     * @throws SQLException
     */
    public User getUserByName(String name) throws SQLException {
        User object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(String.format("SELECT * FROM user WHERE USERNAME = '%s'", name));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     * Returns a single user read from the given Resultset
     * @param set the ResultSet to be read
     * @return the user
     * @throws SQLException
     */
    @Override
    protected User getInstanceFromResultSet(ResultSet set) throws SQLException {
        User end = new User(set.getString(4), set.getString(5));
        end.setID(set.getInt(1));
        end.setLoginName(set.getString(2));
        end.setLoginPasswort(set.getString(3));
        end.setTelephoneNumber(set.getString(6));
        end.setUserType(UserType.getUserTypeFromDataBaseValue(set.getInt(7)));
        return end;
    }

    /**
     * Returns a SQL command string for selecting all rows in the User Table
     * @return the SQL command string
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    /**
     * Returns a ArrayList of users read from the given Resultset
     * @param set the ResultSet to be read
     * @return a ArrayList of users
     * @throws SQLException
     */
    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        User end = null;
        while (set.next()) {
            end = new User(set.getString(4), set.getString(5));
            end.setID(set.getInt(1));
            end.setLoginName(set.getString(2));
            end.setLoginPasswort(set.getString(3));
            end.setTelephoneNumber(set.getString(6));
            end.setUserType(UserType.getUserTypeFromDataBaseValue(set.getInt(7)));
            list.add(end);
        }
        return list;
    }

    /**
     * Returns the Update SQL command string for the users Table
     * @param user the new User data
     * @return the SQL command string
     */
    @Override
    protected String getUpdateStatementString(User user) {
        return String.format("UPDATE user SET ID = %d, USERNAME ='%s', pass = '%s'", user.getID(),user.getLogginName(), user.getLoginPasswort());
    }

    /**
     * Returns the SQL command string for deleting a User
     * @param key the user ID
     * @return the SQL command string
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM user WHERE id= %d", key);
    }
}
