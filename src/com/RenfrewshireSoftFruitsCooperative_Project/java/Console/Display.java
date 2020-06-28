package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.FruitManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.TransactionReportManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Fruit;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Price;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Pricing;

import java.util.*;
import java.util.stream.Stream;

/**
 * Displaying data to the console
 *
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class Display {

    private Display() {
    }

    /**
     * getting a String of data and Displaying it to the Console
     *
     * @param data Data to be displayed
     */
    public static void displayString(String data) {
        //don't really need the try, I am using it just for security in case something goes wrong
        try {
            if (!data.isEmpty()) {
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
     *
     * @param data Data to be displayed
     */
    public static void displayStream(Stream<String> data) {
        if (null != data) {
            data.forEach(System.out::println);
        } else {
            displayString("Data not Found!");
        }
    }

    /**
     * Displaying batches' info
     */
    public static void displayBatches(List<Batch> batchList) {
        displayString("    BATCH ID         TYPE    FARM N.     WEIGHT       DATE        PRICE");

        //printing all batches' information
        if (null != batchList) {
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  "
                    + e.getFruitType() + "  |    "
                    + e.getFarmN() + "    |   "
                    + e.getWeight() + "KG" + "   | "
                    + e.getReceivedDate()
                    //Display Price
                    + " |  £ " + BatchManager.getBatchPrice(e.getId().substring(0, e.getId().length() - 7), e.getFruitType(), e.getWeight(), e.getGrades())
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

        displayString("    BATCH ID         TYPE    FARM N.      WEIGHT        DATE         PRICE");

        if (null != batchList) {
            //Display Batch's info
            batchList.forEach(e -> displayString("  " + e.getId() + "    |  "
                    + e.getFruitType() + "  |    "
                    + e.getFarmN() + "    |   "
                    + e.getWeight() + "KG" + "   | "
                    + e.getReceivedDate()
                    //Display Price
                    + " |  £ " + BatchManager.getBatchPrice(e.getId().substring(0, e.getId().length() - 7), e.getFruitType(), e.getWeight(), e.getGrades())
                    + "\n    "
            ));

            //Display Batch's Grades
            batchList.forEach(e -> {
                SortedMap<String, Double> sortedMap = new TreeMap<>(e.getGrades());
                for (Map.Entry<String, Double> entry : sortedMap.entrySet()) {
                    displayString("    " + entry.getKey()
                            + "    " + entry.getValue()
                            + " = " +
                            batchManager.calculateKg(entry.getValue(), e.getWeight()) + "KG" +
                            "   = £ " +
                            //Display Price
                            BatchManager.getGradesPrice(e.getId().substring(0, e.getId().length() - 7), e.getFruitType(), e.getWeight(), e.getGrades(), entry.getKey())
                    );
                    displayString("");
                }
            });

            displayString("");
        } else {
            displayString("No batch found");
        }
    }

    /**
     * Display Transaction report
     *
     * @param batchList
     */
    public static void displayTransactionReport(List<Batch> batchList) {
        TransactionReportManager reportManager = new TransactionReportManager();
        FruitManager fruitManager = new FruitManager();

        Map<String, Double> priceTotalsMap = new HashMap<>();
        Map<String, Double> weightTotalsMap = new HashMap<>();

        List<Double> totalToPay = new ArrayList<>();

        displayString("    FRUIT        GRADE A       GRADE B        GRADE C        REJECTED      TOTAL PAID");

        if (null != batchList) {

            for (Fruit fruit : fruitManager.getFruitList()) {
                //Getting price totals to display
                priceTotalsMap.putAll(reportManager.getPricesForTransactionReport(batchList, fruit));
                totalToPay.add((priceTotalsMap.get("GRADE A") + priceTotalsMap.get("GRADE B") + priceTotalsMap.get("GRADE C")));
                
                //Getting weight total to Display
                weightTotalsMap.putAll(reportManager.getWeightsForTransactionReport(batchList, fruit));

                //Display Price Totals
                displayString(  fruit.getName()               + "  |  £ "
                                    + priceTotalsMap.get("GRADE A")      + "   |  £ "
                                    + priceTotalsMap.get("GRADE B")      + "    |   £ "
                                    + priceTotalsMap.get("GRADE C")      + "     |      £ 0.0    |   £ "
                                    + (priceTotalsMap.get("GRADE A") + priceTotalsMap.get("GRADE B") + priceTotalsMap.get("GRADE C")));

                //Display Price Totals
                displayString(  "              | KG "
                        + String.format("%.3f", weightTotalsMap.get("GRADE A"))      + "  |  KG "
                        + String.format("%.3f", weightTotalsMap.get("GRADE B"))      + "  |   KG "
                        + String.format("%.3f", weightTotalsMap.get("GRADE C"))      + "  |   KG "
                        + String.format("%.3f", weightTotalsMap.get("REJECTED"))      + "\n");

                priceTotalsMap = new HashMap<>();
                weightTotalsMap = new HashMap<>();
            }

            displayString("TOTAL PAID: £ " + String.format("%.2f", totalToPay.stream().reduce(0.0, Double::sum)));

            displayString("");
        } else {
            displayString("No batch found");
        }
    }

    /**
     * Displaying inserted Price values by Fruit Type
     *
     * @param pricing
     */
    public static void displayPricing(Pricing pricing) {
        FruitManager fruitManager = new FruitManager();

        if (0 != pricing.getPricingList().size()) {
            for (Map.Entry<String, Price> price : pricing.getPricingList().entrySet()) {
                SortedMap<String, Double> sortedList = new TreeMap<>(price.getValue().getPrice());
                displayString("       - " + fruitManager.getFruitNameByCode(price.getKey()) + " " + sortedList);
            }
        } else {
            displayString("         [No data Found]");
        }
    }

}
