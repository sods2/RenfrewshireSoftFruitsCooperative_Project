package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;
import com.google.gson.Gson;

import java.util.*;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class DataManager {

    /**
     * Populating objects with the data needed
     * @param obj
     * @param id
     * @return
     */
    public Data processData(Object obj, String id){

        Data data = new Data();

        try {

            data.getData().put(id, obj);

            return data;

        } catch (Exception e) {
            displayString("Error while processing the inserted Data!\n" +
                    "Please try again!");
        }

        return null;
    }

    public List<Batch> processBatchData(Data data){

        List<Batch> batchList = new ArrayList<>();
        Batch batch;
        Gson gson = new Gson();
        String json;

        try {

            //deserialize json
            for(Map.Entry<String, Object> entry : data.getData().entrySet()) {
                json = gson.toJson(entry.getValue());
                batch = gson.fromJson(json, Batch.class);
                batchList.add(batch);
            }

            return batchList;

        } catch (Exception e) {
            displayString("Error while processing the batch Data!\n" +
                    "Please try again!");
        }

        return null;
    }

//    /**
//     * Getting Pricing or Price Obj from (depending on the class is passed through)
//     * @param data data should be represented by Pricing
//     * @param aClass this is the class of the element we need to get back
//     * @return specified element
//     */
//    public List<?> processPricingData(Data data, Class<?> aClass){//TODO: need to do this & Update Test
//
//        List<Pricing> pricingList = new ArrayList<>();
//        List<Price> priceList = new ArrayList<>();
//        Pricing pricing;
//        Price price;
//        Gson gson = new Gson();
//        String PricingJson;
//        String PriceJson;
//
//        try {
//
//            //deserialize json
//            for(Map.Entry<String, Object> pricingEntry : data.getData().entrySet()) {
//                PricingJson = gson.toJson(pricingEntry.getValue());
//                pricing = gson.fromJson(PricingJson, Pricing.class);
//                pricingList.add(pricing);
//
//                for(Map.Entry<String, Price> priceEntry : pricing.getPricingList().entrySet()) {
//                    PriceJson = gson.toJson(priceEntry.getValue());
//                    price = gson.fromJson(PriceJson, Price.class);
//                    priceList.add(price);
//                }
//            }
//
//            if (aClass==Pricing.class) {
//                return pricingList;
//            } else if (aClass==Price.class){
//                return priceList;
//            }
//
//        } catch (Exception e) {
//            displayString("Error while processing the batch Data!\n" +
//                    "Please try again!");
//        }
//
//        return null;
//    }

}
