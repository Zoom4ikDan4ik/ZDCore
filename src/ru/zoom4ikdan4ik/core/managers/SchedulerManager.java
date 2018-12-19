package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import ru.zoom4ikdan4ik.core.interfaces.ISchedulerManager;
import ru.zoom4ikdan4ik.core.runnables.PluginsManagerRunnable;

import java.util.ArrayList;
import java.util.List;

public class SchedulerManager extends Thread implements ISchedulerManager {

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
        scheduler.scheduleSyncRepeatingTask(corePlugin, () -> {
            for (Runnable runnable : runnables)
                scheduler.runTask(corePlugin, runnable);
        }, 0L, 20L);
    }

    public void stopScheduler() {
        scheduler.cancelTasks(corePlugin);
    }

    public void clearScheduler() {
        runnables.clear();
    }

    public void removeScheduler(Runnable runnable) {
        runnables.remove(runnable);
    }

    public void addScheduler(Runnable runnable) {
        runnables.add(runnable);
    }

    @Override
    public void addScheduler() {
        schedulerManager.addScheduler(new PluginsManagerRunnable());
    }

}
