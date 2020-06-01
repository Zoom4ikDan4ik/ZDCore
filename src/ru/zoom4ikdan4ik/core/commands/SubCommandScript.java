package ru.zoom4ikdan4ik.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.io.IOException;

public final class SubCommandScript implements ISubCommandManager, IBase {
    @Override
    public final boolean onlyPlayer() {
        return false;
    }

    @Override
    public final IPermissions getPermission() {
        return PermissionsEnum.SCRIPT;
    }

    @Override
    public final int getArgsLength() {
        return 1;
    }

    @Override
    public final void onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
        try {
            this.coreMethods.useScripts(strings[1]);
            this.coreMethods.sendMessage(commandSender, "&aStarted...");
        } catch (IOException error) {
            this.loggerUtils.info(this.corePlugin, error.getMessage());

            this.coreMethods.sendMessage(commandSender, "&cError: " + error.getMessage());
        }
    }
}
