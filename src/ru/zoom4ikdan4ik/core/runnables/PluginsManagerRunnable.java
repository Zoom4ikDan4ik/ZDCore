package ru.zoom4ikdan4ik.core.runnables;

import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PluginsManagerRunnable implements Runnable, IBase {

    @Override
    public void run() {
        for (Plugin plugin : corePlugin.plugins.keySet())
            if (this.configManager.getConfig().get("Modules." + plugin.getName()) == null)
                this.configManager.getConfig().set("Modules." + plugin.getName(), true);

        this.configManager.saveConfig();

        for (String key : this.configManager.getConfig().getConfigurationSection("Modules").getKeys(false))
            this.pluginsManager.put(key, this.configManager.getConfig().getConfigurationSection("Modules").getBoolean(key));

        this.loggerUtils.info(this.corePlugin, "Checking addons...");

        this.pluginsManager.checkingModules();

        this.schedulerManager.removeScheduler(this);
    }
}
