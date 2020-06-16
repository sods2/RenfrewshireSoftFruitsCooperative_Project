package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.isNumeric;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Using this Class to create the Console UI for grading a specified batch
 */
public class GradeBatch {

    Console console;

    private final String folder = PathFile.BATCH.toString();

    private FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();

    Data data;
    Batch batch;

    //Storing batch information
    private String id;
    private String receivedDate;
    private int weight;
    private String farmN;
    private String fruitType;
    HashMap<String, Integer> grades = new HashMap<>();

    /**
     * Grading a specified batch
     * @param console
     * @return return true if the operation ended successfully
     */
    public boolean gradeBatch(Console console) {

        this.console= console;

        List<Batch> batchList = new ArrayList<>();

        String grade;

        try{

            displayString("Please enter Batch number:");

            String filename = this.console.getInput();

            //Getting batch object
            data = (Data) fileManagement.read(folder + "/" + filename);

            if(null!=data){
                batchList = dataManager.processBatchData(data);
            } else {
                displayString("No batch data found!");
            }

            //printing batch's information
            displayString("");
            displayString("    BATCH ID         TYPE    FARM N.     WEIGHT      DATE");

            if(null!= batchList){
                batchList.forEach(e -> displayString("  " + (id = e.getId()) + "    |  "
                        + (fruitType = e.getFruitType()) + "  |    "
                        + (farmN = e.getFarmN()) + "    |   "
                        + (weight = e.getWeight()) + "KG" + "   | "
                        + (receivedDate = e.getReceivedDate())));
            } else {
                displayString("No batch found with ID: " + filename);
            }

            //Asking for grades
            displayString("");
            displayString("Enter percentage of GRADE A Fruit in batch:");

            while((grade = isNumeric(this.console.getInput())).isEmpty()){
                displayString("Please Enter a number (only digits allowed 1 to 9)");
            }
            grades.put(FruitGrade.GRADE_A.toString() , Integer.parseInt(grade));

            displayString("");
            displayString("Enter percentage of GRADE B Fruit in batch:");

            while((grade = isNumeric(this.console.getInput())).isEmpty()){
                displayString("Please Enter a number (only digits allowed 1 to 9)");
            }
            grades.put(FruitGrade.GRADE_B.toString() , Integer.parseInt(grade));

            displayString("");
            displayString("Enter percentage of GRADE C Fruit in batch:");

            while((grade = isNumeric(this.console.getInput())).isEmpty()){
                displayString("Please Enter a number (only digits allowed 1 to 9)");
            }
            grades.put(FruitGrade.GRADE_C.toString() , Integer.parseInt(grade));

            displayString("");
            displayString("Enter percentage of REJECTED Fruit in batch:");

            while((grade = isNumeric(this.console.getInput())).isEmpty()){
                displayString("Please Enter a number (only digits allowed 1 to 9)");
            }
            grades.put(FruitGrade.REJECTED.toString() , Integer.parseInt(grade));

            //Saving changes into file
            createFile_Validation();

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
     * Creating File Validation
     */
    private void createFile_Validation(){

        if (console.validate_Input()){
            //Creating a batch with new grades information
            batch = new Batch(id, receivedDate, weight, farmN, fruitType, grades);
            //formatting data
            data = new DataManager().processData(batch, batch.getId());
            //writing file
            console.createNewJSON(PathFile.BATCH.toString() + "/" + batch.getId(), data);
        } else {
            displayString("The file was not created!");
        }
    }
}
