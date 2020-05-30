package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.getInput;

public class Console {

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
    public void getCommand() {
        String input = getInput();
        trafficLight(input);
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
     * This method is used to manage the user interface
     * @param input input inserted
     */
    public void trafficLight(String input){//TODO: check possible alternative
        switch (input){
            case "help":
                displayMainMenu();
                break;
            case "back":
                back();
                break;
            case "exit": case "5":
                exit();
            default:
                displayMainMenu();
        }

//        switch (Command.valueOf(input)){
//            case HELP:
//                displayMainMenu();
//                break;
//            case BACK:
//                back();
//                break;
//            case EXIT: case _5:
//                exit();
//            default:
//                displayMainMenu();
//        }
    }

}
