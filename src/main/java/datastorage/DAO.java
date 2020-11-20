package datastorage;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for providing Method for managing a Database Table
 * @param <T> the Object type of the Database Table
 */
public interface DAO<T> {
    /**
     * Insert data into the Database
     * @param t the data to nbe inserted
     * @throws SQLException something went wrong while executing
     */
    void create(T t) throws SQLException;

    /**
     * Read the first result from the database
     * @param key the search key
     * @return the result
     * @throws SQLException something went wrong while executing
     */
    T read(int key) throws SQLException;

    /**
     * Reads all rows from the Database
     * @return all found rows
     * @throws SQLException something went wrong while executing
     */
    List<T> readAll() throws SQLException;

    /**
     * Updates a row in the Database
     * @param t the new Data
     * @throws SQLException something went wrong while executing
     */
    void update(T t) throws SQLException;

    /**
     * Deletes a single row in the Database
     * @param key the search key
     * @throws SQLException something went wrong while executing
     */
    void deleteById(int key) throws SQLException;
}
