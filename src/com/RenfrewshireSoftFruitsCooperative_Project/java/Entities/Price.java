package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

import java.util.HashMap;
import java.util.Map;

public class Price {

    private Map<String, Double> price = new HashMap<>();

    public Price() {
    }

    public Price(Map<String, Double> price) {
        this.price = price;
    }

    public Map<String, Double> getPrice() {
        return price;
    }
}
