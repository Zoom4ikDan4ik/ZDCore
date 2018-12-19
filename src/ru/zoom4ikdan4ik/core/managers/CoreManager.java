package ru.zoom4ikdan4ik.core.managers;

import ru.zoom4ikdan4ik.core.interfaces.*;

public class CoreManager implements ICore {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public IConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public IMySQLManager getMySQLManager() {
        return mySQLManager;
    }

    @Override
    public ISchedulerManager getSchedulerManager() {
        return schedulerManager;
    }

    @Override
    public ICommandManager getCommandManager() {
        return commandManager;
    }
}
