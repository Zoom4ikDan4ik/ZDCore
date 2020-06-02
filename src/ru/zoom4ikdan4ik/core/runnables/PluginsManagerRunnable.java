package ru.zoom4ikdan4ik.core.runnables;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.RegistrationAPI;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public final class PluginsManagerRunnable implements Runnable, IBase {

    @Override
    public final void run() {
        for (final Plugin plugin : RegistrationAPI.getPlugins().keySet())
            if (!this.configManager.isSet("Modules." + plugin.getName()))
                this.configManager.setValue("Modules." + plugin.getName(), true);

        this.configManager.save();

        ConfigurationSection modules = this.configManager.getConfigurationSection("Modules");

        if (modules != null)
            for (final String key : modules.getKeys(false))
                this.pluginsManager.put(key, modules.getBoolean(key));

        this.loggerUtils.info(this.corePlugin, "Checking modules...");

        this.pluginsManager.checkingModules();

        this.schedulerManager.removeRunnable(this);
    }
}
