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

    /**
     * Liest alle behandlungen aus von nicht gesperrten patienten
     * @return alle behandlungen von nicht gesperrten patienten
     * @throws SQLException SQLException bei lesen des lockedstatus aller Patienten
     */
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

    /**
     * Erstellt einen SQL-String für die Abfrage nach allen Behandlungen von nicht gesperrten Patienten
     * @return einen SQL-String für die Abfrage nach allen Behandlungen von nicht gesperrten Patienten
     */
    protected String getReadAllStatementStringLockedSensitiv() {
        return "SELECT * FROM treatment a, patient b WHERE a.pid = b.pid and (b.locked = FALSE or b.locked is NULL)";
    }

    /**
     * Erstellt eine Abfrage um herauszufinden wann ein Patient zuletzt behandelt wurde
     * @param pid der Patient
     * @return die letzte Behandlung
     * @throws SQLException SQLException bei herausfinden der neuesten Behandlung
     */
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

    /**
     * Erstellt einen ABfragestring um herrauszufinden wan der Patient zuletzt behandelt wurde
     * @param pid Patienten
     * @return die SQL-Abfrage
     */
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

    /**
     * Liest alle Treatments von einem Patienten und beachtet ob ideser gesperrt ist
     * @param pid der Patient
     * @return alle Treatments von einem Patienten und beachtet ob ideser gesperrt ist
     * @throws SQLException SQLException bei lesen der Patiententreatments lockedsensitiv
     */
    public List<Treatment> readTreatmentsByPidLockedSensitiv(long pid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOnePatientByPidLockedSensitiv(pid));
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * Liest alle Treatments von einem Pfleger und beachtet ob deser gesperrt ist
     * @param nid der Pfleger
     * @return alle Treatments von einem Pfleger und beachtet ob ideser gesperrt ist
     * @throws SQLException SQLException bei lesen der Treatments nach NurseID
     */
    public List<Treatment> readTreatmentsByNidLockedSensitiv(long nid) throws SQLException {
        ArrayList<Treatment> list = new ArrayList<Treatment>();
        Treatment object = null;
        Statement st = conn.createStatement();
        ResultSet result = st.executeQuery(getReadAllTreatmentsOfOneNurseByNidLockedSensitiv(nid));
        list = getListFromResultSet(result);
        return list;
    }

    /**
     * Erstellt SQL-String der alle Treatments nach Pfleger ausließt und beachtet ob der dazugehörige Patient gesperrt ist
     * @param nid Pflegerid
     * @return SQL-String der alle Treatments nach Pfleger ausließt und beachtet ob der dazugehörige Patient gesperrt ist
     */
    private String getReadAllTreatmentsOfOneNurseByNidLockedSensitiv(long nid){
        return String.format("SELECT * FROM treatment a, patient b WHERE a.nid = %d and a.pid = b.pid and (b.locked = FALSE or b.locked IS NULL)", nid);
    }

    /**
     * Erstellt einen SQL-String der alle Treatments eines Patienten abfragt
     * @param pid PatientenId
     * @return einen SQL-String der alle Treatments eines Patienten abfragt
     */
    private String getReadAllTreatmentsOfOnePatientByPid(long pid){
        return String.format("SELECT * FROM treatment WHERE pid = %d", pid);
    }

    /**
     * Erstellt einen SQL-String der alle Treatments eines Patienten abfragt und locked beachtet
     * @param pid PatientenId
     * @return einen SQL-String der alle Treatments eines Patienten abfragt und locked beachtet
     */
    private String getReadAllTreatmentsOfOnePatientByPidLockedSensitiv(long pid){
        return String.format("SELECT * FROM treatment a, patient b WHERE a.pid = %d and a.pid = b.pid and (b.locked = FALSE or b.locked IS NULL)", pid);
    }

    public void deleteByPid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE pid= %d", key));
    }

    /**
     * Erstellt einen SQL-String der ein Treatment nach PflegerId loescht
     * @param key die PflegerId
     * @throws SQLException SQLException bei loeschen der Nurse
     */
    public void deleteByNid(int key) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(String.format("Delete FROM treatment WHERE nid= %d", key));
    }
}