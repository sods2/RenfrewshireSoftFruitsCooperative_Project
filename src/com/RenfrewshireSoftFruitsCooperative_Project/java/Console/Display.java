package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.List;
import java.util.stream.Stream;

/**
 * Displaying data to the console
 */
public class Display {

    private Display(){}

    /**
     * getting a String of data and Displaying it to the Console
     * @param data Data to be displayed
     */
    public static void displayString(String data){
        //don't really need the try, I am using it just for security in case something goes wrong
        try {
            if(!data.isEmpty()){
                System.out.println(data);
            } else {
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Data not Found!");
        }
    }

    /**
     * getting a Stream of String type and Displaying it to the Console
     * @param data Data to be displayed
     */
    public static void displayStream(Stream<String> data){
        if(null!=data){
            data.forEach(System.out::println);
        } else {
            displayString("Data not Found!");
        }
    }

//    /**//TODO:finish refactoring
//     * Display batch info
//     */
//    private void displayBatch(List<Batch> batchList, Batch batch) {
//        displayString("    BATCH ID         TYPE    FARM N.     WEIGHT      DATE");
//
//        if(null!= batchList){
//            batchList.forEach(e -> displayString("  " + (id = e.getId()) + "    |  "
//                    + (fruitType = e.getFruitType()) + "  |    "
//                    + (farmN = e.getFarmN()) + "    |   "
//                    + (weight = e.getWeight()) + "KG" + "   | "
//                    + (receivedDate = e.getReceivedDate())));
//        } else {
//            displayString("No batch found with ID: " + filename);
//        }
//    }

}
