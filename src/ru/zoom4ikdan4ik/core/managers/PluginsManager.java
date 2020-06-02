package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.RegistrationAPI;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.List;

public final class PluginsManager implements IBase {
    private List<PluginActivity> pluginActivities = new ArrayList<>();

    public final List<PluginActivity> getPluginActivities() {
        return this.pluginActivities;
    }

    public final void setPluginActivities(final List<PluginActivity> pluginActivities) {
        this.pluginActivities = pluginActivities;
    }

    public final boolean addPluginActivities(PluginActivity pluginActivity) {
        return this.pluginActivities.add(pluginActivity);
    }

    public final boolean removePluginActivities(PluginActivity pluginActivity) {
        return this.pluginActivities.remove(pluginActivity);
    }

    public final void reloadPlugins() {
        RegistrationAPI.registerPlugin(this.corePlugin, this.corePlugin);

        this.checkingModules();

        for (PluginActivity pluginActivity : this.getPluginActivities()) {
            Plugin plugin = pluginActivity.getPlugin();

            AbstractSchedulerManager abstractSchedulerManager = RegistrationAPI.getPlugins().get(plugin).getSchedulerManager();

            abstractSchedulerManager.stopSchedulers();
            abstractSchedulerManager.clearSchedulerRunnable();

            this.bukkitPluginManager.disablePlugin(plugin);
            this.bukkitPluginManager.enablePlugin(plugin);
        }
    }

    public final void disablingPlugins() {
        for (PluginActivity pluginActivity : this.getPluginActivities()) {
            Plugin plugin = pluginActivity.getPlugin();

            if (plugin != null)
                this.bukkitPluginManager.disablePlugin(pluginActivity.getPlugin());
        }
    }

    public final void checkingModules() {
        this.reloadModules();

        List<PluginActivity> pluginActivities = new ArrayList<>();

        for (PluginActivity pluginActivity : this.getPluginActivities()) {
            Plugin plugin = pluginActivity.getPlugin();

            if (plugin != null)
                if (pluginActivity.isActive()) {
                    pluginActivities.add(pluginActivity);

                    this.loggerUtils.info(this.corePlugin, "%% found! =)", plugin.getName());
                } else {
                    Bukkit.getScheduler().runTask(this.corePlugin, () -> this.bukkitPluginManager.disablePlugin(plugin));

                    this.loggerUtils.info(this.corePlugin, "%% found and disabled! =|", plugin.getName());
                }
            else
                this.loggerUtils.info(this.corePlugin, "%% not found! =(", plugin.getName());
        }

        this.pluginActivities = pluginActivities;
    }

    public final void reloadModules() {
        ConfigurationSection modules = this.configManager.getConfigurationSection("Modules");

        if (modules != null)
            for (final String key : modules.getKeys(false))
                this.put(key, modules.getBoolean(key));
    }

    public final void put(final String key, final boolean flag) {
        this.addPluginActivities(new PluginActivity(this.bukkitPluginManager.getPlugin(key), flag));
    }

    public final class PluginActivity {
        private final Plugin plugin;
        private boolean active;

        public PluginActivity(final Plugin plugin) {
            this.plugin = plugin;
            this.active = true;
        }

        public PluginActivity(final Plugin plugin, final boolean active) {
            this.plugin = plugin;
            this.active = active;
        }

        public final Plugin getPlugin() {
            return this.plugin;
        }

        public final boolean isActive() {
            return this.active;
        }

        public final void setActive(final boolean active) {
            this.active = active;
        }
    }
}
