package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

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

    public boolean isGradeValid(String grade){//TODO: update test
        if (grade.matches("\\d{1,3}[.]\\d{0,2}")) {
            //Checking value format
            return true;
        }
            else {
                displayString("Grade format is incorrect!\n" +
                        "Example (0, 50, 25.5)");
        }
        return false;
    }

    /**
     * Calculating how many kg a percentage represents
     * @return result of percentage to kg
     */
    public String calculateKg(double percent, double tot){//TODO: update test

        return String.format("%.3f", (tot * (percent / 100)));
    }

}
