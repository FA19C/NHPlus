package datastorage;

import model.Treatment;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO extends DAOimp<Treatment> {

    public TreatmentDAO(Connection conn) {
        super(conn);
    }

    @Override
    protected String getCreateStatementString(Treatment treatment) {
        return String.format("INSERT INTO treatment (pid, treatment_date, begin, end, description, remarks, nid) VALUES " +
                "(%d, '%s', '%s', '%s', '%s', '%s', '%d')", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(),
                treatment.getRemarks(), treatment.getNid());
    }

    @Override
    protected String getReadByIDStatementString(int key) {
        return String.format("SELECT * FROM treatment WHERE tid = %d", key);
    }

    @Override
    protected Treatment getInstanceFromResultSet(ResultSet result) throws SQLException {
        LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
        LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
        LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
        Treatment m = new Treatment(result.getLong(1), result.getLong(2),
                date, begin, end, result.getString(6), result.getString(7), result.getLong(8));
        return m;
    }

    public List<Treatment> readAllLockedSensitiv() throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllStatementStringLockedSensitiv());
        list = getListFromResultSet(result);
        return list;
    }

    @Override
    protected String getReadAllStatementString() {
        return "SELECT * FROM treatment";
    }

    protected String getReadAllStatementStringLockedSensitiv() {
        return "SELECT * FROM treatment a, patient b WHERE a.pid = b.pid and (b.locked = FALSE or b.locked is NULL)";
    }

    public Treatment readNewestTreatmentByPid(long pid) throws SQLException
    {
        Treatment t = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadNewestTreatmentByPid(pid));
        if(result.next() != false)
        {
            t = getInstanceFromResultSet(result);
        }

        return t;
    }

    protected String getReadNewestTreatmentByPid(long pid)
    {
        return String.format("SELECT * FROM  TREATMENT t1 WHERE t1.PID = %d AND t1.TREATMENT_DATE = (SELECT max(TREATMENT_DATE) from TREATMENT WHERE t1.PID =TREATMENT.PID)", pid);
    }


    @Override
    protected ArrayList<Treatment> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment t = null;
        while (result.next()) {
            LocalDate date = DateConverter.convertStringToLocalDate(result.getString(3));
            LocalTime begin = DateConverter.convertStringToLocalTime(result.getString(4));
            LocalTime end = DateConverter.convertStringToLocalTime(result.getString(5));
            t = new Treatment(result.getLong(1), result.getLong(2),
                    date, begin, end, result.getString(6), result.getString(7), result.getLong(8));
            list.add(t);
        }
        return list;
    }

    @Override
    protected String getUpdateStatementString(Treatment treatment) {
        return String.format("UPDATE treatment SET pid = %d, treatment_date ='%s', begin = '%s', end = '%s'," +
                "description = '%s', remarks = '%s', nid='%d' WHERE tid = %d", treatment.getPid(), treatment.getDate(),
                treatment.getBegin(), treatment.getEnd(), treatment.getDescription(), treatment.getRemarks(),
                treatment.getNid(), treatment.getTid());
    }

    @Override
    protected String getDeleteStatementString(int key) {
        return String.format("Delete FROM treatment WHERE tid= %d", key);
    }

    public List<Treatment> readTreatmentsByPid(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPid(pid));
        list = getListFromResultSet(result);
        return list;
    }

    public List<Treatment> readTreatmentsByPidLockedSensitiv(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPidLockedSensitiv(pid));
        list = getListFromResultSet(result);
        return list;
    }

    public List<Treatment> readTreatmentsByNid(long nid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOneNurseByNid(nid));
        list = getListFromResultSet(result);
        return list;
    }

    private String getReadAllTreatmentsOfOneNurseByNid(long nid){
        return String.format("SELECT * FROM treatment WHERE nid = %d", nid);
    }

    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    private String getReadAllTreatmentsOfOnePatientByPidLockedSensitiv(long pid){
        return String.format("SELECT * FROM treatment a, patient b WHERE a.pid = %d and a.pid = b.pid and b.locked = FALSE", pid);
    }

    public void deleteByPid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }

    public void deleteByNid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE nid= %d", key));
    }
}