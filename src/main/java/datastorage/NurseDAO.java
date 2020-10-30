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
        return null;
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return null;
    }

    @Override
    protected Nurse getInstanceFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getReadAllStatementString() {
        return null;
    }

    @Override
    protected ArrayList<Nurse> getListFromResultSet(ResultSet set) throws SQLException {
        return null;
    }

    @Override
    protected String getUpdateStatementString(Nurse nurse) {
        return null;
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return null;
    }
}
