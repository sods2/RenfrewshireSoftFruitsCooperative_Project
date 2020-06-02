package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.IDGenerator;

import java.util.HashMap;

public class Batch {

    private String id;

    private String receivedDate;

    private int weight;

    private String farmN;

    private String fruitType;

    HashMap<String, Integer> Grades = new HashMap<>();

    public Batch() {}

    /**
     * Using this constructor to initialise all Attributes of the Batch Obj
     * @param weight
     * @param farmN
     * @param fruitType
     * @param grades
     */
    public Batch(int weight, String farmN, String fruitType, HashMap<String, Integer> grades) {
        this.weight = weight;
        this.farmN = farmN;
        this.fruitType = fruitType;
        this.Grades = grades;
        setId();
        setReceivedDate();
    }

    public String getId() {
        return id;
    }

    public void setId() {
        IDGenerator idGenerator = new IDGenerator(this.fruitType,this.farmN);
        this.id = idGenerator.createID();
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate() {
        DateManager dateManager = new DateManager();
        this.receivedDate = dateManager.getDate();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getFarmN() {
        return farmN;
    }

    public void setFarmN(String farmN) {
        this.farmN = farmN;
    }

    public String getFruitType() {
        return fruitType;
    }

    public void setFruitType(String fruitType) {
        this.fruitType = fruitType;
    }

    public HashMap<String, Integer> getGrades() {
        return Grades;
    }

    public void setGrades(HashMap<String, Integer> grades) {
        Grades = grades;
    }

}
