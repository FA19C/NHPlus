package model;

public class User  {
    private int ID;
    private String LogginName;
    private String LogginPasswort;

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
}

