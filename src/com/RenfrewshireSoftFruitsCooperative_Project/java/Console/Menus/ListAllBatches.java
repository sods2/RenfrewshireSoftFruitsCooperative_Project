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
 * Using this Class to create the Console UI for listing all batches
 *
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class ListAllBatches {

    Console console;

    private final String folder = PathFile.BATCH.toString();

    private FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();

    List<Batch> batchList = new ArrayList<>();

    /**
     * Displaying list of batches
     * @param console
     * @return return true if the operation ended successfully
     */
    public boolean listAllBatches(Console console) {

        this.console= console;

        try {

            //getting list of file names
            List<String> files = fileManagement.getFileList(folder);

            //getting data from all files
            Data data = fileManagement.readAll(folder, files);

            //getting batches' list
            if(null!= data) {
                batchList = dataManager.processBatchData(data);
            } else {
                displayString("No batch data found!");
            }

            //Displaying batches' info
            displayBatches();

            displayString("");

            if(!this.console.idle()){
                return false;
            }

            return true;

        } catch (Exception e){
            displayString("Error reading files!\n" +
                    "Please try again!");
        }

        return false;
    }

    /**
     * Displaying batches' info
     */
    private void displayBatches(){
        displayString("    BATCH ID         TYPE    FARM N.     WEIGHT      DATE");

        //printing all batches' information
        if(null!= batchList){
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  "
                    + e.getFruitType() + "  |    "
                    + e.getFarmN() + "    |   "
                    + e.getWeight() + "KG" + "   | "
                    + e.getReceivedDate()));
        } else {
            displayString("No batch list found!");
        }
    }

}
