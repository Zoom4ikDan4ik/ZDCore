package ru.zoom4ikdan4ik.core.interfaces;

/**
 * Interface for main classes of plugins
 * */
public interface ICore extends IBase {
    void onEnable();

    void onDisable();

    IConfigManager getConfigManager();

    ISQLManager getSQLManager();

    ISchedulerManager getSchedulerManager();

    ICommandManager getCommandManager();
}
