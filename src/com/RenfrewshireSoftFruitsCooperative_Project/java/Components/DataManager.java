package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;

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
            displayString("Error while processing The inserted Data!\n" +
                    "Please try again!");
        }

        return null;
    }

}
