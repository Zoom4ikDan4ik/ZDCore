package ru.zoom4ikdan4ik.core;

import org.bukkit.plugin.java.JavaPlugin;
import ru.zoom4ikdan4ik.core.api.RegistrationAPI;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.api.managers.AbstractCommandManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractConfigManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSQLManager;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class for ZDCore
 */
public final class Core extends JavaPlugin implements ICore, IBase {

    /**
     * Default enabling plugin
     */
    @Override
    public final void onEnable() {
        RegistrationAPI.registerPlugin(this.corePlugin, this.corePlugin);
    }

    /**
     * Default disabling plugin
     */
    @Override
    public final void onDisable() {
        this.pluginsManager.disablingPlugins();
    }

    @Override
    public final List<AbstractConfigManager> getConfigManagers() {
        return new ArrayList<>(Arrays.asList(this.configManager));
    }

    @Override
    public final AbstractSQLManager getSQLManager() {
        return null;
    }

    @Override
    public final AbstractSchedulerManager getSchedulerManager() {
        return this.schedulerManager;
    }

    @Override
    public final AbstractCommandManager getCommandManager() {
        return this.commandManager;
    }
}
