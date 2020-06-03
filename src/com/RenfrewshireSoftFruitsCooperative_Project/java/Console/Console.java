package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus.CreateNewBatch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.*;

public class Console {//TODO: refactor for using use File manages

    MyFile myFile = new MyFile();

    /**
     * Running the application
     */
    public void run() {
        displayMainMenu();
    }

    /**
     * Exit the program
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * Getting Command from user (in case I want to add a different feature)
     */
    public void back(){
        getCommand();
    }

    /**
     * Getting Command from user
     */
    public String getCommand() {
        String input = getMenuInput();
        trafficLight(this, input);
        return input;
    }

    /**
     * Getting Command from user
     */
    public String getInput() {
        String input = getUserInput();
        return input;
    }

    /**
     * Display Main Menu
     */
    public void displayMainMenu() {
        myFile.read(PathFile.MENU.toString());
        getCommand();
    }

    /**
     * Display Help Menu
     */
    public void displayHelpMenu() {
        myFile.read(PathFile.HELPMENU.toString());
    }

    /**
     * Create New Branch
     */
    public boolean createNewJSON(String FileName, Data data) {
        MyJSON json = new MyJSON();
        return json.createNewFile(FileName,data);
    }

    /**
     * Create New Branch
     */
    public void CreateNewBranch() {
        CreateNewBatch createNewBatch = new CreateNewBatch();
        createNewBatch.create(this);
    }



}
