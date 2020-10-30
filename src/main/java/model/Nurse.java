package model;

public class Nurse extends Person {

    private String telephoneNumber;

    public Nurse(String firstName, String surname, String telNumber) {
        super(firstName, surname);
        this.telephoneNumber = telNumber;
    }
}
