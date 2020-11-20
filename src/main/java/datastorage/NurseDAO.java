package datastorage;

import model.Nurse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * Implements the Interface <code>DAOImp</code>. Overrides methods to generate specific nurse-SQL-queries.
 */
public class NurseDAO extends DAOimp<Nurse> {

    /**
     * constructs Onbject. Calls the Constructor from <code>DAOImp</code> to store the connection.
     * @param conn Connectio
     */
    public NurseDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Nurse nurse) {
        return String.format("INSERT INTO nurse (firstname, surname, phonenumber) VALUES ('%s', '%s', '%s')",
                 nurse.getFirstName(), nurse.getSurname(), nurse.getPhonenumber());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM nurse WHERE nid = %d", key);
    }

    @Override
    protected Nurse getInstanceFromResultSet(ResultSet result) throws SQLException {
        Nurse n = null;

        n = new Nurse(result.getInt(1), result.getString(2), result.getString(3), result.getString(4));
        return n;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM nurse";
    }

    @Override
    protected ArrayList<Nurse> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Nurse> list = new ArrayList<>();

        Nurse n = null;

        while (result.next()) {
            n = new Nurse(result.getInt(1), result.getString(2), result.getString(3),
                    result.getString(4));
            list.add(n);
        }

        return list;
    }

    @Override
    protected String getUpdateStatementString(Nurse nurse) {
        return String.format("UPDATE nurse SET  firstname= '%s', surname= '%s', phonenumber= '%s' WHERE nid=%d",
                nurse.getFirstName(), nurse.getSurname(), nurse.getPhonenumber(), nurse.getNid());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM nurse WHERE nid=%d", key);

    }
}
