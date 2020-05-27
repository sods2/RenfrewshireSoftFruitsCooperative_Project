package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

import java.util.HashMap;

public class Batch {

    private String id;

    private String recievedDate;

    private int weight;

    HashMap<String, Integer> Grades = new HashMap<>();

    public Batch() {
    }

    public Batch(String id, String recievedDate, int weight) {
        this.id = id;
        this.recievedDate = recievedDate;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(String recievedDate) {
        this.recievedDate = recievedDate;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public HashMap<String, Integer> getGrades() {
        return Grades;
    }

    public void setGrades(HashMap<String, Integer> grades) {
        Grades = grades;
    }
}
