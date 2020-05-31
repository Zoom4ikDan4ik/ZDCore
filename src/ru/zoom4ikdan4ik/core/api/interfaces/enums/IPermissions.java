package ru.zoom4ikdan4ik.core.api.interfaces.enums;

import org.bukkit.command.CommandSender;

public interface IPermissions {
    String getPermission();

    void setPermission(String permission);

    boolean hasPermission(CommandSender commandSender);
}
