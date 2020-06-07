package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.HashMap;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.validation;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * I am using this Class to create the Console UI for the Creation of a New Batch
 */
public class CreateNewBatch {

    Console console;

    BatchManager batchManager = new BatchManager();
    FruitManager fruitManager = new FruitManager();

    Batch batch;
    Data data;

    String date = new DateManager().getDate();
    String farmN;
    String fruitType = "";
    String weight = "0";

    public boolean create(Console console){

        this.console= console;

        try {

            displayString("    ---    Create New Batch     ---");
            displayString("");

            //Getting Today's Date
            displayString("    Date: " + date);
            displayString("");

            //Insert Farm Number
            insertFarmN();

            //Insert a fruit type
            insertFruitType();

            //Insert batch weight
            insertBatchWeight();

            //Check
            CreateFile_Validation();

            //Go back to main menu
            displayString("");
            console.displayMainMenu();

        } catch (Exception e) {
            displayString("Error creating the new Batch\n" +
                    "Please try again.");
        }

        return false;
    }

    //Insert Farm Number
    private void insertFarmN(){
        displayString("    Enter Farm N.   (eg. 001 to 999)");
        while ((farmN = batchManager.checkFarmN(console.getInput())).isEmpty());
    }

    //Insert a fruit type
    private void insertFruitType(){
        displayString("    Select a fruit type:");
        displayString("        1. Strawberries");
        displayString("        2. Raspberries");
        displayString("        3. Blackberries");
        displayString("        4. Gooseberries");
        int fruitLoop = 0;
        while ("".equalsIgnoreCase(fruitType = fruitManager.getFruitCodesByID(fruitType))){
            if(fruitLoop>0){
                displayString("    Fruit not in the list, please try again.");
            }
            fruitType = console.getInput();
            fruitLoop++;
        }
    }

    private void insertBatchWeight(){
        displayString("    Enter batch weight in KG’s (N.B. Max weight per batch is 100Kg).");
        //keep asking weight until is write ( > 0 & <= 100 )
        while ((weight = batchManager.checkBatchWeight(console.getInput())).isEmpty());
    }

    //Create File Validation
    private void CreateFile_Validation(){
        displayString("    This batch contains " + weight +
                "KG of " + fruitManager.getFruitNameByCode(fruitType) +
                " From Farm-Number: " + farmN +
                " received on: " + date);

        if (validation()){
            batch = new Batch(Integer.parseInt(weight), farmN, fruitType, new HashMap<>());
            data = new DataManager().processData(batch, batch.getId());
            console.createNewJSON(PathFile.BATCH.toString() + "/" + batch.getId(), data);
        } else {
            displayString("The file was not created!");
        }
    }

}
