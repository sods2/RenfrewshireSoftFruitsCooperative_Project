package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Components.DateManager;

public class IDGenerator {

    private String fruitType;

    private String farmN;

    public IDGenerator(String fruitType, String farmN) {
        this.fruitType = fruitType;
        this.farmN = farmN;
    }

    /**
     * Creating ID String using desired information. This will be used for the Name of teh files.
     *
     *  ID = formatted Date (ddMMYY) + Fruit Type (eg. ST - Strawberries) + Farm Number (001 to 999)
     *
     * @return ID String
     */
    public String createID() {

        String ID;
        DateManager dateManager = new DateManager();

        //creating Id string
        ID = dateManager.getDateForID() + "-" +
                fruitType               + "-" +
                farmN;

        return ID;

    }

}
