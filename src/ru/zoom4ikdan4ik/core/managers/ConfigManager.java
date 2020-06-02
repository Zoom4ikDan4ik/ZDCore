package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.World;
import ru.zoom4ikdan4ik.core.api.interfaces.ICore;
import ru.zoom4ikdan4ik.core.api.managers.AbstractConfigManager;
import ru.zoom4ikdan4ik.core.enums.CommandsEnum;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;

public final class ConfigManager extends AbstractConfigManager {
    public String server, scripts_path;
    public World main_world;

    public ConfigManager(ICore plugin) {
        super(plugin);
    }

    @Override
    public final void loadConfig() {
        this.server = this.getString("Config.server", "server");
        this.main_world = this.getWorld("Config.main_world", this.bukkitMethods.getWorlds().get(0).getName());
        this.scripts_path = this.getString("Config.scripts_path", "/home/scripts/");

        this.registerCommands(CommandsEnum.values());
        this.registerMessages(MessagesEnum.values());
        this.registerPermissions(PermissionsEnum.values());
    }
}
