package com.RenfrewshireSoftFruitsCooperative_Project.java.Entities;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class Pricing {

    //map id is fruit type
    private Map<String, Price> pricingList = new HashMap<>();

    public Pricing() {
    }

    public Pricing(Map<String, Price> pricingList) {
        this.pricingList = pricingList;
    }

    public Map<String, Price> getPricingList() {
        return pricingList;
    }

}
