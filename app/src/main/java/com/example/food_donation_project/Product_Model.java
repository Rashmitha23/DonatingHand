package com.example.food_donation_project;

import java.io.Serializable;

public class Product_Model implements Serializable {
String name,description,Address,Pin_Code,Area,type,docId,uri;

    public Product_Model() {
    }

    public Product_Model(String name, String description, String address, String pin_Code, String area, String type, String docId, String uri) {
        this.name = name;
        this.description = description;
        Address = address;
        Pin_Code = pin_Code;
        Area = area;
        this.type = type;
        this.docId = docId;
        this.uri = uri;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPin_Code() {
        return Pin_Code;
    }

    public void setPin_Code(String pin_Code) {
        Pin_Code = pin_Code;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
