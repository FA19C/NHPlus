package datastorage;

/**
 * Class providing Methods for generating and accessing differend DAO implementations
 */
public class DAOFactory {

    private static DAOFactory instance;

    private DAOFactory() {

    }

    /**
     * Singleton accessor for the DAOFactory
     * @return
     */
    public static DAOFactory getDAOFactory() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    /**
     * Returns a new instance of the <code>TreatmentDAO</code> class.
     * @return
     */
    public TreatmentDAO createTreatmentDAO() {
        return new TreatmentDAO(ConnectionBuilder.getConnection());
    }

    /**
     * Returns a new instance of the <code>PatientDAO</code> class.
     * @return
     */
    public PatientDAO createPatientDAO() {
        return new PatientDAO(ConnectionBuilder.getConnection());
    }

    /**
     * Returns a new instance of the <code>UserDAO</code> class.
     * @return
     */
    public UserDAO createUserDAO() {
        return new UserDAO(ConnectionBuilder.getConnection());
    }

    /**
     * Returns a new instance of the <code>NurseDAO</code> class.
     * @return
     */
    public NurseDAO createNurseDAO() {
        return new NurseDAO(ConnectionBuilder.getConnection());
    }

}