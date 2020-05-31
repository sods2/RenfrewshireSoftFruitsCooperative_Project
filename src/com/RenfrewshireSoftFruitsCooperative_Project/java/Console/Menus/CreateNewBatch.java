package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.HashMap;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.validation;

public class CreateNewBatch {

    public boolean create(Console console){

        BatchManager batchManager = new BatchManager();
        Batch batch;
        Data data;

        String date = new DateManager().getDate();
        String farmN;
        String fruitType;
        String weight;

        try {

            System.out.println("    ---    Create New Batch     ---");
            System.out.println();

            //Getting Today's Date
            System.out.println("    Date: " + date);
            System.out.println();

            //Insert Farm Number
            System.out.println("    Enter Farm N.   (eg. 001 to 999)");
            farmN = batchManager.checkFarmN(console.getInput());

            //Insert a fruit type
            System.out.println("    Select a fruit type:");
            System.out.println("        1. Strawberries");
            System.out.println("        2. Raspberries");
            System.out.println("        3. Blackberries");
            System.out.println("        4. Gooseberries");
            fruitType = console.getInput();//TODO: create a way to distinguish between fruit types

            //Insert batch weight
            System.out.println("    Enter batch weight in KG’s (N.B. Max weight per batch is 100Kg).");
            weight = console.getInput();//TODO: add loop for checking

            //Check
            System.out.println("    This batch contains " +
                    weight + "KG of STRAWBERRIES FROM " +
                    "FARM NUMBER " + farmN +
                    " RECEIVED ON " + date);

            if (validation()){
                batch = new Batch(Integer.parseInt(weight), farmN, fruitType, new HashMap<>());
                data = new DataManager().processData(batch, batch.getId());
                System.out.println(console.createNewJSON(batch.getId(), data));
            }

            console.getCommand();

        } catch (Exception e) {
            System.out.println("Error creating the new Batch\n" +
                    "Please try again.");
        }


        return false;
    }

}
