package ru.zoom4ikdan4ik.core.api.managers;

import com.sun.istack.internal.NotNull;
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

    public final synchronized void setSchedulerRunnables(final List<SchedulerRunnable> schedulerRunnables) {
        this.schedulerRunnables = schedulerRunnables;
    }

    public final synchronized boolean addSchedulerRunnable(@NotNull final SchedulerRunnable schedulerRunnable) {
        return this.schedulerRunnables.add(schedulerRunnable);
    }

    public final synchronized boolean removeRunnable(@NotNull final Runnable runnable) {
        SchedulerRunnable schedulerRunnable = this.getSchedulerRunnableOfRunnable(runnable);

        if (schedulerRunnable != null)
            if (!schedulerRunnable.getRunnables().isEmpty())
                return schedulerRunnable.removeScheduler(runnable);
            else return false;
        else return false;
    }

    public final synchronized SchedulerRunnable getSchedulerRunnableOfRunnable(@NotNull final Runnable runnable) {
        for (SchedulerRunnable schedulerRunnable : this.getSchedulerRunnables())
            if (schedulerRunnable.getRunnables().contains(runnable))
                return schedulerRunnable;

        return null;
    }

    public final synchronized boolean removeSchedulerRunnable(final SchedulerRunnable schedulerRunnable) {
        return this.schedulerRunnables.remove(schedulerRunnable);
    }

    public final synchronized void clearSchedulerRunnable() {
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
            this(new ArrayList<>(Arrays.asList(runnable)));
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

        public final synchronized List<Runnable> getRunnables() {
            return this.runnables;
        }

        public final synchronized void setRunnables(List<Runnable> runnables) {
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

        public final synchronized void clearScheduler() {
            this.runnables.clear();
        }

        public final synchronized boolean removeScheduler(@NotNull final Runnable runnable) {
            return this.runnables.remove(runnable);
        }

        public final synchronized boolean addScheduler(@NotNull final Runnable runnable) {
            return this.runnables.add(runnable);
        }
    }
}
