package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.managers.AbstractSchedulerManager;

public final class SchedulerManager extends AbstractSchedulerManager {
    public SchedulerManager(Plugin plugin) {
        super(plugin);
    }

    @Override
    public final void addScheduler() {
        this.schedulerManager.addSchedulerRunnable(new SchedulerRunnable(this.pluginManagerRunnable));
    }
}
