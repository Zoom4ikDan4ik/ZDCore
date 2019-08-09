package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.ISchedulerManager;

import java.util.ArrayList;
import java.util.List;

public class SchedulerManager extends Thread implements ISchedulerManager, IBase {
    private BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    private List<Runnable> runnables = new ArrayList<>();

    public void startScheduler() {
        if (this.isAlive())
            return;

        this.setName("ZDCore scheduler thread");
        this.run();
    }

    @Override
    public void run() {
        this.scheduler.scheduleSyncRepeatingTask(this.corePlugin, () -> {
            for (Runnable runnable : this.runnables)
                this.scheduler.runTask(this.corePlugin, runnable);
        }, 0L, 20L);
    }

    public void stopScheduler() {
        this.scheduler.cancelTasks(this.corePlugin);
    }

    public void clearScheduler() {
        this.runnables.clear();
    }

    public void removeScheduler(Runnable runnable) {
        this.runnables.remove(runnable);
    }

    public void addScheduler(Runnable runnable) {
        this.runnables.add(runnable);
    }

    @Override
    public void addScheduler() {
        this.schedulerManager.addScheduler(this.pluginManagerRunnable);
    }
}
