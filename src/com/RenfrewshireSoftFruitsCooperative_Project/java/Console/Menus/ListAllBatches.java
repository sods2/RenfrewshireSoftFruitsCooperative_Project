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
 *
 */
public class ListAllBatches {

    Console console;

    private final String folder = PathFile.BATCH.toString();

    private FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();
    private List<String> files;
    private Data data;
    private List<Batch> batchList = new ArrayList<>();

    /**
     * Displaying list of batches
     */
    public boolean listAllBatches(Console console) {//TODO: once done with grades need to add it to the display

        this.console= console;

        try {

            //getting list of file names
            files = fileManagement.getFileList(folder);

            //getting data from all files
            data = fileManagement.readAll(folder, files);

            //getting batches's list
            batchList = dataManager.processBatchData(data);

            displayString("    BATCH ID         TYPE    FARM N.     WEIGHT      DATE");

            //printing all batches' information
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  " + e.getFruitType() + "  |    " + e.getFarmN() + "    |   " + e.getWeight() + "KG" + "   | " + e.getReceivedDate()));

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

}
