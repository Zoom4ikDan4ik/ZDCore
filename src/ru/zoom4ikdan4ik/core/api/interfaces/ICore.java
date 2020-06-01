package ru.zoom4ikdan4ik.core.api.interfaces;

import ru.zoom4ikdan4ik.core.api.managers.AbstractCommandManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractConfigManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSQLManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSchedulerManager;

/**
 * Interface for main classes of plugins
 */
public interface ICore {
    AbstractConfigManager getConfigManager();

    AbstractSQLManager getSQLManager();

    AbstractSchedulerManager getSchedulerManager();

    AbstractCommandManager getCommandManager();
}
