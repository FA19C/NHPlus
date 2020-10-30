package model;


public class Nurse extends Person {

    private long pid;
    private String telephoneNumber;

    /**
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param telephoneNumber
     */
    public Nurse(String firstName, String surname, String telephoneNumber) {
        this(0, firstName, surname, telephoneNumber);
    }

    /**
     * constructs a patient from the given params.
     * @param pid
     * @param firstName
     * @param surname
     * @param telephoneNumber
     */
    public Nurse(long pid, String firstName, String surname, String telephoneNumber) {
        super(firstName, surname);
        this.pid = pid;
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public long getPid() {
        return pid;
    }
}
