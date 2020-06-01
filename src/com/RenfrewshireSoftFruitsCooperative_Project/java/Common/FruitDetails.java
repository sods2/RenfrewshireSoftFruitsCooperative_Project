package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

import com.RenfrewshireSoftFruitsCooperative_Project.java.Console.Command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FruitDetails {

    _1("1"),
    _2("2"),
    _3("3"),
    _4("4");


    private String detail;

    FruitDetails(String detail) {
        this.detail = detail;
    }

    public String toString() {
        return this.detail;
    }

//    public static List<String> COMMAND_LIST = Stream.of(Command.values()).map(Command::toString).collect(Collectors.toList());

}
