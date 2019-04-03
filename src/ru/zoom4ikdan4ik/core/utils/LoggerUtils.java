package ru.zoom4ikdan4ik.core.utils;

import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public class LoggerUtils {
    private final Logger log = Logger.getLogger("Minecraft");

    public void info(Plugin plugin, String msg) {
        this.log.info("[" + plugin.getName() + "]" + " " + msg);
    }
}
