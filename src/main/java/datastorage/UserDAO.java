package datastorage;

import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO extends DAOimp<User> {

    public UserDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(User User) {
        return String.format("INSERT INTO treatment (ID, name, pass) VALUES " +
                        "(%d, '%s', '%s')",User.getID(),User.getLogginName(),User.getLogginPasswort() );
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM user WHERE ID = %d", key);
    }

    public User getUserByName(String name) throws SQLException {
        User object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(String.format("SELECT * FROM user WHERE name = '%s'", name));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    @Override
    protected User getInstanceFromResultSet(ResultSet set) throws SQLException {
        User end = new User();
        end.setID(set.getInt(1));
        end.setLogginName(set.getString(2));
        end.setLogginPasswort(set.getString(3));
        return end;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM user";
    }

    @Override
    protected ArrayList<User> getListFromResultSet(ResultSet set) throws SQLException {
        ArrayList<User> list = new ArrayList<User>();
        User end = null;
        while (set.next()) {
            end = new User();
            end.setID(set.getInt(1));
            end.setLogginName(set.getString(2));
            end.setLogginPasswort(set.getString(3));
            list.add(end);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(User user) {
        return String.format("UPDATE user SET ID = %d, name ='%s', pass = '%s'", user.getID(),user.getLogginName(), user.getLogginPasswort());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM user WHERE id= %d", key);
    }
}
