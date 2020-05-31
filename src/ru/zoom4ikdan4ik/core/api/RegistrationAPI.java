package ru.zoom4ikdan4ik.core.api;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.api.interfaces.ISQLManager;
import ru.zoom4ikdan4ik.core.api.interfaces.ISchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.ICommandManager;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

import java.util.HashMap;
import java.util.Map;

public class RegistrationAPI implements IBase {
    /**
     * Record all of the plugins that work from ZDCore
     */
    public static final Map<Plugin, ICore> plugins = new HashMap<>();

    public static final Map<Plugin, ICore> getPlugins() {
        return plugins;
    }

    /**
     * Registration of plugins, processing of their managers
     */
    public static void registerPlugin(ICore manager, Plugin plugin) {
        if (!corePlugin.getName().equalsIgnoreCase(plugin.getName()))
            plugins.put(plugin, manager);

        IConfigManager config = manager.getConfigManager();
        if (config != null) {
            loggerUtils.info(plugin, "Loading configs...");

            config.setFileConfig(coreMethods.createConfigYML(plugin.getName(), plugin));
            config.setConfig(YamlConfiguration.loadConfiguration(config.getFileConfig()));
            config.loadConfig();
            config.save();
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

        ICommandManager command = manager.getCommandManager();
        if (command != null) {
            loggerUtils.info(plugin, "Register commands...");

            for (String com : command.getCommands()) {
                PluginCommand pluginCommand = Bukkit.getPluginCommand(com);

                if (pluginCommand.isRegistered()) {
                    loggerUtils.info(plugin, "Command %% was registered by %% plugin", com, pluginCommand.getPlugin().getName());

                    Bukkit.getPluginCommand(com).setExecutor(command);
                } else
                    loggerUtils.info(plugin, "Command %% can't be register by %% plugin", com, pluginCommand.getPlugin().getName());
            }
        }
    }
}
