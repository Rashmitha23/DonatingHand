package com.example.food_donation_project;

public class Usermodel {
    String username;
    String useremail;
    String useraddress;
    String userpassword;
    String uid;
    int usertype;

    public Usermodel() {
    }

    public Usermodel(String username, String useremail, String useraddress, String userpassword, String uid, int usertype) {
        this.username = username;
        this.useremail = useremail;
        this.useraddress = useraddress;
        this.userpassword = userpassword;
        this.uid = uid;
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
