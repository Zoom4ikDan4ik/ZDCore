package ru.zoom4ikdan4ik.core.enums;

import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.ICommands;
import ru.zoom4ikdan4ik.core.commands.SubCommandID;
import ru.zoom4ikdan4ik.core.commands.SubCommandReload;
import ru.zoom4ikdan4ik.core.commands.SubCommandScript;
import ru.zoom4ikdan4ik.core.commands.SubCommandUnix;

public enum CommandsEnum implements ICommands {
    RELOAD("reload", new SubCommandReload()),
    SCRIPT("script", new SubCommandScript()),
    UNIX("unix", new SubCommandUnix()),
    ID("id", new SubCommandID());

    private final String command;
    private final ISubCommandManager subCommandManager;

    CommandsEnum(String command, ISubCommandManager subCommandManager) {
        this.command = command;
        this.subCommandManager = subCommandManager;
    }

    @Override
    public final String getName() {
        return this.name();
    }

    @Override
    public final String getSubCommand() {
        return this.command;
    }

    @Override
    public final ISubCommandManager getSubCommandManager() {
        return this.subCommandManager;
    }
}
