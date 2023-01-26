package org.example;

public class SensitiveAccountData {
    private String password;
    private int ID;

    //narazie podaje tylko customersDatabase
    public SensitiveAccountData (String password) {
        this.password = password;
    }

    public SensitiveAccountData (String password, int ID) {
        this.password = password;
        this.ID = ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }
}
