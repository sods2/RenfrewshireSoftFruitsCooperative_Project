package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus.CreateNewBatch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus.GradeBatch;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus.ListAllBatches;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus.ViewBatchDetails;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.*;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class Console {

    FileManagement fileManagement = new MyFile();

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
     * Putting the console in an idle state until the Enter key is pressed
     */
    public boolean idle() {
        return consoleIdle();
    }

    /**
     * Display Main Menu
     */
    public void displayMainMenu() {
        fileManagement.read(PathFile.MENU_STEP2.toString());
        getCommand();
    }

    /**
     * Display Help Menu
     */
    public void displayHelpMenu() {
        fileManagement.read(PathFile.HELPMENU.toString());
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
    public boolean CreateNewBatch() {
        CreateNewBatch createNewBatch = new CreateNewBatch();
        return verifyProcess(createNewBatch.create(this));
    }

    /**
     * Displaying all batch's info
     */
    public boolean listAll_Batches() {
        ListAllBatches allBatches = new ListAllBatches();
        return verifyProcess(allBatches.listAllBatches(this));
    }

    /**
     * Displaying all batches's info
     */
    public boolean grade_Batch() {
        GradeBatch gradeBatch = new GradeBatch();
        return verifyProcess(gradeBatch.gradeBatch(this));
    }

    /**
     * Displaying specified batch's info
     */
    public boolean view_BatchDetails() {
        ViewBatchDetails viewBatchDetails = new ViewBatchDetails();
        return verifyProcess(viewBatchDetails.displayBatchDetails(this));
    }

    /**
     * Validating inputted data
     */
    public boolean validate_Input() {
        return validation();
    }

    /**
     * Verify that operation ended with true feedback
     *
     * Using it just to double check that everything went fine
     * @param processVerification
     * @return same value that was initially passed
     */
    public boolean verifyProcess(boolean processVerification){
        if (!processVerification){
            displayString("Sorry, something went wrong\n" +
                    "Please try again!");
            return false;
        }
        return true;
    }

}
