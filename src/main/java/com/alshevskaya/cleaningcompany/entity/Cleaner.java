package com.alshevskaya.cleaningcompany.entity;

import java.util.StringJoiner;

public class Cleaner extends User {
    private int cleanerId;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_HASH = 31;

    public Cleaner() {
    }

    public Cleaner(String login, String password, UserRole role, int cleanerId, String name, String surname,
                   String phone, String address) {
        super(login, password, role);
        this.cleanerId = cleanerId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Cleaner(int cleanerId, String name, String surname, String phone, String address) {
        this.cleanerId = cleanerId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Cleaner(int cleanerId, String name, String surname, String phone, String address, String login) {
        super(login);
        this.cleanerId = cleanerId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Cleaner(String name, String surname, String phone, String address, String login) {
        super(login);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public int getCleanerId() {
        return cleanerId;
    }

    public void setCleanerId(int cleanerId) {
        this.cleanerId = cleanerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;
        if (!super.equals(o)) return false;

        Cleaner cleaner = (Cleaner) o;

        if (cleanerId != cleaner.cleanerId) return false;
        if (name != null ? !name.equals(cleaner.name) : cleaner.name != null) return false;
        if (surname != null ? !surname.equals(cleaner.surname) : cleaner.surname != null) return false;
        if (phone != null ? !phone.equals(cleaner.phone) : cleaner.phone != null) return false;
        return address != null ? address.equals(cleaner.address) : cleaner.address == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = NUMBER_HASH * result + cleanerId;
        result = NUMBER_HASH * result + (name != null ? name.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (surname != null ? surname.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (phone != null ? phone.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (address != null ? address.hashCode() : NUMBER_ZERO);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Cleaner.class.getSimpleName() + "[", "]")
                .add("cleanerId=" + cleanerId)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("phone='" + phone + "'")
                .add("address='" + address + "'")
                .toString();
    }
}
