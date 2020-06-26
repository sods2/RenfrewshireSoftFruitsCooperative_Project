package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * All operation fo batch are executed on this class
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

            if (Double.parseDouble(weight) <= 100 && Double.parseDouble(weight) > 0) {
                return weight;
            } else {
                displayString("The Weight inserted is invalid.\n" +
                        "   (eg. > 0 to <= 100 - valid: 12,5)");
            }

        } catch (Exception e) {
            displayString("The Weight inserted is invalid.\n" +
                    "   (eg. > 0 to <= 100 - valid: 12,5)");
        }
        return "";
    }

    /**
     * Checking the farm's number format
     *
     * @param num farm's number
     * @return farm number with right format or empty string when format is wrong
     */
    public String checkFarmN(String num) {
        try {
            if (0 < Integer.parseInt(num)) {

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

    public boolean isGradeValid(String grade) {
        if (grade.matches("[0-9]*[.]?[0-9]")) {
            //Checking value format
            return true;
        } else {
            displayString("Grade: (" + grade + ") format is incorrect!\n" +
                    "Example (0, 50, 25.5)");
        }
        return false;
    }

    /**
     * Calculating how many kg a percentage represents
     *
     * @param percent percentage
     * @param tot     total number to consider
     * @return result of percentage to kg
     */
    public String calculateKg(double percent, double tot) {

        return String.format("%.3f", (tot * (percent / 100)));
    }

    /**
     * Checking Grades' sum
     *
     * @param grades
     * @return return true if sum is equal to 100
     */
    public boolean gradeVerification(HashMap<String, Double> grades) {

        if (0 != grades.size()) {
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
     *
     * @return batchList
     */
    public List<Batch> getBatchObj(String folder, String filename) {
        FileManagement fileManagement = new MyJSON();
        DataManager dataManager = new DataManager();
        List<Batch> batchList;

        Data data = (Data) fileManagement.read(folder + "/" + filename);

        if (null != data.getData()) {
            batchList = dataManager.processBatchData(data);
            return batchList;
        } else {
            displayString("No batch data found!");
        }
        return null;
    }

    /**
     * Getting Batch Price tot
     *
     * @param date      date in date ID format
     * @param fruitType
     * @param weight
     * @param grades
     * @return batch price tot
     */
    public static String getBatchPrice(String date, String fruitType, Double weight, HashMap<String, Double> grades) {

        Map<String, Double> priceMap;

        Double tot = 0.0;
        String kgPerGrade;

        try {

            //Getting Price Map
            priceMap = priceMapForDisplay(fruitType, date);

            //Calculating the total for the batch
            if (null != priceMap) {
                //Looping through the Price map
                for (Map.Entry<String, Double> price : priceMap.entrySet()) {

                    //Getting how many kg for the specified Grade (Price.getKey represents the Grade)
                    kgPerGrade = new BatchManager().calculateKg(grades.get(price.getKey()), weight);
                    //Adding to the total each price value for each grade amount and relative price
                    tot += price.getValue() * Double.parseDouble(kgPerGrade.replace(",", "."));
                }
            }

            if (!"".equalsIgnoreCase(String.valueOf(tot))) {
                return String.format("%.2f", tot);
            } else {
                return "0,00";
            }

        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * Getting Grade Price tot
     *
     * @param date      date in date ID format
     * @param fruitType
     * @param weight
     * @param grade
     * @return batch price tot
     */
    public static String getGradePrice(String date, String fruitType, Double weight, Map<String, Double> grades, String grade) {

        Map<String, Double> priceMap;

        Double gradePrice;
        String kgPerGrade;

        try {

            //Getting Price Map
            priceMap = priceMapForDisplay(fruitType, date);

            //Calculating the total for the batch
            if (null != priceMap) {
                //Looping through the Price map
                for (Map.Entry<String, Double> price : priceMap.entrySet()) {

                    //Getting how many kg for the specified Grade (Price.getKey represents the Grade)
                    kgPerGrade = new BatchManager().calculateKg(grades.get(price.getKey()), weight);
                    //Adding to the total each price value for each grade amount and relative price
                    if (grade.equalsIgnoreCase(price.getKey())) {
                        gradePrice = price.getValue() * Double.parseDouble(kgPerGrade.replace(",", "."));
                        if (!"".equalsIgnoreCase(String.valueOf(gradePrice))) {
                            return String.format("%.2f", gradePrice);
                        }
                    }
                }
            }

            return "0.00";
        } catch (Exception e) {
            return "0.00";
        }

    }

    /**
     * Getting Grade Price tot
     *
     * @param date      date in date ID format
     * @param fruitType
     * @param weight
     * @param grade
     * @return batch price tot
     */
    public static String getTransactionReportPrice(String date, String fruitType, Double weight, Map<String, Double> grades, String grade) {//TODO: modify this to work with a specific grade and fruit type

        Map<String, Double> priceMap;

        Double gradePrice;
        String kgPerGrade;

        try {

            //Getting Price Map
            priceMap = priceMapForDisplay(fruitType, date);

            //Calculating the total for the batch
            if (null != priceMap) {
                //Looping through the Price map
                for (Map.Entry<String, Double> price : priceMap.entrySet()) {

                    //Getting how many kg for the specified Grade (Price.getKey represents the Grade)
                    kgPerGrade = new BatchManager().calculateKg(grades.get(price.getKey()), weight);
                    //Adding to the total each price value for each grade amount and relative price
                    if (grade.equalsIgnoreCase(price.getKey())) {
                        gradePrice = price.getValue() * Double.parseDouble(kgPerGrade.replace(",", "."));
                        if (!"".equalsIgnoreCase(String.valueOf(gradePrice))) {
                            return String.format("%.2f", gradePrice);
                        }
                    }
                }
            }

            return "0.00";
        } catch (Exception e) {
            return "0.00";
        }

    }

    /**
     * Getting Price Map
     *
     * @param fruitType
     * @param date
     * @return price map
     */
    private static Map<String, Double> priceMapForDisplay(String fruitType, String date) {
        DataManager dataManager = new DataManager();
        PricingManager pricingManager = new PricingManager();

        //Getting data Obj
        Data data = dataManager.getDataFromPricingFile(PathFile.PRICING.toString(), date);

        //Getting the Price Map for the specified fruitType
        if (null != data) {
            return pricingManager.getPriceMap(data, fruitType);
        }
        return null;
    }

    /**
     * Getting all Batch Files from a specific date
     * (This test needs to be performed without any pricing file present inside the test folder to avoid null pointer error)
     *
     * @param batchList
     * @param transactionDate
     * @return List of batches from a specific date
     */
    public List<Batch> getBatchListByDate(List<Batch> batchList, String transactionDate) {

        try {
            if (null != batchList) {
                //getting List for specified date
                return batchList.stream().filter(date -> date.getReceivedDate().replace(" ", "").equalsIgnoreCase(transactionDate)).collect(Collectors.toList());
            }
        } catch (Exception e) {
            displayString("Error while getting Batch List by date");
            return null;
        }
        return null;
    }
}
