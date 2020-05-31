package com.RenfrewshireSoftFruitsCooperative_Project;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.DateManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.FruitGrade;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Console;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Entities.Batch;

import java.io.IOException;
import java.util.HashMap;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.STRAWBERRIES;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class Main {

    public static void main(String[] args) {

        while (true){
            new Console().run();
        }
    }

    private static void test() throws IOException {
        MyFile myFile = new MyFile();
        Data data = new Data();
        myFile.write(PathFile.TEST.toString(), data);

        //Read Menu
        myFile.read(PathFile.MENU.toString());

        DateManager dateManager = new DateManager();

        //TEST dateManager to create dates
        displayString(dateManager.getDate());
        displayString(dateManager.getDateForID());

        //Testing JSON Creation
        data.getData().put("TestContent", "Content");

        HashMap<String, Integer> grades = new HashMap<>();
        grades.put(FruitGrade.GRADE_A.toString(), 22);

        Batch batch = new Batch(50, "001", STRAWBERRIES, grades);

        MyJSON json = new MyJSON();

        System.out.println(json.createNewFile(batch.getId(),data));

        //Read JSON
        String jsonStr;

        if(json.read("300520-ST-001") != null){

            data = (Data) json.read("300520-ST-001");

            data.getData().keySet().forEach(System.out::println);
            data.getData().values().forEach(System.out::println);
        }
    }
}
