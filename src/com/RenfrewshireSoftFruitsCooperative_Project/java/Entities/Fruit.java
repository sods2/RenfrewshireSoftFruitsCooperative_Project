package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

public abstract class Fruit {

    private String ID;

    private String name;

    private double price;

    private String code;

    public Fruit(String ID, String name, double price, String code) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
