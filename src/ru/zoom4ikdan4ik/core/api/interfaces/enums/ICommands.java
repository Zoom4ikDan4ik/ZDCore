package ru.zoom4ikdan4ik.core.api.interfaces.enums;

import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;

public interface ICommands {
    String getName();

    String getSubCommand();

    ISubCommandManager getSubCommandManager();
}
