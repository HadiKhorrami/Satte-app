package com.example.satte.db.tables;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(id = true)
    private long userid;

    @DatabaseField
    private String emailaddress;

    @DatabaseField
    private String firstname;

    @DatabaseField
    private String lastname;

    @DatabaseField
    private String fullname;

    @DatabaseField
    private boolean lockout;

    @DatabaseField
    private long mobilenumber;

    @DatabaseField
    private String password;

    @DatabaseField
    private boolean passwordencrypted;

    @DatabaseField
    private String screenname;

    @DatabaseField
    private long status;

    public User() {
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isLockout() {
        return lockout;
    }

    public void setLockout(boolean lockout) {
        this.lockout = lockout;
    }

    public long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPasswordencrypted() {
        return passwordencrypted;
    }

    public void setPasswordencrypted(boolean passwordencrypted) {
        this.passwordencrypted = passwordencrypted;
    }

    public String getScreenname() {
        return screenname;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
