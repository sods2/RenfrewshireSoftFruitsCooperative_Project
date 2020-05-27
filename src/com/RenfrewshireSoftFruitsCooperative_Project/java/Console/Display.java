package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import java.util.stream.Stream;

/**
 * Displaying data to the console
 */
public class Display {

    private Display(){}

    /**
     * getting a String of data and Displaying it to the Console
     * @param data Data to be displayed
     */
    public static void displayString(String data){
        if(!data.isEmpty()){
            System.out.println(data);
        } else {
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
            System.out.println("Data not Found!");
        }
    }

}
