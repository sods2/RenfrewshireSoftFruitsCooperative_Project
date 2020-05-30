package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import java.util.Scanner;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Common.Constants.MAX_INPUT_ATTEMPT;
import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Command.COMMAND_LIST;

public class DataInput {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Getting console input (User input)
     *
     * @return returns input or empty string is no input was passed
     */
    public static String getInput() {
        String cmd = "";
        int attempt= 0;

        try {
            //using line just for spacing
            System.out.println();
            //Getting input value while the field is left empty
            while (!(cmd = scanner.next()).isEmpty()){

                //checking if command is valid
                if (checkInput(cmd)){
                    //returning the command
                    return cmd;
                } else {
                    System.out.println("You entered an invalid command\n" +
                            "Please try again!");
                }
                attempt++;

                //Checking number of attempts
                if (checkAttemptN(attempt)){
                    new Console().displayHelpMenu();
                }

//                helpCommands(cmd);
            }

        } catch (Exception e) {
            System.out.println("Error while reading input\n" +
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
     * Checking valid attempt number
     * @param attempt attempt number
     * @return true if we reached the maximum number of attempts
     */
    public static boolean checkAttemptN(int attempt) {
        return attempt >= MAX_INPUT_ATTEMPT;
    }

}
