package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
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

    /**
     * Getting Pricing or Price Obj from (depending on the class is passed through)
     * @param data data should be represented by Pricing
     * @param fruitType fruit Type that we need
     * @return specified element
     */
    public Map<String, Double> processPricingData(Data data, String fruitType){//TODO: need to do this & Update Test

        List<Pricing> pricingList = new ArrayList<>();
        Pricing pricing;
        Gson gson = new Gson();
        String PricingJson;

        try {

            //deserialize json
            for(Map.Entry<String, Object> pricingEntry : data.getData().entrySet()) {
                PricingJson = gson.toJson(pricingEntry.getValue());
                pricing = gson.fromJson(PricingJson, Pricing.class);
                pricingList.add(pricing);
            }

            switch (fruitType){
                case "ST": case "RA": case "BL": case "GO":
                    return pricingList.get(0).getPricingList().get(fruitType).getPrice();
            }

        } catch (Exception e) {
            displayString("Error while processing the batch Data!\n" +
                    "Please try again!");
        }

        return null;
    }

    /**
     * Getting Data Obj from specified File
     * @param folder
     * @param date
     * @return
     * @throws Exception
     */
    public Data getDataFromPricingFile(String folder, String date){
        FileManagement fileManagement = new MyJSON();

        List<String> fileList = fileManagement.getFileList(folder);

        //Getting right pricing Obj for batch
        for (String file : fileList) {
            if (file.contains(date)) {
                return  (Data) fileManagement.read(folder + "/" + PathFile.PRICING_FILE.toString() + date);
            }
        }
        return null;
    }

}
