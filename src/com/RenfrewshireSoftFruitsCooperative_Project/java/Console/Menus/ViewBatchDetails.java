package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.ArrayList;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Using this Class to display, to the Console UI, details about a specified batch
 */
public class ViewBatchDetails {

    Console console;

    private final String folder = PathFile.BATCH.toString();
    String filename;

    private FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();

    Data data;
    List<Batch> batchList = new ArrayList<>();

    public boolean displayBatchDetails(Console console){

        this.console= console;

        try{

            displayString("     Please enter Batch number:");

            //getting batch ID
            filename = this.console.getInput();

            //Getting batch object
            getBatchObj();

            //printing batch's information
            displayString("");
            displayBatch();

            displayString("");

            if(!this.console.idle()){
                return false;
            }

            return true;
        } catch (Exception e){
            displayString("Error while grading batch\n" +
                    "Please try again!");
        }
        return false;
    }

    /**
     * Getting batch Obj
     */
    private void getBatchObj() {
        data = (Data) fileManagement.read(folder + "/" + filename);

        if(null!=data){
            batchList = dataManager.processBatchData(data);
        } else {
            displayString("No batch data found!");
        }
    }

    /**
     * Display batch info
     */
    private void displayBatch() {
        displayString("    BATCH ID         TYPE    FARM N.     WEIGHT      DATE");

        if(null!= batchList){
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  "
                    + e.getFruitType() + "  |    "
                    + e.getFarmN() + "    |   "
                    + e.getWeight() + "KG" + "   | "
                    + e.getReceivedDate() + "\n    "

                    + e.getGrades()
            ));
        } else {
            displayString("No batch found with ID: " + filename);
        }
    }

}
