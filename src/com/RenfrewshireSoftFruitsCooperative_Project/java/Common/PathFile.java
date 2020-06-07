package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

public enum PathFile {

    //TESTs
    TESTPATH("Test"),
    TEST("FileTest"),
    JSON_TEST("JSONTest"),

    //JSON
    JSON("JSON"),

    //Console
    CONSOLE("Console"),
    MENU("Menu"),
    HELPMENU("HelpMenu");

    private String pathString;

    PathFile(String pathString) {
        this.pathString = pathString;
    }

    public String toString() {
        return this.pathString;
    }

    }
