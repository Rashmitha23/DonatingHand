package com.example.food_donation_project;

public class MyOrderModel {
    String Date;
    String Name;
    int Price;
    int Quantity;
    String Time;

    public MyOrderModel() {
    }

    public MyOrderModel(String date, String name, int price, int quantity, String time) {
        Date = date;
        Name = name;
        Price = price;
        Quantity = quantity;
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
