package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import java.util.Scanner;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.MAX_INPUT_ATTEMPT;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Command.COMMAND_LIST;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

public class DataInput {

    private static Scanner scanner;

    /**
     * Getting console input (User input) used for menu
     *
     * @return returns input or empty string is no input was passed
     */
    public static String getMenuInput() {
        scanner = new Scanner(System.in);

        String cmd;
        int attempt= 0;

        try {
            //using line just for spacing
            displayString("");
            //Getting input value while the field is left empty
            while (!(cmd = scanner.next()).isEmpty()){

                //checking if command is valid
                if (checkInput(cmd)){
                    //returning the command
                    return cmd;
                } else {
                    displayString("You entered an invalid command\n" +
                            "Please try again!");
                }
                attempt++;

                //Checking number of attempts
                if (checkAttemptN(attempt)){
                    new Console().displayHelpMenu();
                }
            }

        } catch (Exception e) {
            displayString("Error while reading input\n" +
                    "Please try again!");
        }

        return "";
    }

    /**
     * Getting console input (User input) used for gathering data
     * @return returns input or empty string is no input was passed
     */
    public static String getUserInput() {
        scanner = new Scanner(System.in);

        String cmd;

        try {
            //using line just for spacing
            displayString("");
            //Getting input value while the field is left empty
            while (!(cmd = scanner.next()).isEmpty()){

                if (cmd.equals("exit")){
                    trafficLight( new Console(), cmd);
                }
                //returning the command
                return cmd;
            }

        } catch (Exception e) {
            displayString("Error while reading input\n" +
                    "Please try again!");
        }

        return "";
    }

    /**
     * Getting console input (User input) used for gathering data
     * @return returns input or empty string is no input was passed
     */
    public static String getUserInput_Double() {
        scanner = new Scanner(System.in);

        String cmd;

        try {
            //using line just for spacing
            displayString("");
            //Getting input value while the field is left empty
            while (!(cmd = String.valueOf(scanner.nextDouble())).isEmpty()){

                //returning the command
                return cmd;
            }

        } catch (Exception e) {
            displayString("Error while reading input\n" +
                    "Please try again!");
        }

        return "";
    }

    /**
     * Checking if input inserted is a valid input
     * @param cmd command inserted
     * @return true if command is valid
     */
    public static boolean checkInput(String cmd) {

        for (String command : COMMAND_LIST) {
            if (cmd.equals(command)){
                return true;
            }
        }

        return false;
    }

    /**
     * Using this method to keep the console in an Idle state
     * @return true if Enter key is pressed
     */
    public static boolean consoleIdle(){
        scanner = new Scanner(System.in);

        String cmd;
        int i;

        try{
            //using line just for spacing
            displayString("");
            for (i = 0; i >= 0; i++) {

                displayString("    Press enter to continue!\n" +
                        "    Or type 'exit' to quit the program");

                cmd = scanner.nextLine();

                if (cmd.equals("exit")){
                    trafficLight( new Console(), cmd);
                }

                if(cmd.isEmpty()){
                    displayString("");
                }

                i=-2;
            }

            return true;

        } catch (Exception e){
            displayString("Application could not go in Idle state!");
        } finally {
        }

        return false;
    }

    /**
     * Checking valid attempt number
     * @param attempt attempt number
     * @return true if we reached the maximum number of attempts
     */
    public static boolean checkAttemptN(int attempt) {
        return attempt >= MAX_INPUT_ATTEMPT;
    }

    /**
     * Validate if the user wishes to continue or not
     * @return true if the user wishes to continue
     */
    public static boolean validation() {
        String input;
        displayString("");
        displayString("    Please input Y or N to continue with the changes!");

        //Keep the loop on until Y or N are selected
        while (!(input = getUserInput()).equalsIgnoreCase("Y")) {
            if (input.equalsIgnoreCase("N")) {//if N is selected exit the loop
                return false;
            }
            displayString("    The input selected was wrong!\n" +
                    "    Please input Y or N to continue!");
        }
        return true;
    }


    /**
     * This method is used to manage the user interface
     * @param console Console obj
     * @param input String input
     */
    public static void trafficLight(Console console, String input){
        switch (input){
            //EXIT Application
            case "exit": case "7":
                displayString("Goodbye");
                console.exit();

            case "help": //Get Help Menu
                console.displayMainMenu();
                break;
            case "back": //Keep trying
                console.back();
                break;

            case "1": //Create New Branch
                console.CreateNewBatch();
                break;
            case "2": //List all batches
                console.listAll_Batches();
                break;
            case "3": //View details of a batch
                console.view_BatchDetails();
                break;
            case "4": //Sort\Grade a batch
                console.grade_Batch();
                break;
            case "5": //Payments
                console.update_Payments();
                break;
            case "6": //Transactions Report
                console.Transactions_Report();
                break;

            default://Should never be called
//                console.displayMainMenu();
        }
    }

}
