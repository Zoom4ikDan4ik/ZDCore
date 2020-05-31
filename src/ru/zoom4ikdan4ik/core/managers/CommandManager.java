package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.api.managers.AbstractCommandManager;
import ru.zoom4ikdan4ik.core.enums.CommandsEnum;

public class CommandManager extends AbstractCommandManager {

    @Override
    public void registerCommands() {
        this.registerCommand("zdcore");
    }

    @Override
    public void registerSubCommands() {
        this.registerSubCommand(CommandsEnum.ID);
        this.registerSubCommand(CommandsEnum.RELOAD);
        this.registerSubCommand(CommandsEnum.SCRIPT);
        this.registerSubCommand(CommandsEnum.UNIX);
    }
}
