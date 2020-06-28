package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

/**
 * @author Alessandro Spano (Student N. rmb19196)
 */
public enum PathFile {

    //TESTs
    TESTPATH("Test"),
    TEST("FileTest"),
    JSON_TEST("JSONTest"),

    //JSON
    JSON("JSON"),

    //Batch
    BATCH("Batch"),

    //Console
    CONSOLE("Console"),
    MENU_STEP1("MenuStep1"),
    MENU_STEP2("MenuStep2"),
    HELPMENU("HelpMenu");

    private String pathString;

    PathFile(String pathString) {
        this.pathString = pathString;
    }

    public String toString() {
        return this.pathString;
    }

    }
