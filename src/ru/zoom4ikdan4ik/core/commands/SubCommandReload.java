package ru.zoom4ikdan4ik.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class SubCommandReload implements ISubCommandManager, IBase {

    @Override
    public boolean onlyPlayer() {
        return false;
    }

    @Override
    public IPermissions getPermission() {
        return PermissionsEnum.RELOAD;
    }

    @Override
    public int getArgsLength() {
        return 0;
    }

    @Override
    public void onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        this.pluginsManager.reloadPlugins();

        this.coreMethods.sendMessage(commandSender, "&aThe plugins was reloaded!");
    }
}
