package ru.zoom4ikdan4ik.core.enums;

import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.ICommands;
import ru.zoom4ikdan4ik.core.commands.SubCommandID;
import ru.zoom4ikdan4ik.core.commands.SubCommandReload;
import ru.zoom4ikdan4ik.core.commands.SubCommandScript;
import ru.zoom4ikdan4ik.core.commands.SubCommandUnix;

public enum CommandsEnum implements ICommands {
    RELOAD("RELOAD", new SubCommandReload()),
    SCRIPT("SCRIPT", new SubCommandScript()),
    UNIX("UNIX", new SubCommandUnix()),
    ID("ID", new SubCommandID());

    private final String command;
    private final ISubCommandManager subCommandManager;

    CommandsEnum(String command, ISubCommandManager subCommandManager) {
        this.command = command;
        this.subCommandManager = subCommandManager;
    }

    @Override
    public String getSubCommand() {
        return this.command;
    }

    @Override
    public ISubCommandManager getSubCommandManager() {
        return this.subCommandManager;
    }
}
