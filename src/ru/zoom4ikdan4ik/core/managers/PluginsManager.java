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
            this.disablePlugin(getPlugin(key));

            this.enablePlugin(getPlugin(key));
        }
    }

    public void disablingPlugins() {
        for (String key : this.modules.keySet())
            this.disablePlugin(getPlugin(key));
    }

    public void checkingModules() {
        Iterator<Map.Entry<String, Boolean>> it = this.modules.entrySet().iterator();
        Map<String, Boolean> newMap = new HashMap<String, Boolean>();

        while (it.hasNext()) {
            final Map.Entry<String, Boolean> key = it.next();

            if (this.getPlugin(key.getKey()) != null) {
                if (key.getValue()) {
                    newMap.put(key.getKey(), key.getValue());

                    this.loggerUtils.info(this.corePlugin, key.getKey() + " found! =)");
                } else {
                    Bukkit.getScheduler().runTask(this.corePlugin, () -> this.disablePlugin(this.bukkitPluginManager.getPlugin(key.getKey())));

                    this.loggerUtils.info(this.corePlugin, key.getKey() + " found and disabled! =|");
                }
            } else {
                it.remove();

                this.loggerUtils.info(this.corePlugin, key.getKey() + " not found! =(");
            }
        }

        this.modules = newMap;
    }

    public boolean isEnabledPlugin(String plugin) {
        return this.bukkitPluginManager.isPluginEnabled(plugin);
    }

    public boolean isEnabledPlugin(Plugin plugin) {
        return this.bukkitPluginManager.isPluginEnabled(plugin);
    }

    public Plugin getPlugin(String plugin) {
        return this.bukkitPluginManager.getPlugin(plugin);
    }

    public void disablePlugin(Plugin plugin) {
        this.bukkitPluginManager.disablePlugin(plugin);
    }

    public void enablePlugin(Plugin plugin) {
        this.bukkitPluginManager.enablePlugin(plugin);
    }

    public void put(String key, boolean flag) {
        this.modules.put(key, flag);
    }
}
