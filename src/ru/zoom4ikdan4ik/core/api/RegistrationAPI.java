package ru.zoom4ikdan4ik.core.api;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.api.managers.AbstractCommandManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractConfigManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSQLManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.HashMap;
import java.util.List;
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
        long start = coreMethods.getSystemTime();

        if (!corePlugin.getName().equalsIgnoreCase(plugin.getName()))
            plugins.put(plugin, manager);

        registerConfigManager(plugin, manager.getConfigManagers());
        registerSQLManager(plugin, manager.getSQLManager());
        registerSchedulerManager(plugin, manager.getSchedulerManager());
        registerCommandManager(plugin, manager.getCommandManager());

        long end = coreMethods.getSystemTime();

        loggerUtils.info(plugin, "Started with %% sec.", ((end - start) / 1000.0));
    }

    public final static void registerConfigManager(final Plugin plugin, final List<AbstractConfigManager> abstractConfigManagers) {
        for (final AbstractConfigManager abstractConfigManager : abstractConfigManagers) {
            loggerUtils.info(plugin, "Loading configs...");

            abstractConfigManager.setFileConfig(coreMethods.createConfigYML(abstractConfigManager.getName(), plugin));
            abstractConfigManager.setConfig(YamlConfiguration.loadConfiguration(abstractConfigManager.getFileConfig()));
            abstractConfigManager.loadConfig();
            abstractConfigManager.save();
        }
    }

    public final static void registerSQLManager(final Plugin plugin, final AbstractSQLManager abstractSQLManager) {
        if (abstractSQLManager != null) {
            loggerUtils.info(plugin, "Create tables...");

            abstractSQLManager.createTables();
        }
    }

    public final static void registerSchedulerManager(final Plugin plugin, final AbstractSchedulerManager abstractSchedulerManager) {
        if (abstractSchedulerManager != null) {
            loggerUtils.info(plugin, "Add schedulers...");

            abstractSchedulerManager.addScheduler();
            abstractSchedulerManager.startScheduler();

            loggerUtils.info(plugin, "Starting scheduler...");
        }
    }

    public final static void registerCommandManager(final Plugin plugin, final AbstractCommandManager abstractCommandManager) {
        if (abstractCommandManager != null) {
            loggerUtils.info(plugin, "Register commands...");

            abstractCommandManager.unregisterAll();
            abstractCommandManager.registerCommands();
            abstractCommandManager.registerSubCommands();

            for (final String command : abstractCommandManager.getCommands()) {
                PluginCommand pluginCommand = Bukkit.getPluginCommand(command);

                if (pluginCommand.isRegistered()) {
                    loggerUtils.info(plugin, "Command %% was registered by %%", command, pluginCommand.getPlugin().getName());

                    Bukkit.getPluginCommand(command).setExecutor(abstractCommandManager);
                } else
                    loggerUtils.info(plugin, "Command %% can't be register by %%", command, pluginCommand.getPlugin().getName());
            }
        }
    }
}
