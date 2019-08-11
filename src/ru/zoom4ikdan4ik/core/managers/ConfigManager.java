package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import ru.zoom4ikdan4ik.core.interfaces.IBase;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

import java.io.File;

public class ConfigManager implements IConfigManager, IBase {
    private File file;
    private FileConfiguration config;

    private String mysql_host, mysql_database, mysql_user, mysql_password;
    private int mysql_port;

    public String server, scripts_path;

    public World main_world;

    @Override
    public void loadConfig() {
        this.server = this.configUtils.getString(this.config, "Config.server", "server");
        this.main_world = this.configUtils.getWorld(this.config, "Config.main_world", this.bukkitMethods.getWorlds().get(0).getName());
        this.scripts_path = this.configUtils.getString(this.config, "Config.scripts_path", "/home/scripts/");

        this.mysql_host = this.configUtils.getString(this.config, "MySQL.host", "localhost");
        this.mysql_port = this.configUtils.getInt(this.config, "MySQL.port", 3306);
        this.mysql_database = this.configUtils.getString(this.config, "MySQL.database", "root");
        this.mysql_user = this.configUtils.getString(this.config, "MySQL.user", "root");
        this.mysql_password = this.configUtils.getString(this.config, "MySQL.password", "password");
    }

    public void setConnection() {
        this.mySQLManager.getConnection(this.mysql_host, this.mysql_port, this.mysql_database, this.mysql_user, this.mysql_password);
    }

    @Override
    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public void setFileConfig(File file) {
        this.file = file;
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
