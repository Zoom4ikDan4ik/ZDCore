package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.RegistrationAPI;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class PluginsManager implements IBase {
    private Map<String, Boolean> modules = new HashMap<>();

    public final void reloadPlugins() {
        RegistrationAPI.registerPlugin(this.corePlugin, this.corePlugin);

        this.checkingModules();

        for (String key : this.modules.keySet()) {
            Plugin plugin = this.bukkitPluginManager.getPlugin(key);

            this.bukkitPluginManager.disablePlugin(plugin);

            AbstractSchedulerManager abstractSchedulerManager = RegistrationAPI.getPlugins().get(plugin).getSchedulerManager();

            abstractSchedulerManager.stopSchedulers();
            abstractSchedulerManager.clearSchedulerRunnable();

            this.bukkitPluginManager.enablePlugin(plugin);
        }
    }

    public final void disablingPlugins() {
        for (String key : this.modules.keySet())
            this.bukkitPluginManager.disablePlugin(this.bukkitPluginManager.getPlugin(key));
    }

    public final void checkingModules() {
        Iterator<Map.Entry<String, Boolean>> iterator = this.modules.entrySet().iterator();
        Map<String, Boolean> newMap = new HashMap<String, Boolean>();

        while (iterator.hasNext()) {
            final Map.Entry<String, Boolean> key = iterator.next();

            final Plugin plugin = this.bukkitPluginManager.getPlugin(key.getKey());
            final boolean isEnabled = key.getValue();

            if (plugin != null) {
                if (isEnabled) {
                    newMap.put(plugin.getName(), true);

                    this.loggerUtils.info(this.corePlugin, "%% found! =)", plugin.getName());
                } else {
                    Bukkit.getScheduler().runTask(this.corePlugin, () -> this.bukkitPluginManager.disablePlugin(plugin));

                    this.loggerUtils.info(this.corePlugin, "%% found and disabled! =|", plugin.getName());
                }
            } else {
                iterator.remove();

                this.loggerUtils.info(this.corePlugin, "%% not found! =(", plugin.getName());
            }
        }

        this.modules = newMap;
    }

    public final void put(final String key, final boolean flag) {
        this.modules.put(key, flag);
    }
}
