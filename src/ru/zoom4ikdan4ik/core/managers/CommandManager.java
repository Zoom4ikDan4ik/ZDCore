package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.api.managers.AbstractCommandManager;
import ru.zoom4ikdan4ik.core.enums.CommandsEnum;

public final class CommandManager extends AbstractCommandManager {

    @Override
    public final void registerCommands() {
        this.registerCommand("zdcore");
    }

    @Override
    public final void registerSubCommands() {
        for (CommandsEnum commandsEnum : CommandsEnum.values())
            this.registerSubCommand(commandsEnum);
    }
}
