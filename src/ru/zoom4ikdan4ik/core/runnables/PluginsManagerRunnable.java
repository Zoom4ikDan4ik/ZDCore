package ru.zoom4ikdan4ik.core.runnables;

import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.Core;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PluginsManagerRunnable implements Runnable, IBase {

    @Override
    public void run() {
        for (Plugin plugin : Core.plugins.keySet())
            if (configManager.config.get("Modules." + plugin.getName()) == null)
                configManager.config.set("Modules." + plugin.getName(), true);

        configManager.save();

        for (String key : configManager.config.getConfigurationSection("Modules").getKeys(false))
            pluginsManager.put(key, configManager.config.getConfigurationSection("Modules").getBoolean(key));

        loggerUtils.info(corePlugin, "Checking addons...");

        pluginsManager.checkingModules();

        schedulerManager.removeScheduler(this);
    }
}
