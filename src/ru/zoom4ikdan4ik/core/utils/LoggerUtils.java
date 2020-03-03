package ru.zoom4ikdan4ik.core.utils;

import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class LoggerUtils {
    private final Logger log = Logger.getLogger("Minecraft");

    public void info(Plugin plugin, String message, Object... args) {
        for (Object object : args)
            message = message.replaceFirst("%%", String.valueOf(object));

        this.log.info("[" + plugin.getName() + "]" + " " + message);
    }
}
