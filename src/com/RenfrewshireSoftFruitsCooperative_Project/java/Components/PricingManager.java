package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import java.util.List;
import java.util.Map;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class PricingManager {

    FileManagement fileManagement = new MyJSON();
    DateManager dateManager = new DateManager();

    List<String> filenameList;

    /**
     * Checking if Pricing is Up To Date
     * @param folder
     * @return true if condition is met
     */
    public boolean isPricingUpToDate(String folder){

        if (!isTodaysFilePresent(folder)){
            return false;
        }
        return true;
    }

    /**
     * Checking if a File named with today's date is present in specified folder
     * @param folder name
     * @return true if condition is met
     */
    private boolean isTodaysFilePresent(String folder){

        //Getting filename List
        filenameList = fileManagement.getFileList(folder);
        String today = dateManager.getDateForID();

        for(String filename : filenameList) {

            if(filename.contains(today)){
                return true;
            }
        }

        return false;
    }

    /**
     * checking if price format is valid
     * @param price
     * @return true if condition is met
     */
    public boolean isPriceValid(String price){
        if (price.matches("[0-9]*[.]?[0-9]?[0-9]")) {
            //Checking value format
            return true;
        }
        else {
            displayString("Price: (" + price + ") format is incorrect!\n" +
                    "Example (0 - 50 - 25.5)");
        }
        return false;
    }

    /**
     * Getting Price Map
     * @param data
     * @param fruitType
     * @return Price Map
     */
    public Map<String, Double> getPriceMap(Data data, String fruitType){
        DataManager dataManager = new DataManager();

        return dataManager.processPricingData(data, fruitType);

    }

}
