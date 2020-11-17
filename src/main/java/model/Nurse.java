package model;

public class Nurse extends Person {

    public long nid;
    public String phonenumber;

    public Nurse(long nid, String firstName, String surname, String phonenumber) {
        super(firstName, surname);
        this.nid = nid;
        this.phonenumber = phonenumber;

    }


    public Nurse(String firstName, String surname, String phonenumber) {
        super(firstName, surname);
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "nid=" + this.nid +
                "\n, phonenumber='" + this.phonenumber +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                '}';
    }

    public long getNid() {
        return nid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
