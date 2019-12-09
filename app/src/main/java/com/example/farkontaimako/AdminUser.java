package com.example.farkontaimako;

public class AdminUser {
    String edFname, edLName, edPhone, edPass, edMail, edHealthFac;

    public AdminUser(String edFname, String edLName, String edPhone, String edMail, String edHealthFac) {
        this.edFname = edFname;
        this.edLName = edLName;
        this.edPhone = edPhone;
        this.edMail = edMail;
        this.edHealthFac = edHealthFac;
    }

    public String getEdMail() {
        return edMail;
    }

    public void setEdMail(String edMail) {
        this.edMail = edMail;
    }

    public AdminUser() {
    }

    public String getEdFname() {
        return edFname;
    }

    public void setEdFname(String edFname) {
        this.edFname = edFname;
    }

    public String getEdLName() {
        return edLName;
    }

    public void setEdLName(String edLName) {
        this.edLName = edLName;
    }

    public String getEdPhone() {
        return edPhone;
    }

    public void setEdPhone(String edPhone) {
        this.edPhone = edPhone;
    }

    public String getEdPass() {
        return edPass;
    }

    public void setEdPass(String edPass) {
        this.edPass = edPass;
    }

    public String getEdHealthFac() {
        return edHealthFac;
    }

    public void setEdHealthFac(String edHealthFac) {
        this.edHealthFac = edHealthFac;
    }
}
