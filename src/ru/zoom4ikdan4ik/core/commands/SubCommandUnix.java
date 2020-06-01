package ru.zoom4ikdan4ik.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public final class SubCommandUnix implements ISubCommandManager, IBase {
    @Override
    public final boolean onlyPlayer() {
        return false;
    }

    @Override
    public final IPermissions getPermission() {
        return PermissionsEnum.UNIX;
    }

    @Override
    public final int getArgsLength() {
        return 0;
    }

    @Override
    public final void onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
        this.coreMethods.sendMessage(commandSender, "&aUnix time: " + this.coreMethods.getUnixTime());
    }
}
