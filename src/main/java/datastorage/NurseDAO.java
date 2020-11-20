package datastorage;

import model.Nurse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Class providing Methods for managing Treatments in the Databse
 */
public class NurseDAO  extends DAOimp<Nurse>{
    /**
     * Default constructor for the NurseDAO class
     * @param conn the database connection
     */
    public NurseDAO(Connection conn) {
        super(conn);
    }

    /**
     * Returns a SQL command for creating a Nurse
     * @param nurse the Nurse data to be inserted into the Database
     * @return the SQL command string
     */
    @Override
    protected String getCreateStatementString(Nurse nurse) {
        return String.format("INSERT INTO nurse (firstname, surname, telephoneNumber) VALUES ('%s', '%s', '%s')",
                nurse.getFirstName(), nurse.getSurname(), nurse.getTelephoneNumber());
    }

    /**
     * Gets a SQL command string for selecting a Nurse by its ID
     * @param key the ID
     * @return
     */
    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM nurse WHERE pid = %d", key);
    }

    /**
     * Returns a single Nurse read from the given Resultset
     * @param result the ResultSet to be read
     * @return the user
     * @throws SQLException
     */
    @Override
    protected Nurse getInstanceFromResultSet(ResultSet result) throws SQLException {
        Nurse n = null;
        n = new Nurse(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4));
        return n;
    }

    /**
     * Returns a SQL command string for selecting all rows in the Nurse Table
     * @return the SQL command string
     */
    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM nurse";
    }
    /**
     * Returns a ArrayList of Nurses read from the given Resultset
     * @param result the ResultSet to be read
     * @return a ArrayList of Treatments
     * @throws SQLException
     */
    @Override
    protected ArrayList<Nurse> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Nurse> list = new ArrayList<Nurse>();
        Nurse n = null;
        while (result.next()) {
            n = new Nurse(result.getInt(1), result.getString(2),
                    result.getString(3), result.getString(4));
            list.add(n);
        }
        return list;
    }
    /**
     * Returns the Update SQL command string for the Nurse Table
     * @param nurse the new Nurse data
     * @return the SQL command string
     */
    @Override
    protected String getUpdateStatementString(Nurse nurse) {
        return String.format("UPDATE nurse SET firstname = '%s', surname = '%s', telephoneNumber = '%s', WHERE pid = %d",
                nurse.getFirstName(), nurse.getSurname(), nurse.getTelephoneNumber(), nurse.getPid());
    }
    /**
     * Gets the SQL command for deleting a Nurse with the given key
     * @param key the key
     * @return
     */
    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM nurse WHERE pid=%d", key);
    }
}
