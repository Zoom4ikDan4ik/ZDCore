package ru.zoom4ikdan4ik.core.runnables;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.RegistrationAPI;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PluginsManagerRunnable implements Runnable, IBase {

    @Override
    public void run() {
        for (Plugin plugin : RegistrationAPI.getPlugins().keySet())
            if (!this.configUtils.isSet(this.configManager.getConfig(), "Modules." + plugin.getName()))
                this.configUtils.setValue(this.configManager.getConfig(), "Modules." + plugin.getName(), true);

        this.configUtils.save(this.corePlugin);

        ConfigurationSection modules = this.configUtils.getConfigurationSection(this.configManager.getConfig(), "Modules");

        if (modules != null)
            for (String key : modules.getKeys(false))
                this.pluginsManager.put(key, modules.getBoolean(key));

        this.loggerUtils.info(this.corePlugin, "Checking modules...");

        this.pluginsManager.checkingModules();

        this.schedulerManager.removeScheduler(this);
    }
}
