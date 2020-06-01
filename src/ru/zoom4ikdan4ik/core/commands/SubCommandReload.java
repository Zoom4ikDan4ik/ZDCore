package ru.zoom4ikdan4ik.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public final class SubCommandReload implements ISubCommandManager, IBase {

    @Override
    public final boolean onlyPlayer() {
        return false;
    }

    @Override
    public final IPermissions getPermission() {
        return PermissionsEnum.RELOAD;
    }

    @Override
    public final int getArgsLength() {
        return 0;
    }

    @Override
    public final void onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        this.pluginsManager.reloadPlugins();

        this.coreMethods.sendMessage(commandSender, "&aThe plugins was reloaded!");
    }
}
