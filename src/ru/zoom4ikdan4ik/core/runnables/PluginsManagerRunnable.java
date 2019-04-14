package ru.zoom4ikdan4ik.core.runnables;

import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PluginsManagerRunnable implements Runnable, IBase {

    @Override
    public void run() {
        for (Plugin plugin : corePlugin.plugins.keySet())
            if (configManager.getConfig().get("Modules." + plugin.getName()) == null)
                configManager.getConfig().set("Modules." + plugin.getName(), true);

        configManager.saveConfig();

        for (String key : configManager.getConfig().getConfigurationSection("Modules").getKeys(false))
            pluginsManager.put(key, configManager.getConfig().getConfigurationSection("Modules").getBoolean(key));

        loggerUtils.info(corePlugin, "Checking addons...");

        pluginsManager.checkingModules();

        schedulerManager.removeScheduler(this);
    }
}
