package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Common.PathFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.PricingManager;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Menus.*;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.Data;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.FileManagement;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyFile;
import com.RenfrewshireSoftFruitsCooperative_Project.java.Data.FileManagement.MyJSON;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.DataInput.*;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class Console {

    final private String folder = "Pricing";

    FileManagement fileManagement = new MyFile();
    PricingManager pricingManager = new PricingManager();

    /**
     * Running the application
     */
    public void run() {
        if(!pricingManager.isPricingUpToDate(folder)){
            displayString("");
            displayString("     Looks like the Pricing file is not Up to Date");
            displayString("");
            update_Payments();
        }
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
     * Getting Command from user (generic String)
     */
    public String getCommand() {
        String input = getMenuInput();
        trafficLight(this, input);
        return input;
    }

    /**
     * Getting Command from user (generic String)
     */
    public String getInput() {
        String input = getUserInput();
        return input;
    }

    /**
     * Getting Command from user (Double)
     */
    public String getInput_Double() {
        String input = getUserInput_Double();
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
        fileManagement.read(PathFile.MENU_STEP4.toString());
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
     * Displaying specified batch's info
     */
    public boolean update_Payments() {
        UpdatePayments updatePayments = new UpdatePayments();
        return verifyProcess(updatePayments.updatePayments(this));
    }

    /**
     * Displaying specified batch's info
     */
    public boolean Transactions_Report() {
        TransactionsReport transactionsReport = new TransactionsReport();
        return verifyProcess(transactionsReport.transactionsReport(this));
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
