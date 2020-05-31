package ru.zoom4ikdan4ik.core.enums;

import ru.zoom4ikdan4ik.core.api.interfaces.enums.IMessages;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public enum MessagesEnum implements IMessages, IBase {
    NUMBER_EXCEPTIONS("&cNot enough parameters!"),
    CONSOLE_SENDER("&cThis command can be used only by the player!"),
    NOT_FOUND_PARAMETERS("&cUnknown parameters!"),
    NOT_HAVE_PERMISSIONS("&cNot enough rights!");

    private String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
