package ru.zoom4ikdan4ik.core.api.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import ru.zoom4ikdan4ik.core.api.interfaces.ISchedulerManager;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractSchedulerManager implements ISchedulerManager, IBase {
    private final Plugin plugin;
    private final BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    private List<SchedulerRunnable> schedulerRunnables = new ArrayList<>();

    public AbstractSchedulerManager(final Plugin plugin) {
        this.plugin = plugin;
    }

    public final void startScheduler() {
        for (SchedulerRunnable schedulerRunnable : this.getSchedulerRunnables())
            if (schedulerRunnable.isActive())
                this.scheduler.scheduleSyncRepeatingTask(this.plugin, () -> {
                    for (Runnable runnable : schedulerRunnable.getRunnables())
                        scheduler.runTask(plugin, runnable);
                }, schedulerRunnable.getDelay(), schedulerRunnable.getPeriod());
    }

    public final synchronized List<SchedulerRunnable> getSchedulerRunnables() {
        return this.schedulerRunnables;
    }

    public final void setSchedulerRunnables(List<SchedulerRunnable> schedulerRunnables) {
        this.schedulerRunnables = schedulerRunnables;
    }

    public final boolean addSchedulerRunnable(SchedulerRunnable schedulerRunnable) {
        return this.schedulerRunnables.add(schedulerRunnable);
    }

    public final boolean removeRunnable(Runnable runnable) {
        SchedulerRunnable schedulerRunnable = this.getSchedulerRunnableOfRunnable(runnable);

        if (schedulerRunnable != null)
            return schedulerRunnable.getRunnables().remove(runnable);
        else return false;
    }

    public final synchronized SchedulerRunnable getSchedulerRunnableOfRunnable(Runnable runnable) {
        for (SchedulerRunnable schedulerRunnable : this.getSchedulerRunnables())
            if (schedulerRunnable.getRunnables().contains(runnable))
                return schedulerRunnable;

        return null;
    }

    public final boolean removeSchedulerRunnable(SchedulerRunnable schedulerRunnable) {
        return this.schedulerRunnables.remove(schedulerRunnable);
    }

    public final void clearSchedulerRunnable() {
        this.schedulerRunnables.clear();
    }

    public final void stopSchedulers() {
        this.scheduler.cancelTasks(this.plugin);
    }

    public final class SchedulerRunnable {
        private boolean active;
        private List<Runnable> runnables;
        private long delay;
        private long period;

        public SchedulerRunnable() {
            this(new ArrayList<>());
        }

        public SchedulerRunnable(final Runnable runnable) {
            this(Arrays.asList(runnable));
        }

        public SchedulerRunnable(final List<Runnable> runnables) {
            this(runnables, 0, 20);
        }

        public SchedulerRunnable(final List<Runnable> runnables, final long delay, final long period) {
            this.active = true;
            this.runnables = runnables;
            this.delay = delay;
            this.period = period;
        }

        public final boolean isActive() {
            return this.active;
        }

        public final void setActive(final boolean active) {
            this.active = active;
        }

        public final List<Runnable> getRunnables() {
            return this.runnables;
        }

        public final void setRunnables(List<Runnable> runnables) {
            this.runnables = runnables;
        }

        public final long getDelay() {
            return this.delay;
        }

        public final void setDelay(final long delay) {
            this.delay = delay;
        }

        public final long getPeriod() {
            return this.period;
        }

        public final void setPeriod(final long period) {
            this.period = period;
        }

        public final void clearScheduler() {
            this.runnables.clear();
        }

        public final void removeScheduler(final Runnable runnable) {
            this.runnables.remove(runnable);
        }

        public final void addScheduler(final Runnable runnable) {
            this.runnables.add(runnable);
        }
    }
}
