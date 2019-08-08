package ru.zoom4ikdan4ik.core.enums;

public enum CommandsEnum {
    RELOAD("RELOAD"),
    SCRIPT("SCRIPT"),
    UNIX("UNIX"),
    ID("ID");
    private final String command;

    CommandsEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
