package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.HashMap;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * All operation fo batch are executed on this class
 *
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class BatchManager {

    /**
     * Checking batch weight
     *
     * @param weight batch's weight
     * @return false if weight min and max is exceeded
     */
    public String checkBatchWeight(String weight) {

        try {

            if (Integer.parseInt(weight) <= 100 && Integer.parseInt(weight) > 0) {
                return weight;
            } else {
                displayString("    The Weight inserted is invalid.\n" +
                        "   (eg. > 0 to <= 100)");
            }

        } catch (Exception e) {
            displayString("    The Weight inserted is invalid.\n" +
                    "   (eg. > 0 to <= 100)");
        }
        return "";
    }

    /**
     * Checking the farm's number format
     * @param num farm's number
     * @return farm number with right format or empty string when format is wrong
     */
    public String checkFarmN(String num) {
        try {
            if (0<Integer.parseInt(num)){

                return String.format("%03d", Integer.valueOf(num));
            }
        } catch (Exception e) {
            displayString("Farm number is incorrect!\n" +
                    "The right format is 001 to 999\n" +
                    "Try entering the number again.");
            return "";
        }
        return "";
    }

    public boolean isGradeValid(String grade){
        if (grade.matches("[0-9]*[.]?[0-9]")) {
            //Checking value format
            return true;
        }
            else {
                displayString("Grade: (" + grade + ") format is incorrect!\n" +
                        "Example (0, 50, 25.5)");
        }
        return false;
    }

    /**
     * Calculating how many kg a percentage represents
     * @param percent percentage
     * @param tot total number to consider
     * @return result of percentage to kg
     */
    public String calculateKg(double percent, double tot){

        return String.format("%.3f", (tot * (percent / 100)));
    }

    /**
     * Checking Grades' sum
     * @param grades
     * @return return true if sum is equal to 100
     */
    public boolean gradeVerification(HashMap<String, Double> grades){

        if (0!=grades.size()){
            //getting sum
            double grade = grades.values().stream().mapToDouble(g -> g).sum();

            if (grade != 100) {
                displayString("Grades' total must be 100%!\n" +
                        grade + "% was entered.\n");
            }

            //verify sum adds up to 100
            return grade == 100;
        }

        return false;
    }

    /**
     * Getting batch Obj
     * @return batchList
     */
    public List<Batch> getBatchObj(String folder, String filename) {
        FileManagement fileManagement = new MyJSON();
        DataManager dataManager = new DataManager();
        List<Batch> batchList;

        Data data = (Data) fileManagement.read(folder + "/" + filename);

        if(null!=data.getData()){
            batchList = dataManager.processBatchData(data);
            return batchList;
        } else {
            displayString("No batch data found!");
        }
        return null;
    }
}
