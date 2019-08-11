package ru.zoom4ikdan4ik.core.interfaces;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * Interface for structure of config
 */
public interface IConfigManager {
    void loadConfig();

    FileConfiguration getConfig();

    void setConfig(FileConfiguration config);
    
    File getFileConfig();

    void setFileConfig(File file);
}
