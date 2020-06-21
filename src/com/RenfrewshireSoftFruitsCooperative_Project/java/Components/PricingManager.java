package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class PricingManager {//TODO: Test

    final private String folder = "Pricing";

    FileManagement fileManagement = new MyJSON();
    DateManager dateManager = new DateManager();

    List<String> filenameList;

    public boolean isPricingUpToDate(){

        if (!isTodaysFilePresent()){
            return false;
        }
        return true;
    }

    private boolean isTodaysFilePresent(){

        filenameList = fileManagement.getFileList(folder);
        String today = dateManager.getDateForID();

        for(String filename : filenameList) {

            if(filename.contains(today)){
                return true;
            }
        }

        return false;
    }

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

//    /**
//     * Inserting a fruit type
//     */
//    private void insertFruitType(){//TODO: refactoring
//        int fruitLoop = 0;
//        while ("".equalsIgnoreCase(fruitType = fruitManager.getFruitCodesByID(fruitType))){
//            if(fruitLoop>0){
//                displayString("    Fruit not in the list, please try again.");
//            }
//            fruitType = console.getInput();
//            fruitLoop++;
//        }
//    }

}