package ru.zoom4ikdan4ik.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigUtils implements IBase {

    public String getString(FileConfiguration config, String path) {
        return config.getString(path);
    }

    public String getString(FileConfiguration config, String path, String option) {
        setPath(config, path, option);

        return config.getString(path);
    }

    public int getInt(FileConfiguration config, String path) {
        return config.getInt(path);
    }

    public int getInt(FileConfiguration config, String path, int option) {
        setPath(config, path, option);

        return config.getInt(path);
    }

    public boolean getBoolean(FileConfiguration config, String path) {
        return config.getBoolean(path);
    }

    public boolean getBoolean(FileConfiguration config, String path, boolean option) {
        setPath(config, path, option);

        return config.getBoolean(path);
    }

    public double getDouble(FileConfiguration config, String path) {
        return config.getDouble(path);
    }

    public double getDouble(FileConfiguration config, String path, double option) {
        setPath(config, path, option);

        return config.getDouble(path);
    }

    public long getLong(FileConfiguration config, String path) {
        return config.getLong(path);
    }

    public long getLong(FileConfiguration config, String path, long option) {
        setPath(config, path, option);

        return config.getLong(path);
    }

    public List<String> getStringList(FileConfiguration config, String path) {
        return config.getStringList(path);
    }

    public List<String> getStringList(FileConfiguration config, String path, List<String> option) {
        setPath(config, path, option);

        return config.getStringList(path);
    }

    public List<?> getList(FileConfiguration config, String path) {
        return config.getList(path);
    }

    public List<?> getList(FileConfiguration config, String path, List<?> option) {
        setPath(config, path, option);

        return config.getList(path);
    }

    public World getWorld(FileConfiguration config, String path) {
        return Bukkit.getWorld(config.getString(path));
    }

    public World getWorld(FileConfiguration config, String path, String option) {
        setPath(config, path, option);

        return Bukkit.getWorld(config.getString(path));
    }

    public void setPath(FileConfiguration config, String path, Object option) {
        if (!isSet(config, path))
            config.set(path, option);
    }

    public void setValue(FileConfiguration config, String path, Object option) {
        config.set(path, option);
    }

    public boolean isSet(FileConfiguration config, String path) {
        return config.isSet(path);
    }

    public void save(Plugin plugin, FileConfiguration cfg, File file) {
        try {
            cfg.save(file);
        } catch (IOException e) {
            loggerUtils.info(plugin, e.getMessage());
        }
    }
}
