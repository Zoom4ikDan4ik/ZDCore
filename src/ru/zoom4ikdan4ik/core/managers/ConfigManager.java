package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager implements IConfigManager {
    private File file;
    private FileConfiguration config;

    private String mysql_host, mysql_port, mysql_database, mysql_user, mysql_password;
    public String server, scripts_path;

    public World main_world;

    @Override
    public void loadConfig() {
        this.file = coreMethods.createConfigYML("core", corePlugin);
        this.config = YamlConfiguration.loadConfiguration(this.file);

        this.server = configUtils.getString(this.config, "Config.server", "server");
        this.main_world = configUtils.getWorld(this.config, "Config.Main_world", bukkitMethods.getWorlds().get(0).getName());
        this.scripts_path = configUtils.getString(this.config, "Config.scripts_path", "/home/scripts/");

        this.mysql_host = configUtils.getString(this.config, "MySQL.mysql_host", "localhost");
        this.mysql_port = configUtils.getString(this.config, "MySQL.mysql_port", "3306");
        this.mysql_database = configUtils.getString(this.config, "MySQL.mysql_database", "root");
        this.mysql_user = configUtils.getString(this.config, "MySQL.mysql_user", "root");
        this.mysql_password = configUtils.getString(this.config, "MySQL.mysql_password", "password");

        mySQLManager.getConnection(this.mysql_host, this.mysql_port, this.mysql_database, this.mysql_user, this.mysql_password);
    }

    @Override
    public void saveConfig() {
        configUtils.save(corePlugin);
    }

    @Override
    public FileConfiguration getConfig() {
        return this.config;
    }

    @Override
    public File getFileConfig() {
        return this.file;
    }
}
