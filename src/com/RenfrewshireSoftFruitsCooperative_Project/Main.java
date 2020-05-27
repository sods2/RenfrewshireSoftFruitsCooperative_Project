package com.RenfrewshireSoftFruitsCooperative_Project;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import java.io.IOException;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class Main {

    public static void main(String[] args) throws IOException {
        MyFile myFile = new MyFile();
        Data data = new Data();
        myFile.write(PathFile.TEST, data);

        myFile.read(PathFile.MENU);

        DateManager dateManager = new DateManager();

        displayString(dateManager.getDate());
        displayString(dateManager.getDateForID());


        data.getData().put("GRADE_A", "22 %");
        MyJSON json = new MyJSON();

        System.out.println(json.write(PathFile.JSON, data));


    }
}
