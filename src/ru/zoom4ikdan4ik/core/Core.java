package ru.zoom4ikdan4ik.core;

import org.bukkit.plugin.java.JavaPlugin;
import ru.zoom4ikdan4ik.core.api.RegistrationAPI;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.api.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.api.interfaces.ISchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.ICommandManager;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

/**
 * Main class for ZDCore
 */
public class Core extends JavaPlugin implements ICore, IBase {

    /**
     * Default enabling plugin
     */
    @Override
    public void onEnable() {
        RegistrationAPI.registerPlugin(this.corePlugin, this.corePlugin);

        this.loggerUtils.info(this.corePlugin, "Starting scheduler...");

        this.schedulerManager.startScheduler();
    }

    /**
     * Default disabling plugin
     */
    @Override
    public void onDisable() {
        this.pluginsManager.disablingPlugins();
    }

    @Override
    public IConfigManager getConfigManager() {
        return this.configManager;
    }

    @Override
    public ISQLManager getSQLManager() {
        return this.mySQLManager;
    }

    @Override
    public ISchedulerManager getSchedulerManager() {
        return this.schedulerManager;
    }

    @Override
    public ICommandManager getCommandManager() {
        return this.commandManager;
    }
}
