package ru.zoom4ikdan4ik.core.interfaces;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * Interface for structure of config
 */
public interface IConfigManager extends IBase {

    void loadConfig();

    void saveConfig();

    void setConfig(FileConfiguration config);

    void setFileConfig(File file);

    FileConfiguration getConfig();

    File getFileConfig();

    String nameConfig();
}
