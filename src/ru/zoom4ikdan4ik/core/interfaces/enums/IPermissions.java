package ru.zoom4ikdan4ik.core.interfaces.enums;

import org.bukkit.command.CommandSender;

public interface IPermissions {
    String getPermission();

    boolean hasPermission(CommandSender commandSender);
}
