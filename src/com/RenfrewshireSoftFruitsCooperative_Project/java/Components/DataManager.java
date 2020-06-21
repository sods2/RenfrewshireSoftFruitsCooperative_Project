package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
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

    public List<Batch> processPricingData(Data data){//TODO: need to do this

        List<Batch> batchList = new ArrayList<>();
        Batch batch;
        Gson gson = new Gson();
        String json;

        try {

            //deserialize json
            for(Map.Entry<String, Object> entry : data.getData().entrySet()) {//TODO: I will probably need two add another loop inside to convert the price.class from the list
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

}
