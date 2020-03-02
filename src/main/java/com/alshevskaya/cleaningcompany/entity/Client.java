package com.alshevskaya.cleaningcompany.entity;

import java.util.StringJoiner;

public class Client extends User {
    private int clientId;
    private String name;
    private String surname;
    private String phone;
    private String address;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_HASH = 31;

    public Client() {
    }

    public Client(String login, String password, UserRole role, int idClient, String name,
                  String surname, String phone, String address) {
        super(login, password, role);
        this.clientId = idClient;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Client(int clientId, String name, String surname, String phone, String address) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Client(int clientId, String name, String surname, String phone, String address, String login) {
        super(login);
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public Client(String name, String surname, String phone, String address, String login) {
        super(login);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

        Client client = (Client) o;

        if (clientId != client.clientId) return false;
        if (name != null ? !name.equals(client.name) : client.name != null) return false;
        if (surname != null ? !surname.equals(client.surname) : client.surname != null) return false;
        if (phone != null ? !phone.equals(client.phone) : client.phone != null) return false;
        return address != null ? address.equals(client.address) : client.address == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = NUMBER_HASH * result + clientId;
        result = NUMBER_HASH * result + (name != null ? name.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (surname != null ? surname.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (phone != null ? phone.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (address != null ? address.hashCode() : NUMBER_ZERO);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                .add("clientId=" + clientId)
                .add("name='" + name + "'")
                .add("surname='" + surname + "'")
                .add("phone='" + phone + "'")
                .add("address='" + address + "'")
                .toString();
    }
}
