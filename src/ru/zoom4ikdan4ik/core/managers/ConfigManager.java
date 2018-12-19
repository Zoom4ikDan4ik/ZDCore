package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.zoom4ikdan4ik.core.interfaces.IConfigManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager implements IConfigManager {

    public File file;
    public FileConfiguration config;
    public String Server, ScriptsPath;
    private String Host, Port, Database, User, Password;
    public World MainWorld;
    public List<String> Messages = new ArrayList<>();

    @Override
    public void loadConfig() {
        file = coreMethods.createConfigYML("core", corePlugin);
        config = YamlConfiguration.loadConfiguration(file);

        Server = configUtils.getString(config, "Config.Server", "server");
        MainWorld = configUtils.getWorld(config, "Config.MainWorld", "world");
        ScriptsPath = configUtils.getString(config, "Config.ScriptsPath", "/home/scripts/");
        Messages = configUtils.getStringList(config, "Config.Messages", Messages);

        Host = configUtils.getString(config, "MySQL.Host", "localhost");
        Port = configUtils.getString(config, "MySQL.Port", "3306");
        Database = configUtils.getString(config, "MySQL.Database", "root");
        User = configUtils.getString(config, "MySQL.User", "root");
        Password = configUtils.getString(config, "MySQL.Password", "password");

        mySQLManager.getConnection(Host, Port, Database, User, Password);

        this.save();
    }

    public void save() {
        configUtils.save(corePlugin, config, file);
    }
}
