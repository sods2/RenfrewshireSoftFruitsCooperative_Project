package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.BatchManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.TransactionReportManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.ArrayList;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * Using this Class to create the Console UI for displaying a Transaction Report of a specified Day
 *
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class TransactionsReport {

    Console console;

    private final String folder = PathFile.BATCH.toString();
    String transactionDate = "";

    FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();
    DateManager dateManager = new DateManager();
    BatchManager batchManager = new BatchManager();

    List<Batch> batchList;
    List<Batch> batchListForReport;

    public boolean transactionsReport(Console console) {

        this.console = console;

        try {

            displayString("     Please enter transaction report date:");
            displayString("     Example (23-01-2020)");
            //getting batch ID
            while (!dateManager.isDateValidFormat(transactionDate = this.console.getInput()));

            //getting list of file names
            List<String> files = fileManagement.getFileList(folder);

            //getting data from all files
            Data data = fileManagement.readAll(folder, files);

            //getting batches' list
            if(null!= data) {
                batchList = dataManager.processBatchData(data);
            } else {
                displayString("No batch data found!");
            }

            if(null!=batchList){
                batchListForReport = batchManager.getBatchListByDate(batchList, transactionDate.replace("-", ""));
            }

            //Displaying Transactions' info
            Display.displayTransactionReport(batchListForReport);

            displayString("");

            if(!this.console.idle()){
                return false;
            }

            return true;
        } catch (Exception e) {
            displayString("Error while trying to display Transactions Report \n");
        }
        return false;
    }

}
