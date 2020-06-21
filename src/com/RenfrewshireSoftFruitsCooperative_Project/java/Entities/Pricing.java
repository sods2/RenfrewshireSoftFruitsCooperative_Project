package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pricing {

    //map id is fruit type
    private Map<String, List<Price>> pricingList = new HashMap<>();

    public Pricing() {
    }

    public Pricing(Map<String, List<Price>> pricingList) {
        this.pricingList = pricingList;
    }

    public Map<String, List<Price>> getPricingList() {
        return pricingList;
    }

}
