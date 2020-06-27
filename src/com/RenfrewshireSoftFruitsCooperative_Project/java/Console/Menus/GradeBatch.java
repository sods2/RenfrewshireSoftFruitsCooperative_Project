package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Using this Class to create the Console UI for grading a specified batch
 */
public class GradeBatch {

    Console console;

    private final String folder = PathFile.BATCH.toString();
    String filename;

    BatchManager batchManager = new BatchManager();

    Batch batch;
    List<Batch> batchList = new ArrayList<>();

    //Storing batch information
    private String id;
    private String receivedDate;
    private Double weight;
    private String farmN;
    private String fruitType;
    HashMap<String, Double> grades = new HashMap<>();

    double grade;

    /**
     * Grading a specified batch
     * @param console
     * @return return true if the operation ended successfully
     */
    public boolean gradeBatch(Console console) {

        this.console= console;

        try{

            displayString("     Please enter Batch number:");

            //getting batch ID
            while (!(filename = this.console.getInput()).matches("\\d{6}[-]\\w{2}[-]\\d{3}")){
                displayString("Batch: " + filename + " Number is incorrect!\n" +
                        "(Example: 210321-ST-005)");
            }

            //Getting batch object
            batchList = batchManager.getBatchObj(folder, filename);

            //printing batch's information
            displayString("");
            displayBatch();

            //Grading Batch
            displayString("");
            //Checking that grades add up to 100
            while(!batchManager.gradeVerification(grades)){
                if(!this.console.idle()){
                    return false;
                }
                //Asking for grades
                getBatchGrades();
            }

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
     * Creating File Validation
     */
    private void createFile_Validation(){

        if (console.validate_Input()){
            //Creating a batch with new grades information
            batch = new Batch(id, receivedDate, weight, farmN, fruitType, grades);
            //formatting data
            Data data = new DataManager().processData(batch, batch.getId());
            //writing file
            console.createNewJSON(PathFile.BATCH.toString() + "/" + batch.getId(), data);
        } else {
            displayString("The file was not created!");
        }
    }

    /**
     * Display batch info
     */
    private void displayBatch() {
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
    }

    /**
     * Getting all grades for batch
     */
    private void getBatchGrades(){
        displayString("     Enter percentage of GRADE A Fruit in batch:");

        while(!getGrade());
        grades.put(FruitGrade.GRADE_A.toString() , grade);

        displayString("");
        displayString("     Enter percentage of GRADE B Fruit in batch:");

        while(!getGrade());
        grades.put(FruitGrade.GRADE_B.toString() , grade);

        displayString("");
        displayString("     Enter percentage of GRADE C Fruit in batch:");

        while(!getGrade());
        grades.put(FruitGrade.GRADE_C.toString() , grade);

        displayString("");
        displayString("     Enter percentage of REJECTED Fruit in batch:");

        while(!getGrade());
        grades.put(FruitGrade.REJECTED.toString() , grade);
    }

    /**
     * Getting grade
     * @return true if grade value is valid
     */
    private boolean getGrade(){
        try {

            return batchManager.isGradeValid(String.valueOf(grade = Double.parseDouble(this.console.getInput_Double())));

        } catch (Exception e){
            displayString("Grade format is incorrect!\n" +
                    "Example (0 or 50 or 25,5)");
            return false;
        }
    }

}
