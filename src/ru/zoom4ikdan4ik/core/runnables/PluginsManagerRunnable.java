package ru.zoom4ikdan4ik.core.runnables;

import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PluginsManagerRunnable implements Runnable, IBase {

    @Override
    public void run() {
        for (Plugin plugin : corePlugin.plugins.keySet())
            if (!this.configUtils.isSet(this.configManager.getConfig(), "Modules." + plugin.getName()))
                this.configUtils.setValue(this.configManager.getConfig(), "Modules." + plugin.getName(), true);

        this.configUtils.save(this.corePlugin);

        for (String key : this.configUtils.getConfigurationSection(this.configManager.getConfig(), "Modules").getKeys(false))
            this.pluginsManager.put(key, this.configUtils.getConfigurationSection(this.configManager.getConfig(), "Modules").getBoolean(key));

        this.loggerUtils.info(this.corePlugin, "Checking addons...");

        this.pluginsManager.checkingModules();

        this.schedulerManager.removeScheduler(this);
    }
}
