package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.HashMap;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.validation;

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

            System.out.println("    ---    Create New Batch     ---");
            System.out.println();

            //Getting Today's Date
            System.out.println("    Date: " + date);
            System.out.println();

            //Insert Farm Number
            insertFarmN();

            //Insert a fruit type
            insertFruitType();

            //Insert batch weight
            insertBatchWeight();

            //Check
            CreateFile_Validation();

            //Go back to main menu
            System.out.println();
            console.displayMainMenu();

        } catch (Exception e) {
            System.out.println("Error creating the new Batch\n" +
                    "Please try again.");
        }

        return false;
    }

    //Insert Farm Number
    private void insertFarmN(){
        System.out.println("    Enter Farm N.   (eg. 001 to 999)");
        while ((farmN = batchManager.checkFarmN(console.getInput())).isEmpty());
    }

    //Insert a fruit type
    private void insertFruitType(){
        System.out.println("    Select a fruit type:");
        System.out.println("        1. Strawberries");
        System.out.println("        2. Raspberries");
        System.out.println("        3. Blackberries");
        System.out.println("        4. Gooseberries");
        int fruitLoop = 0;
        while ("".equalsIgnoreCase(fruitType = fruitManager.getFruitCodesByID(fruitType))){
            if(fruitLoop>0){
                System.out.println("    Fruit not in the list, please try again.");
            }
            fruitType = console.getInput();
            fruitLoop++;
        }
    }

    private void insertBatchWeight(){
        System.out.println("    Enter batch weight in KG’s (N.B. Max weight per batch is 100Kg).");
        //keep asking weight until is write ( > 0 & <= 100 )
        while ((weight = batchManager.checkBatchWeight(console.getInput())).isEmpty());
    }

    //Create File Validation
    private void CreateFile_Validation(){
        System.out.println("    This batch contains " + weight +
                "KG of " + fruitManager.getFruitNameByCode(fruitType) +
                " From Farm-Number: " + farmN +
                " received on: " + date);

        if (validation()){
            batch = new Batch(Integer.parseInt(weight), farmN, fruitType, new HashMap<>());
            data = new DataManager().processData(batch, batch.getId());
            console.createNewJSON(batch.getId(), data);
        } else {
            System.out.println("The file was not created!");
        }
    }

}
