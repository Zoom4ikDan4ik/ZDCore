package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.IPluginsManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PluginsManager implements IPluginsManager, IBase {
    private Map<String, Boolean> modules = new HashMap<>();

    public void reloadPlugins() {
        this.schedulerManager.clearScheduler();

        this.corePlugin.registerPlugin(this.corePlugin, this.corePlugin);

        this.checkingModules();

        for (String key : this.modules.keySet()) {
            Plugin plugin = this.bukkitPluginManager.getPlugin(key);

            this.bukkitPluginManager.disablePlugin(plugin);
            this.bukkitPluginManager.enablePlugin(plugin);
        }
    }

    public void disablingPlugins() {
        for (String key : this.modules.keySet())
            this.bukkitPluginManager.enablePlugin(this.bukkitPluginManager.getPlugin(key));
    }

    public void checkingModules() {
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

    public void put(String key, boolean flag) {
        this.modules.put(key, flag);
    }
}
