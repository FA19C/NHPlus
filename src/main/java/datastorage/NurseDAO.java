package datastorage;

import model.Nurse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NurseDAO  extends DAOimp<Nurse>{

    public NurseDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Nurse nurse) {
        return String.format("INSERT INTO nurse (firstname, surname, telephoneNumber) VALUES ('%s', '%s', '%s')",
                nurse.getFirstName(), nurse.getSurname(), nurse.getTelephoneNumber());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM nurse WHERE pid = %d", key);
    }

    @Override
    protected Nurse getInstanceFromResultSet(ResultSet result) throws SQLException {
        Nurse n = null;
        n = new Nurse(result.getInt(1), result.getString(2),
                result.getString(3), result.getString(4));
        return n;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM nurse";
    }

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

    @Override
    protected String getUpdateStatementString(Nurse nurse) {
        return String.format("UPDATE nurse SET firstname = '%s', surname = '%s', telephoneNumber = '%s', WHERE pid = %d",
                nurse.getFirstName(), nurse.getSurname(), nurse.getTelephoneNumber(), nurse.getPid());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM nurse WHERE pid=%d", key);
    }
}
