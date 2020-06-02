package ru.zoom4ikdan4ik.core.api;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.*;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.HashMap;
import java.util.Map;

public final class RegistrationAPI implements IBase {
    /**
     * Record all of the plugins that work from ZDCore
     */
    private static final Map<Plugin, ICore> plugins = new HashMap<>();

    public static final Map<Plugin, ICore> getPlugins() {
        return plugins;
    }

    /**
     * Registration of plugins, processing of their managers
     */
    public final static void registerPlugin(final ICore manager, final Plugin plugin) {
        if (!corePlugin.getName().equalsIgnoreCase(plugin.getName()))
            plugins.put(plugin, manager);

        registerConfigManager(plugin, manager.getConfigManager());
        registerSQLManager(plugin, manager.getSQLManager());
        registerSchedulerManager(plugin, manager.getSchedulerManager());
        registerCommandManager(plugin, manager.getCommandManager());
    }

    public final static void registerConfigManager(final Plugin plugin, final IConfigManager configManager) {
        if (configManager != null) {
            loggerUtils.info(plugin, "Loading configs...");

            configManager.setFileConfig(coreMethods.createConfigYML(plugin.getName(), plugin));
            configManager.setConfig(YamlConfiguration.loadConfiguration(configManager.getFileConfig()));
            configManager.loadConfig();
            configManager.save();
        }
    }

    public final static void registerSQLManager(final Plugin plugin, final ISQLManager sqlManager) {
        if (sqlManager != null) {
            loggerUtils.info(plugin, "Create tables...");

            sqlManager.createTables();
        }
    }

    public final static void registerSchedulerManager(final Plugin plugin, final ISchedulerManager schedulerManager) {
        if (schedulerManager != null) {
            loggerUtils.info(plugin, "Add schedulers...");

            schedulerManager.addScheduler();
        }
    }

    public final static void registerCommandManager(final Plugin plugin, final ICommandManager commandManager) {
        if (commandManager != null) {
            loggerUtils.info(plugin, "Register commands...");

            for (String command : commandManager.getCommands()) {
                PluginCommand pluginCommand = Bukkit.getPluginCommand(command);

                if (pluginCommand.isRegistered()) {
                    loggerUtils.info(plugin, "Command %% was registered by %% plugin", command, pluginCommand.getPlugin().getName());

                    Bukkit.getPluginCommand(command).setExecutor(commandManager);
                } else
                    loggerUtils.info(plugin, "Command %% can't be register by %% plugin", command, pluginCommand.getPlugin().getName());
            }
        }
    }
}
