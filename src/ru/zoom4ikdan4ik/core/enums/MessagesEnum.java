package ru.zoom4ikdan4ik.core.enums;

import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.enums.IMessages;

public enum MessagesEnum implements IMessages, IBase {
    NUMBER_EXCEPTIONS(configUtils.getString(configManager.getConfig(), "Messages.NUMBER_EXCEPTIONS", "&cNot enough parameters!")),
    CONSOLE_SENDER(configUtils.getString(configManager.getConfig(), "Messages.CONSOLE_SENDER", "&cThis command can be used only by the player!")),
    NOT_FOUND_PARAMETERS(configUtils.getString(configManager.getConfig(), "Messages.NOT_FOUND_PARAMETERS", "&cUnknown parameters!")),
    NOT_HAVE_PERMISSIONS(configUtils.getString(configManager.getConfig(), "Messages.NOT_HAVE_PERMISSIONS", "&cNot enough rights!"));

    private final String message;

    MessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
