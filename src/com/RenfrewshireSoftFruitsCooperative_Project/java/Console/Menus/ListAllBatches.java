package com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DataManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.util.ArrayList;
import java.util.List;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class ListAllBatches {

    private final String folder = PathFile.BATCH.toString();

    private FileManagement fileManagement = new MyJSON();
    DataManager dataManager = new DataManager();
    private List<String> files;
    private Data data;
    private List<Batch> batchList = new ArrayList<>();

    public void listAllBatches() {

        try {

            //getting list of file names
            files = fileManagement.getFileList(folder);

            //getting data from all files
            data = fileManagement.readAll(folder, files);

            batchList = dataManager.processBatchData(data.getData());

            batchList.forEach(e -> displayString(e.getId() + e.getFruitType() + e.getFarmN() + "KG " + e.getReceivedDate()));

        } catch (Exception e){
            System.out.println();
        }



    }

}
