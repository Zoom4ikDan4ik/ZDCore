package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.managers.AbstractConfigManager;
import ru.zoom4ikdan4ik.core.enums.CommandsEnum;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;

public final class ConfigManager extends AbstractConfigManager {
    public String server, scripts_path;
    public World main_world;

    public ConfigManager(Plugin plugin) {
        super(plugin);
    }

    @Override
    public final void loadConfig() {
        this.server = this.getString("Server", "server");
        this.main_world = this.getWorld("Main world", this.bukkitMethods.getWorlds().get(0).getName());
        this.scripts_path = this.getString("Script path", "/home/scripts/");

        this.registerCommands(CommandsEnum.values());
        this.registerMessages(MessagesEnum.values());
        this.registerPermissions(PermissionsEnum.values());
    }
}
