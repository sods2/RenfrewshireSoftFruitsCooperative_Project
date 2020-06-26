package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

public enum PathFile {

    //TESTs
    TESTPATH("Test"),
    TEST("FileTest"),
    JSON_TEST("JSONTest"),
    PRICING_TEST("PricingTest_"),

    //JSON
    JSON("JSON"),

    //Batch
    BATCH("Batch"),

    //Pricing
    PRICING("Pricing"),
    PRICING_FILE("Pricing_"),

    //Console
    CONSOLE("Console"),
    MENU_STEP1("MenuStep1"),
    MENU_STEP2("MenuStep2"),
    MENU_STEP3("MenuStep3"),
    MENU_STEP4("MenuStep4"),
    HELPMENU("HelpMenu");

    private String pathString;

    PathFile(String pathString) {
        this.pathString = pathString;
    }

    public String toString() {
        return this.pathString;
    }

    }
