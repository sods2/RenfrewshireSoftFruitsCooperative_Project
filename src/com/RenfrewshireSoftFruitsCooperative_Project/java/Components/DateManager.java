package com.RenfrewshireSoftFruitsCooperative_Project.java.Components;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Display.displayString;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public class DateManager {

    /**
     * Getting Today's date as String
     *
     * @return Today's date
     */
    public String getDate() {

        //getting date
        LocalDate date = LocalDate.now();

        //formatting date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        //returning formatted date
        return date.format(formatter);

    }

    /**
     * Getting Today's date as formatted String for ID generation. will use it for file names.
     *
     * @return Formatted Today's date
     */
    public String getDateForID() {

        //getting date
        LocalDate date = LocalDate.now();

        //formatting date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

        //returning formatted date
        return date.format(formatter);

    }

    /**
     * Checking date format
     *
     * @param date
     * @return true if condition is met
     */
    public boolean isDateValidFormat(String date) {
        //Checking value format
        return date.matches("\\d{2}[-]\\d{2}[-]\\d{4}");
    }

}
