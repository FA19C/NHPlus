package datastorage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert die Grundsätze die jede DAO braucht um hiervon zu erben
 * @param <T>
 */
public abstract class DAOimp<T> implements DAO<T>{
    protected Connection conn;

    /**
     * Constructor
     * @param conn die Datenbankverbindung
     */
    public DAOimp(Connection conn) {
        this.conn = conn;
    }

    @Override
    /**
     * Erstelt ein Dateneintrag zu der implementierten DAO
     */
    public void create(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getCreateStatementString(t));
    }

    @Override
    /**
     * Liest einen Datenbankeintrag nach dem Key aus zu der implementierten DAO
     */
    public T read(int key) throws SQLException {
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadByIDStatementString(key));
        if (result.next()) {
            object = getInstanceFromResultSet(result);
        }
        return object;
    }

    @Override
    /**
     * Gibt alle Datenbankeintraege zurrueck zu der implementierten DAO
     */
    public List<T> readAll() throws SQLException {
        ArrayList<T> list = new ArrayList<T>();
        T object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementString());
        list = getListFromResultSet(result);
        return list;
    }

    @Override
    /**
     * Fuert ein update des ubergebenen Eintragen zu der implementierten DAO aus
     */
    public void update(T t) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(getUpdateStatementString(t));
    }

    @Override
    /**
     * loescht einen Datenbankeintrag zu der implementierten DAO mit dem uebergebenen key
     */
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
