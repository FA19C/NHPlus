package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Default base implementation of the DAO interface
 * @param <T> the Object type of the database table
 */
public abstract class DAOimp<T> implements DAO<T>{
    protected Connection conn;

    /**
     * Default constructor of the DAOImp class
     * @param conn the database connection
     */
    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    /**
     * Executes a create statement on the database
     * @param t the data to be inserted
     * @throws SQLException something went wrong while executing
     */
    @Override
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    /**
     * Executes a Select statement on the database and returns the first result
     * @param key the search key
     * @return the found data
     * @throws SQLException something went wrong while executing
     */
    @Override
    public T read(int key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    /**
     * Executes a Select statement on the database and returns all rows
     * @return the list of found data
     * @throws SQLException something went wrong while executing
     */
    @Override
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * Executes an Update statement on the database
     * @param t the new data to be updated
     * @throws SQLException something went wrong while executing
     */
    @Override
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    /**
     * Executes a Delete statement on the database
     * @param key the search key
     * @throws SQLException something went wrong while executing
     */
    @Override
    public void deleteById(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getDeleteStatementString(key));
    }

    protected abstract String getCreateStatementString(T t);

    protected abstract String getReadByIDStatementString(int key);

    protected abstract T getInstanceFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getReadAllStatementString();

    protected abstract ArrayList<T> getListFromResultSet(ResultSet set) throws SQLException;

    protected abstract String getUpdateStatementString(T t);

    protected abstract String getDeleteStatementString(int key);
}
