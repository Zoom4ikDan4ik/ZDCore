package ru.zoom4ikdan4ik.core;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.zoom4ikdan4ik.core.interfaces.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Main class for ZDCore
 */
public class Core extends JavaPlugin implements ICore, IBase {

    /**
     * Record all of the plugins that work from ZDCore
     */
    public Map<Plugin, ICore> plugins = new HashMap<Plugin, ICore>();

    /**
     * Default enabling plugin
     */
    @Override
    public void onEnable() {
        this.registerPlugin(this.corePlugin, this.corePlugin);

        this.loggerUtils.info(corePlugin, "Starting scheduler...");

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

    /**
     * Registration of plugins, processing of their managers
     */
    public void registerPlugin(ICore manager, Plugin plugin) {
        if (!this.corePlugin.getName().equalsIgnoreCase(plugin.getName()))
            this.plugins.put(plugin, manager);

        IConfigManager config = manager.getConfigManager();
        if (config != null) {
            this.loggerUtils.info(plugin, "Loading configs...");

            config.setFileConfig(this.coreMethods.createConfigYML(plugin.getName(), plugin));
            config.setConfig(YamlConfiguration.loadConfiguration(config.getFileConfig()));
            config.loadConfig();

            this.configUtils.save(plugin);
        }

        ISQLManager sql = manager.getSQLManager();
        if (sql != null) {
            this.loggerUtils.info(plugin, "Create tables...");

            sql.createTables();
        }

        ISchedulerManager scheduler = manager.getSchedulerManager();
        if (scheduler != null) {
            this.loggerUtils.info(plugin, "Add schedulers...");

            scheduler.addScheduler();
        }

        ICommandManager command = manager.getCommandManager();
        if (command != null) {
            this.loggerUtils.info(plugin, "Register commands...");

            List<String> commands = command.getCommands();

            for (String com : commands)
                Bukkit.getPluginCommand(com).setExecutor(command);
        }
    }
}
