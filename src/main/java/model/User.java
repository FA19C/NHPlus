package model;

public class User extends Person {

    // Getter and Setter are for Faggots
    public static User LogginUser;

    private int ID;
    private String LogginName;
    private String LogginPasswort;
    private String telephoneNumber;
    private UserType UserType;

    public User(String firstName, String surname) {
        super(firstName, surname);
    }

    // private Person UserPerson;

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setLogginName(String logginName) {
        LogginName = logginName;
    }

    public String getLogginName() {
        return LogginName;
    }

    public void setLogginPasswort(String logginPasswort) {
        LogginPasswort = logginPasswort;
    }

    public String getLogginPasswort() {
        return LogginPasswort;
    }

    public model.UserType getUserType() {
        return UserType;
    }

    public void setUserType(model.UserType userType) {
        UserType = userType;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /*
    public void setUserPerson(Person userPerson) {
        UserPerson = userPerson;
    }

    public Person getUserPerson() {
        return UserPerson;
    }
    */

}

