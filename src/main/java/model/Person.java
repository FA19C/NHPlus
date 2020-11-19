package model;

/**
 * Base class for Person based Models
 */
public abstract class Person {
    private String firstName;
    private String surname;

    /**
     * Default Constructor
     * @param firstName
     * @param surname
     */
    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    /**
     * Getter for the Firstname
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the Firstname
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the Surname
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Setter for the Surname
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
