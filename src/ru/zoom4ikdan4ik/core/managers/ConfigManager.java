package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.api.managers.AbstractConfigManager;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;

public class ConfigManager extends AbstractConfigManager {
    public String server, scripts_path;
    public World main_world;
    private String mysql_host, mysql_database, mysql_user, mysql_password;
    private int mysql_port;

    public ConfigManager(ICore plugin) {
        super(plugin);
    }

    @Override
    public void loadConfig() {
        this.server = this.getString("Config.server", "server");
        this.main_world = this.getWorld("Config.main_world", this.bukkitMethods.getWorlds().get(0).getName());
        this.scripts_path = this.getString("Config.scripts_path", "/home/scripts/");

        this.mysql_host = this.getString("MySQL.host", "localhost");
        this.mysql_port = this.getInt("MySQL.port", 3306);
        this.mysql_database = this.getString("MySQL.database", "root");
        this.mysql_user = this.getString("MySQL.user", "root");
        this.mysql_password = this.getString("MySQL.password", "password");

        this.registerMessages(MessagesEnum.values());
        this.registerPermissions(PermissionsEnum.values());
    }

    public void setConnection() {
        this.mySQLManager.getConnection(this.mysql_host, this.mysql_port, this.mysql_database, this.mysql_user, this.mysql_password);
    }
}
