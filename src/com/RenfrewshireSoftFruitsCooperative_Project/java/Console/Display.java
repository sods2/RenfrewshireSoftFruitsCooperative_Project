package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;

import javax.print.attribute.DocAttributeSet;
import java.util.*;
import java.util.concurrent.atomic.DoubleAccumulator;
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

    /**
     * Displaying batches' info
     */
    public static void displayBatches(List<Batch> batchList){
        displayString("    BATCH ID         TYPE    FARM N.     WEIGHT       DATE        PRICE");

        //printing all batches' information
        if(null!= batchList){
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  "
                    + e.getFruitType() + "  |    "
                    + e.getFarmN() + "    |   "
                    + e.getWeight() + "KG" + "   | "
                    + e.getReceivedDate()
                    //Display Price
                    + " |  £ " + getDisplayPrice(e.getId().substring(0, e.getId().length() - 7), e.getFruitType(), e.getWeight(), e.getGrades())
            ));
        } else {
            displayString("No batch list found!");
        }
    }

    /**
     * Display batch info
     */
    public static void displayBatchesWithGrades(List<Batch> batchList) {
        BatchManager batchManager = new BatchManager();

        displayString("    BATCH ID         TYPE    FARM N.     WEIGHT      DATE");

        if(null!= batchList){
            //Display Batch's info
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  "
                    + e.getFruitType() + "  |    "
                    + e.getFarmN() + "    |   "
                    + e.getWeight() + "KG" + "   | "
                    + e.getReceivedDate() + "\n    "
            ));

            //Display Batch's Grades
            batchList.forEach(e -> {
                SortedMap<String, Double> sortedMap = new TreeMap<>(e.getGrades());
                for(Map.Entry<String, Double> entry : sortedMap.entrySet()) {
                    displayString("    " + entry.getKey() + "    " + entry.getValue() + " = " + batchManager.calculateKg(entry.getValue() , e.getWeight()) + "KG");
                    displayString("");
                }
            });

            displayString("");
        } else {
            displayString("No batch found" );
        }
    }

    /**
     * Displaying inserted Price values by Fruit Type
     * @param pricing
     */
    public static void displayPricing(Pricing pricing){
        FruitManager fruitManager = new FruitManager();

        if(0!=pricing.getPricingList().size()){
            for(Map.Entry<String, Price> price : pricing.getPricingList().entrySet()) {
                SortedMap<String, Double> sortedList = new TreeMap<>(price.getValue().getPrice());
                displayString("       - " + fruitManager.getFruitNameByCode(price.getKey()) + " " + sortedList);
            }
        } else {
            displayString("         [No data Found]");
        }
    }

    private static String getDisplayPrice(String date, String fruitType, Double weight, HashMap<String, Double> grades){//TODO: update Test & check changes with impacted methods in case of refactoring is needed & Refactor logic should be somewhere else
        final String folder = PathFile.PRICING.toString();
        FileManagement fileManagement = new MyJSON();
        DataManager dataManager = new DataManager();

        Data data = null;
        Map<String, Double> priceMapToDisplay = null;

        Double tot= 0.0;
        String kgPerGrade = "";

        try {
            List<String> fileList = fileManagement.getFileList(folder);

            //Getting right pricing Obj for batch
            for (String file : fileList) {
                if (file.contains(date)) {
                    data = (Data) fileManagement.read(folder + "/" + PathFile.PRICING_FILE.toString() + date);
                }
            }

            //Getting the Price Map for the specified fruitType
            if (null != data) {
                priceMapToDisplay = dataManager.processPricingData(data, fruitType);
            }

            //Calculating the total for the batch
            if (null != priceMapToDisplay) {
                //Looping through the Price map
                for (Map.Entry<String, Double> price : priceMapToDisplay.entrySet()) {

                    //Getting how many kg for the specified Grade (Price.getKey represents the Grade)
                    kgPerGrade = new BatchManager().calculateKg(grades.get(price.getKey()), weight);
                    //Adding to the total each price value for each grade amount and relative price
                    tot += price.getValue() * Double.parseDouble(kgPerGrade.replace(",", "."));
                }
            }

            if(!"".equalsIgnoreCase(String.valueOf(tot))){
                return String.format("%.2f", tot);
            } else {
                return "0.00";
            }

        } catch (Exception e) {
//            displayString("Error while displaying Price!");
            return "0.00";
        }
    }

}
