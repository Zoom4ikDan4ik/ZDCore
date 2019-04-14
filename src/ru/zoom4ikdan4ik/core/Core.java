package ru.zoom4ikdan4ik.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zoom4ikdan4ik.core.interfaces.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class for ZDCore
 */
public class Core extends JavaPlugin implements ICore, IBase {

    /**
     * Record all of the plugins that work from ZDCore
     */
    public Map<Plugin, ICore> plugins = new HashMap<>();

    /**
     * Default enabling plugin
     */
    @Override
    public void onEnable() {
        this.registerPlugin(corePlugin, corePlugin);

        loggerUtils.info(corePlugin, "Starting scheduler...");

        schedulerManager.startScheduler();
    }

    /**
     * Default disabling plugin
     */
    @Override
    public void onDisable() {
        pluginsManager.disablingPlugins();
    }

    @Override
    public IConfigManager getConfigManager() {
        return configManager;
    }

    @Override
    public ISQLManager getSQLManager() {
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

    /**
     * Registration of plugins, processing of their managers
     */
    public void registerPlugin(ICore manager, Plugin plugin) {
        if (!corePlugin.getName().equalsIgnoreCase(plugin.getName()))
            plugins.put(plugin, manager);

        ICommandManager command = manager.getCommandManager();
        if (command != null) {
            loggerUtils.info(plugin, "Register commands...");

            Bukkit.getPluginCommand(plugin.getName().toLowerCase()).setExecutor(command);
        }

        IConfigManager config = manager.getConfigManager();
        if (config != null) {
            loggerUtils.info(plugin, "Loading configs...");

            config.loadConfig();
            config.saveConfig();
        }

        ISQLManager sql = manager.getSQLManager();
        if (sql != null) {
            loggerUtils.info(plugin, "Create tables...");

            sql.createTables();
        }

        ISchedulerManager scheduler = manager.getSchedulerManager();
        if (scheduler != null) {
            loggerUtils.info(plugin, "Add schedulers...");

            scheduler.addScheduler();
        }
    }
}
