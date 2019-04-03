package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.interfaces.IPluginsManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PluginsManager implements IPluginsManager {
    private Map<String, Boolean> modules = new HashMap<>();

    public void reloadPlugins() {
        schedulerManager.clearScheduler();

        corePlugin.registerPlugin(corePlugin, corePlugin);

        this.checkingModules();

        for (String key : modules.keySet()) {
            disablePlugin(getPlugin(key));
            enablePlugin(getPlugin(key));
        }
    }

    public void disablingPlugins() {
        for (String key : modules.keySet())
            disablePlugin(getPlugin(key));
    }

    public void checkingModules() {
        Iterator<Map.Entry<String, Boolean>> it = modules.entrySet().iterator();
        Map<String, Boolean> newMap = new HashMap<String, Boolean>();

        while (it.hasNext()) {
            final Map.Entry<String, Boolean> key = it.next();

            if (this.getPlugin(key.getKey()) != null) {
                if (key.getValue()) {
                    newMap.put(key.getKey(), key.getValue());

                    loggerUtils.info(corePlugin, key.getKey() + " найден! =)");
                } else {
                    Bukkit.getScheduler().runTask(corePlugin, () -> this.disablePlugin(bukkitPluginManager.getPlugin(key.getKey())));

                    loggerUtils.info(corePlugin, key.getKey() + " найден и выключен! =|");
                }
            } else {
                it.remove();

                loggerUtils.info(corePlugin, key.getKey() + " не найден! =(");
            }
        }

        this.modules = newMap;
    }

    public boolean isEnabledPlugin(String plugin) {
        return bukkitPluginManager.isPluginEnabled(plugin);
    }

    public boolean isEnabledPlugin(Plugin plugin) {
        return bukkitPluginManager.isPluginEnabled(plugin);
    }

    public Plugin getPlugin(String plugin) {
        return bukkitPluginManager.getPlugin(plugin);
    }

    public void disablePlugin(Plugin plugin) {
        bukkitPluginManager.disablePlugin(plugin);
    }

    public void enablePlugin(Plugin plugin) {
        bukkitPluginManager.enablePlugin(plugin);
    }

    public void put(String key, boolean flag) {
        this.modules.put(key, flag);
    }
}
