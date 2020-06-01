package ru.zoom4ikdan4ik.core.api.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import ru.zoom4ikdan4ik.core.api.interfaces.ISchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSchedulerManager extends Thread implements ISchedulerManager, IBase {
    private final Plugin plugin;
    private final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    private final List<Runnable> runnableList = new ArrayList<>();

    public AbstractSchedulerManager(final Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public final void run() {
        this.scheduler.scheduleSyncRepeatingTask(this.plugin, () -> {
            for (Runnable runnable : this.runnableList)
                this.scheduler.runTask(this.plugin, runnable);
        }, 0L, 20L);
    }

    public final void startScheduler() {
        if (this.isAlive())
            return;

        this.setName(String.format("%s scheduler thread", this.plugin.getName()));
        this.run();
    }

    public final void stopScheduler() {
        this.scheduler.cancelTasks(this.plugin);
    }

    public final void clearScheduler() {
        this.runnableList.clear();
    }

    public final void removeScheduler(final Runnable runnable) {
        this.runnableList.remove(runnable);
    }

    public final void addScheduler(final Runnable runnable) {
        this.runnableList.add(runnable);
    }
}
