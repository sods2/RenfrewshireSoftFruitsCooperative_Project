package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

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
    public boolean checkBatchWeight(int weight) {

        return weight <= 100 && weight > 0;
    }

    /**
     * Checking the farm's number format
     * @param num farm's number
     * @return farm number with right format or empty string when format is wrong
     */
    public String checkFarmN(String num) {

        if (0<Integer.parseInt(num)){
            return String.format("%03d", Integer.valueOf(num));
        }

        System.out.println("Farm number is incorrect!\n" +
                "The right format is 001 to 999\n" +
                "Try entering the number again.");
        return "";
    }

}
