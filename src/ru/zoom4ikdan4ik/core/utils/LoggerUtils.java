package ru.zoom4ikdan4ik.core.utils;

import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public final class LoggerUtils {
    private final Logger log = Logger.getLogger("Minecraft");

    public final void info(final Plugin plugin, String message, final Object... args) {
        for (Object object : args)
            message = message.replaceFirst("%%", String.valueOf(object));

        this.log.info("[" + plugin.getName() + "]" + " " + message);
    }
}
