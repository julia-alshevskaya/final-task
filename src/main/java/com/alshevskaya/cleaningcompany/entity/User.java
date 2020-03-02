package com.alshevskaya.cleaningcompany.entity;

import java.util.Objects;
import java.util.StringJoiner;

public class User extends Entity {
    private String login;
    private String password;
    private UserRole role;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_HASH = 31;

    public User() {
    }

    public User(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == this.getClass())) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : NUMBER_ZERO;
        result = NUMBER_HASH * result + (password != null ? password.hashCode() : NUMBER_ZERO);
        result = NUMBER_HASH * result + (role != null ? role.hashCode() : NUMBER_ZERO);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("login='" + login + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .toString();
    }
}

