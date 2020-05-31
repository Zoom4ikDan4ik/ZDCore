package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import ru.zoom4ikdan4ik.core.api.interfaces.ISchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.List;

public class SchedulerManager extends Thread implements ISchedulerManager, IBase {
    private final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    private final List<Runnable> runnables = new ArrayList<>();

    @Override
    public void run() {
        this.scheduler.scheduleSyncRepeatingTask(this.corePlugin, () -> {
            for (Runnable runnable : this.runnables)
                this.scheduler.runTask(this.corePlugin, runnable);

            this.mySQLManager.commit();
        }, 0L, 20L);
    }

    public void startScheduler() {
        if (this.isAlive())
            return;

        this.setName("ZDCore scheduler thread");
        this.run();
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
