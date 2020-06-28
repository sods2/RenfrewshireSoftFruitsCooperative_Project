package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Fruit;

import java.util.*;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class TransactionReportManager {

    BatchManager batchManager = new BatchManager();

    Map<String, Double> batchTotals = new HashMap<>();

    List<Batch> batches;
    List<Double> totGradeABatch = new ArrayList<>();
    List<Double> totGradeBBatch = new ArrayList<>();
    List<Double> totGradeCBatch = new ArrayList<>();
    List<Double> totRejectedBatch = new ArrayList<>();

    /**
     * Getting map of total prices sorted by grade
     *
     * @param batchList
     * @param fruit
     * @return map of total prices
     */
    public Map<String, Double> getPricesForTransactionReport(List<Batch> batchList, Fruit fruit) {

        //get list by fruit type
        batches = batchManager.getBatchListByFruitType(batchList, fruit.getCode());

        if (0 != batches.size()) {
            //get tot of batches with fruit type
            batches.forEach(e -> {
                for (Map.Entry<String, Double> entry : e.getGrades().entrySet()) {

                    double totGradeA = 0.0;
                    double totGradeB = 0.0;
                    double totGradeC = 0.0;

                    switch (entry.getKey()) {
                        case "GRADE A":
                            totGradeA += Double.parseDouble(batchManager.getTotPriceByGrade(e.getId().substring(0, e.getId().length() - 7),
                                    e.getFruitType(),
                                    e.getWeight(),
                                    e.getGrades(),
                                    entry.getKey()).replace(",", "."));
                            totGradeABatch.add(totGradeA);
                            break;
                        case "GRADE B":
                            totGradeB += Double.parseDouble(batchManager.getTotPriceByGrade(e.getId().substring(0, e.getId().length() - 7),
                                    e.getFruitType(),
                                    e.getWeight(),
                                    e.getGrades(),
                                    entry.getKey()).replace(",", "."));
                            totGradeBBatch.add(totGradeB);
                            break;
                        case "GRADE C":
                            totGradeC += Double.parseDouble(batchManager.getTotPriceByGrade(e.getId().substring(0, e.getId().length() - 7),
                                    e.getFruitType(),
                                    e.getWeight(),
                                    e.getGrades(),
                                    entry.getKey()).replace(",", "."));
                            totGradeCBatch.add(totGradeC);
                            break;
                    }

                }
            });

            populateMap(FruitGrade.GRADE_A.toString(), totGradeABatch.stream().reduce(0.0, Double::sum));
            populateMap(FruitGrade.GRADE_B.toString(), totGradeBBatch.stream().reduce(0.0, Double::sum));
            populateMap(FruitGrade.GRADE_C.toString(), totGradeCBatch.stream().reduce(0.0, Double::sum));

            return batchTotals;
        }
        populateMap(FruitGrade.GRADE_A.toString(), 0.0);
        populateMap(FruitGrade.GRADE_B.toString(), 0.0);
        populateMap(FruitGrade.GRADE_C.toString(), 0.0);

        return batchTotals;
    }

    /**
     * Getting map of total weights sorted by grade
     *
     * @param batchList
     * @param fruit
     * @return map of total weights
     */
    public Map<String, Double> getWeightsForTransactionReport(List<Batch> batchList, Fruit fruit) {

        //get list by fruit type
        batches = batchManager.getBatchListByFruitType(batchList, fruit.getCode());

        if (0 != batches.size()) {
            //get tot of batches with fruit type
            batches.forEach(e -> {
                for (Map.Entry<String, Double> entry : e.getGrades().entrySet()) {

                    double totGradeA = 0.0;
                    double totGradeB = 0.0;
                    double totGradeC = 0.0;
                    double totRejected = 0.0;

                    switch (entry.getKey()) {
                        case "GRADE A":
                            totGradeA += Double.parseDouble(batchManager.calculateKg(entry.getValue(), e.getWeight()).replace(",", "."));
                            totGradeABatch.add(totGradeA);
                            break;
                        case "GRADE B":
                            totGradeB += Double.parseDouble(batchManager.calculateKg(entry.getValue(), e.getWeight()).replace(",", "."));
                            totGradeBBatch.add(totGradeB);
                            break;
                        case "GRADE C":
                            totGradeC += Double.parseDouble(batchManager.calculateKg(entry.getValue(), e.getWeight()).replace(",", "."));
                            totGradeCBatch.add(totGradeC);
                            break;
                        case "REJECTED":
                            totRejected += Double.parseDouble(batchManager.calculateKg(entry.getValue(), e.getWeight()).replace(",", "."));
                            totRejectedBatch.add(totRejected);
                            break;
                    }

                }
            });

            populateMap(FruitGrade.GRADE_A.toString(), totGradeABatch.stream().reduce(0.0, Double::sum));
            populateMap(FruitGrade.GRADE_B.toString(), totGradeBBatch.stream().reduce(0.0, Double::sum));
            populateMap(FruitGrade.GRADE_C.toString(), totGradeCBatch.stream().reduce(0.0, Double::sum));
            populateMap(FruitGrade.REJECTED.toString(), totRejectedBatch.stream().reduce(0.0, Double::sum));

            return batchTotals;
        }
        populateMap(FruitGrade.GRADE_A.toString(), 0.0);
        populateMap(FruitGrade.GRADE_B.toString(), 0.0);
        populateMap(FruitGrade.GRADE_C.toString(), 0.0);
        populateMap(FruitGrade.REJECTED.toString(), 0.0);

        return batchTotals;
    }

    /**
     * populates a map with the given keys and values
     *
     * @param grade
     * @param value
     */
    private void populateMap(String grade, Double value) {

        batchTotals.put(grade, value);
    }

}
