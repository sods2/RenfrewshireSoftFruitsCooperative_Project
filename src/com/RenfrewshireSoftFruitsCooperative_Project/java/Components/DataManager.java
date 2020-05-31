package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

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
            System.out.println("Error while processing The inserted Data!\n" +
                    "Please try again!");
        }

        return null;
    }

}
