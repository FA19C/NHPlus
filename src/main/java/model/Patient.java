package model;

import utils.DateConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Patients live in a NURSING home and are treated by nurses.
 */
public class Patient extends Person {
    private long pid;
    private LocalDate dateOfBirth;
    private String careLevel;
    private String roomnumber;
    private List<Treatment> allTreatments = new ArrayList<Treatment>();
    private boolean isLocked;


    /**
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     */
    public Patient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber) {
        this(firstName, surname, dateOfBirth, careLevel, roomnumber, false);
    }

    /**
     * constructs a patient from the given params.
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     */
    public Patient(String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, boolean isLocked) {
        super(firstName, surname);
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.isLocked = isLocked;
    }

    /**
     * constructs a patient from the given params.
     * @param pid
     * @param firstName
     * @param surname
     * @param dateOfBirth
     * @param careLevel
     * @param roomnumber
     */
    public Patient(long pid, String firstName, String surname, LocalDate dateOfBirth, String careLevel, String roomnumber, Boolean isLocked) {
        super(firstName, surname);
        this.pid = pid;
        this.dateOfBirth = dateOfBirth;
        this.careLevel = careLevel;
        this.roomnumber = roomnumber;
        this.isLocked = (isLocked != null) ? isLocked : false;
    }

    /**
     * Getter for the Patient ID
     * @return patient id
     */
    public long getPid() {
        return pid;
    }

    /**
     *  Getter for the Patient Birthdate
     * @return date of birth as a string
     */
    public String getDateOfBirth() {
        return dateOfBirth.toString();
    }

    /**
     * Setter for the Patient Birthdate
     * convert given param to a localDate and store as new <code>birthOfDate</code>
     * @param dateOfBirth as string in the following format: YYYY-MM-DD
     */
    public void setDateOfBirth(String dateOfBirth) {
        LocalDate birthday = DateConverter.convertStringToLocalDate(dateOfBirth);
        this.dateOfBirth = birthday;
    }

    /**
     * Getter for the Patient Carelevel
     * @return careLevel
     */
    public String getCareLevel() {
        return careLevel;
    }

    /**
     * Setter for the Patient Carelevel
     * @param careLevel new care level
     */
    public void setCareLevel(String careLevel) {
        this.careLevel = careLevel;
    }

    /**
     * Getter for the Patients roomnumber
     * @return roomNumber as string
     */
    public String getRoomnumber() {
        return roomnumber;
    }

    /**
     * Setter for the Patients roomnumber
     * @param roomnumber
     */
    public void setRoomnumber(String roomnumber) {
        this.roomnumber = roomnumber;
    }

    /**
     * adds a treatment to the treatment-list, if it does not already contain it.
     * @param m Treatment
     * @return true if the treatment was not already part of the list. otherwise false
     */
    public boolean add(Treatment m) {
        if (!this.allTreatments.contains(m)) {
            this.allTreatments.add(m);
            return true;
        }
        return false;
    }

    /**
     * Method for getting the Patients Data lockstate
     * @return
     */
    public boolean isLocked(){ return this.isLocked; }

    /**
     * Method for Locking a Patients data
     */
    public void lockPatient(){
        this.isLocked = true;
    }

    /**
     * Method for Unlocking a Patients data
     */
    public void unlockPatient(){
        this.isLocked = false;
    }

    /**
     * Setter for the Patient data lockstate
     * @param lockState
     */
    public void setLockState(boolean lockState){
        this.isLocked = lockState;
    }
    /**
     *
     * @return string-representation of the patient
     */
    public String toString() {
        if (this.isLocked){
            return "Patient" + "\nMNID: " + this.pid +
                    "\nFirstname: " + this.getFirstName() +
                    "\nSurname: " + this.getSurname() +
                    "\nIsLocked: " + this.isLocked +
                    "\n";
        }
        return "Patient" + "\nMNID: " + this.pid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nBirthday: " + this.dateOfBirth +
                "\nCarelevel: " + this.careLevel +
                "\nRoomnumber: " + this.roomnumber +
                "\nIsLocked: " + this.isLocked +
                "\n";
    }
}