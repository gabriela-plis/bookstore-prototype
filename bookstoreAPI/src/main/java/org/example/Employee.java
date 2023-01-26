package org.example;

public class Employee extends Person implements DBObject, Readable {

    public Employee(int ID, String password, String firstname, String lastname, String phone) {
        super(firstname, lastname, phone, password, ID);
    }

    public String read() {
        String connector = ",";
        return super.getFirstName() + connector + super.getLastName() + connector + super.getPhone() + connector + super.getSensitiveData().getPassword() + connector + super.getSensitiveData().getID();
    }

    @Override
    public int getID() {
        return getSensitiveData().getID();
    }
}
