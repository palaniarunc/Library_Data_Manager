package com.example.login_testt;

public class Users {

    String editFirstname, editLastname, editAge, editUsername;

    public Users() {
    }

    public Users(String editFirstname, String editLastname, String editAge, String editUsername) {
        this.editFirstname = editFirstname;
        this.editLastname = editLastname;
        this.editAge = editAge;
        this.editUsername = editUsername;
    }

    public String getEditFirstname() {
        return editFirstname;
    }

    public void setEditFirstname(String editFirstname) {
        this.editFirstname = editFirstname;
    }

    public String getEditLastname() {
        return editLastname;
    }

    public void setEditLastname(String editLastname) {
        this.editLastname = editLastname;
    }

    public String getEditAge() {
        return editAge;
    }

    public void setEditAge(String editAge) {
        this.editAge = editAge;
    }

    public String getEditUsername() {
        return editUsername;
    }

    public void setEditUsername(String editUsername) {
        this.editUsername = editUsername;
    }
}
