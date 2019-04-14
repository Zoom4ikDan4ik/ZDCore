package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

import java.io.File;

public class ConfigManager implements IConfigManager {
    private File file;
    private FileConfiguration config;

    private String mysql_host, mysql_database, mysql_user, mysql_password;
    private int mysql_port;

    public String server, scripts_path;

    public World main_world;

    @Override
    public void loadConfig() {
        this.file = coreMethods.createConfigYML("core", corePlugin);
        this.config = YamlConfiguration.loadConfiguration(this.file);

        this.server = configUtils.getString(this.config, "Config.server", "server");
        this.main_world = configUtils.getWorld(this.config, "Config.main_world", bukkitMethods.getWorlds().get(0).getName());
        this.scripts_path = configUtils.getString(this.config, "Config.scripts_path", "/home/scripts/");

        this.mysql_host = configUtils.getString(this.config, "MySQL.host", "localhost");
        this.mysql_port = configUtils.getInt(this.config, "MySQL.port", 3306);
        this.mysql_database = configUtils.getString(this.config, "MySQL.database", "root");
        this.mysql_user = configUtils.getString(this.config, "MySQL.user", "root");
        this.mysql_password = configUtils.getString(this.config, "MySQL.password", "password");

        mySQLManager.getConnection(this.mysql_host, this.mysql_port, this.mysql_database, this.mysql_user, this.mysql_password);
    }

    @Override
    public void saveConfig() {
        configUtils.save(corePlugin, this.getConfig(), this.getFileConfig());
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
