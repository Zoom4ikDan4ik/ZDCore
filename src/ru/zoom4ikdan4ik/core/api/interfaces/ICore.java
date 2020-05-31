package ru.zoom4ikdan4ik.core.api.interfaces;

import ru.zoom4ikdan4ik.core.interfaces.ICommandManager;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

/**
 * Interface for main classes of plugins
 */
public interface ICore {
    IConfigManager getConfigManager();

    ISQLManager getSQLManager();

    ISchedulerManager getSchedulerManager();

    ICommandManager getCommandManager();
}
