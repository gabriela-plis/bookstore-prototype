package org.example;

import java.util.Objects;

public abstract class Person {

    //protected vs private check!
    private final String firstName;
    private final String lastName;
    private String phone;
    private final SensitiveAccountData sensitiveData;

    public Person (String firstName, String lastName, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        sensitiveData = new SensitiveAccountData(password);
    }


    public Person (String firstName, String lastName, String phone, String password, int ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        sensitiveData = new SensitiveAccountData(password, ID);
    }

    public SensitiveAccountData getSensitiveData() {
        return sensitiveData;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(phone, person.phone) && Objects.equals(sensitiveData, person.sensitiveData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phone, sensitiveData);
    }
}
