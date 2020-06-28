package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class PricingManager {

    FileManagement fileManagement = new MyJSON();
    DateManager dateManager = new DateManager();

    List<String> filenameList;

    public boolean isPricingUpToDate(String folder){

        if (!isTodaysFilePresent(folder)){
            return false;
        }
        return true;
    }

    private boolean isTodaysFilePresent(String folder){

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

}