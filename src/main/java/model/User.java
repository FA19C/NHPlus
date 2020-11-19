package model;

import java.util.ArrayList;

public class User extends Person {

    // Getter and Setter are for Faggots
    public static User LogginUser; //RIP {get;set;} this post was made by the C# gang

    private int ID;
    private String LoginName;
    private String LoginPasswort;
    private String telephoneNumber;
    private UserType UserType;

    /**
     * Default constructor for User class
     * @param firstName the Firstname of the user
     * @param surname the Surname of the user
     */
    public User(String firstName, String surname) {
        super(firstName, surname);
    }


    /**
     * setter for the ID property
     * @param ID the new ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * getter for the ID property
     * @return the users ID
     */
    public int getID() {
        return ID;
    }

    /**
     * setter for the Login name
     * @param loginName the new login name
     */
    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    /**
     * getter for the login name
     * @return the users login name
     */
    public String getLogginName() {
        return LoginName;
    }

    /**
     *  setter for the Login password
     * @param loginPasswort the new Login password
     */
    public void setLoginPasswort(String loginPasswort) {
        LoginPasswort = loginPasswort;
    }

    /**
     * getter for the Login password
     * @return the Login password
     */
    public String getLoginPasswort() {
        return LoginPasswort;
    }

    /**
     * getter for the Usertype
     * @return the Type of the Current User
     */
    public model.UserType getUserType() {
        return UserType;
    }

    /**
     * setter for the Usertype
     * @param userType the new Usertype
     */
    public void setUserType(model.UserType userType) {
        UserType = userType;
    }

    /**
     * getter for the users telephone number
     * @return
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * setter for the Users telephone number
     * @param telephoneNumber the new telephone number
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}

