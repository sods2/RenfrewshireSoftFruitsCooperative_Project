package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.*;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.*;

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
            displayBatchesWithGrades(batchList);

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

}
