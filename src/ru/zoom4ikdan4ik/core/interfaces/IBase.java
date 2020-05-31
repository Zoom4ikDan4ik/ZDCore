package ru.zoom4ikdan4ik.core.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import ru.zoom4ikdan4ik.core.Core;
import ru.zoom4ikdan4ik.core.api.methods.BukkitMethods;
import ru.zoom4ikdan4ik.core.api.methods.CoreMethods;
import ru.zoom4ikdan4ik.core.api.methods.PermissionsMethods;
import ru.zoom4ikdan4ik.core.managers.*;
import ru.zoom4ikdan4ik.core.runnables.PluginsManagerRunnable;
import ru.zoom4ikdan4ik.core.utils.ConfigUtils;
import ru.zoom4ikdan4ik.core.utils.LoggerUtils;

/**
 * Interface for any use
 */
public interface IBase {
    PluginManager bukkitPluginManager = Bukkit.getPluginManager();

    Core corePlugin = (Core) bukkitPluginManager.getPlugin("ZDCore");

    CommandManager commandManager = new CommandManager();
    ConfigManager configManager = new ConfigManager(corePlugin);
    MySQLManager mySQLManager = new MySQLManager();
    PluginsManager pluginsManager = new PluginsManager();
    SchedulerManager schedulerManager = new SchedulerManager();

    BukkitMethods bukkitMethods = new BukkitMethods();
    CoreMethods coreMethods = new CoreMethods();
    PermissionsMethods permissionsMethods = new PermissionsMethods();

    PluginsManagerRunnable pluginManagerRunnable = new PluginsManagerRunnable();

    LoggerUtils loggerUtils = new LoggerUtils();
    ConfigUtils configUtils = new ConfigUtils();
}
