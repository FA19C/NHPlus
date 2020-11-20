package datastorage;

public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    /**
     * gibt die DAOFactory zurrueck
     * @return gibt die DAOFactory zurrueck
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    public NurseDAO createNurseDAD(){ return new NurseDAO(ConnectionBuilder.getConnection()); }
}