package ru.zoom4ikdan4ik.core.api.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;

public interface ISubCommandManager {
    boolean onlyPlayer();

    IPermissions getPermission();

    int getArgsLength();

    void onCommand(CommandSender commandSender, Command command, String string, String[] strings);
}
