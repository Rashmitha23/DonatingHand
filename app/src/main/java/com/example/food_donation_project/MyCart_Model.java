package com.example.food_donation_project;

import java.io.Serializable;

public class MyCart_Model implements Serializable{
    String Name,Date,Time;
    String City,email,landmark,locality,mobilenumber,pincode,state,ulternative,username;
    String documentid;
    int Price;
int Quantity;

    public MyCart_Model() {
    }

    public MyCart_Model(String name, String date, String time, String city, String email, String landmark, String locality, String mobilenumber, String pincode, String state, String ulternative, String username, String documentid, int price, int quantity) {
        Name = name;
        Date = date;
        Time = time;
        City = city;
        this.email = email;
        this.landmark = landmark;
        this.locality = locality;
        this.mobilenumber = mobilenumber;
        this.pincode = pincode;
        this.state = state;
        this.ulternative = ulternative;
        this.username = username;
        this.documentid = documentid;
        Price = price;
        Quantity = quantity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUlternative() {
        return ulternative;
    }

    public void setUlternative(String ulternative) {
        this.ulternative = ulternative;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}