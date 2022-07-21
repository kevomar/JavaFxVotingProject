package com.group15.voting;

import javafx.beans.property.SimpleStringProperty;

public class Users {
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty id;
    private SimpleStringProperty status;

    public Users(String firstname, String lastname, String id, String status) {
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.id = new SimpleStringProperty(id);
        this.status = new SimpleStringProperty(status);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public String getId() {
        return id.get();
    }

    public String getStatus() {
        return status.get();
    }

    public void setFirstname(String firstname) {
        this.firstname = new SimpleStringProperty(firstname);
    }

    public void setLastname(String lastname) {
        this.lastname = new SimpleStringProperty(lastname);
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public void setStatus(String status) {
        this.status = new SimpleStringProperty(status);
    }
}
