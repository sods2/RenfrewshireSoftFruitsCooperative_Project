package com.RenfrewshireSoftFruitsCooperative_Project.java.Console;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Command {


    _1("1"),
    _2("2"),
    _3("3"),
    _4("4"),
    _5("5"),
    _6("6"),

    HELP("help"),
    BACK("back"),
    EXIT("exit");

    private String cmd;

    Command(String cmd) {
        this.cmd = cmd;
    }

    public String toString() {
        return this.cmd;
    }

    public static List<String> COMMAND_LIST = Stream.of(Command.values()).map(Command::toString).collect(Collectors.toList());

}
