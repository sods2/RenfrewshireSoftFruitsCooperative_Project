package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.IDGenerator;

import java.util.HashMap;

public class Batch {

    private String id;

    private String receivedDate;

    private Double weight;

    private String farmN;

    private String fruitType;

    HashMap<String, Double> grades = new HashMap<>();

    public Batch() {}

    /**
     * Using this constructor to initialise all Attributes of the Batch Obj
     * @param weight
     * @param farmN
     * @param fruitType
     * @param grades
     */
    public Batch(Double weight, String farmN, String fruitType, HashMap<String, Double> grades) {
        this.weight = weight;
        this.farmN = farmN;
        this.fruitType = fruitType;
        this.grades = grades;
        setId();
        setReceivedDate();
    }

    /**
     * Using this to create a new Batch (For when a copy of existing one is needed)
     * @param id
     * @param receivedDate
     * @param weight
     * @param farmN
     * @param fruitType
     * @param grades
     */
    public Batch(String id, String receivedDate, Double weight, String farmN, String fruitType, HashMap<String, Double> grades) {
        this.id = id;
        this.receivedDate = receivedDate;
        this.weight = weight;
        this.farmN = farmN;
        this.fruitType = fruitType;
        this.grades = grades;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
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

    public HashMap<String, Double> getGrades() {
        return grades;
    }

    public void setGrades(HashMap<String, Double> grades) {
        grades = grades;
    }

}
