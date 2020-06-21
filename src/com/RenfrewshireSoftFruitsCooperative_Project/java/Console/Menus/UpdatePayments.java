package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.PricingManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;

import java.util.ArrayList;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Using this Class to create the Console UI for updating the Payments
 */
public class UpdatePayments {//TODO: check for refactoring

    Console console;

    FileManagement fileManagement = new MyFile();
    DateManager dateManager = new DateManager();
    PricingManager pricingManager = new PricingManager();
    FruitManager fruitManager = new FruitManager();
    DataManager dataManager = new DataManager();

    Price price = new Price();
    List<Price> priceList = new ArrayList<>();
    Pricing pricing = new Pricing();
    Data data = new Data();

    String fruitType;
    Double price_input;

    public boolean updatePayments(Console console) {

        this.console = console;

        try{
            pricingManager.isPricingUpToDate();

            displayString("     Here you can assign a price to every fruit Type");
            displayString("");
            displayString("     System Date: " + dateManager.getDate());
            displayString("");
            //read fruitChoice menu
            fileManagement.read("FruitChoice");

            //Insert a fruit type
            insertFruitType();

            displayString("     Enter prices below (£ per KG) for: " + fruitType);

            //get Price for each category
            getPricing();

            //Preparing Pricing for saving
            savePricing();

            //Saving changes into file
            createFile_Validation();

            displayString("");

            if(!this.console.idle()){
                return false;
            }

            return true;
        } catch (Exception e){
            displayString("Error while grading batch\n");
        }

        return false;
    }

    /**
     * Inserting a fruit type
     */
    private void insertFruitType(){//TODO: move rest into FruitManager
        int fruitLoop = 0;
        while ("".equalsIgnoreCase(fruitType = fruitManager.getFruitCodesByID(fruitType))){
            if(fruitLoop>0){
                displayString("    Fruit not in the list, please try again.");
            }
            fruitType = console.getInput();
            fruitLoop++;
        }
    }

    /**
     * Getting all grades for batch
     */
    private void getPricing(){
        displayString("");
        displayString("     Enter price for GRADE A:");

        //getting price for grade A
        while(!getPrice());
        price.getPrice().put(FruitGrade.GRADE_A.toString(), price_input);

        displayString("");
        displayString("     Enter price for GRADE B:");

        //getting price for grade B
        while(!getPrice());
        price.getPrice().put(FruitGrade.GRADE_B.toString(), price_input);

        displayString("");
        displayString("     Enter price for GRADE C:");

        //getting price for grade C
        while(!getPrice());
        price.getPrice().put(FruitGrade.GRADE_C.toString(), price_input);
        priceList.add(price);
    }

    /**
     * Getting grade
     * @return true if grade value is valid
     */
    private boolean getPrice(){
        try {

            return pricingManager.isPriceValid(String.valueOf(price_input = Double.parseDouble(this.console.getInput_Double())));

        } catch (Exception e){
            displayString("Price format is incorrect!\n" +
                    "Example (0 or 50 or 25,5)");
            return false;
        }
    }

    /**
     * Populating Pricing
     */
    private void savePricing(){

        pricing.getPricingList().put(fruitType, priceList);
    }

    /**
     * Creating File Validation
     */
    private void createFile_Validation(){

        if (console.validate_Input()){
            //formatting data
            data = dataManager.processData(pricing, dateManager.getDateForID());
            //writing file
            console.createNewJSON(PathFile.PRICING.toString() + "/" + PathFile.PRICING_FILE.toString() + dateManager.getDateForID(), data);
        } else {
            displayString("The file was not created!");
        }
    }

}
