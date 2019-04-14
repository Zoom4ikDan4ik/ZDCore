package ru.zoom4ikdan4ik.core.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import ru.zoom4ikdan4ik.core.Core;
import ru.zoom4ikdan4ik.core.managers.*;
import ru.zoom4ikdan4ik.core.methods.BukkitMethods;
import ru.zoom4ikdan4ik.core.methods.CoreMethods;
import ru.zoom4ikdan4ik.core.methods.PermissionsMethods;
import ru.zoom4ikdan4ik.core.runnables.PluginsManagerRunnable;
import ru.zoom4ikdan4ik.core.utils.ConfigUtils;
import ru.zoom4ikdan4ik.core.utils.LoggerUtils;

/**
 * Interface for any use
 */
public interface IBase {
    CommandManager commandManager = new CommandManager();
    ConfigManager configManager = new ConfigManager();
    MySQLManager mySQLManager = new MySQLManager();
    SQLiteManager sqliteManager = new SQLiteManager();
    PluginsManager pluginsManager = new PluginsManager();
    SchedulerManager schedulerManager = new SchedulerManager();
    PluginManager bukkitPluginManager = Bukkit.getPluginManager();

    BukkitMethods bukkitMethods = new BukkitMethods();
    CoreMethods coreMethods = new CoreMethods();
    PermissionsMethods permissionsMethods = new PermissionsMethods();

    PluginsManagerRunnable pluginManagerRunnable = new PluginsManagerRunnable();

    LoggerUtils loggerUtils = new LoggerUtils();
    ConfigUtils configUtils = new ConfigUtils();

    Core corePlugin = (Core) bukkitPluginManager.getPlugin("ZDCore");
}
