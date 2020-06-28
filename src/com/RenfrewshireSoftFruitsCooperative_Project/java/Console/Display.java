package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import java.util.stream.Stream;

/**
 * Displaying data to the console
 *
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class Display {

    private Display(){}

    /**
     * getting a String of data and Displaying it to the Console
     * @param data Data to be displayed
     */
    public static void displayString(String data){
        //don't really need the try, I am using it just for security in case something goes wrong
        try {
            if(!data.isEmpty()){
                System.out.println(data);
            } else {
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Data not Found!");
        }
    }

    /**
     * getting a Stream of String type and Displaying it to the Console
     * @param data Data to be displayed
     */
    public static void displayStream(Stream<String> data){
        if(null!=data){
            data.forEach(System.out::println);
        } else {
            displayString("Data not Found!");
        }
    }

}
