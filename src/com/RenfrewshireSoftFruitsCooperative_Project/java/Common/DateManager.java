package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        String textDate = date.format(formatter);

        //returning formatted date
        return textDate;

    }

    public String getDateForID() {

        //getting date
        LocalDate date = LocalDate.now();

        //formatting date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String textDate = date.format(formatter);

        //returning formatted date
        return textDate;

    }

}
