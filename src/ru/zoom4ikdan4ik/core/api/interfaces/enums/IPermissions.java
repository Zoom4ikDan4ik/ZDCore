package ru.zoom4ikdan4ik.core.api.interfaces.enums;

import org.bukkit.command.CommandSender;

public interface IPermissions {
    String getName();

    String getPermission();

    void setPermission(final String permission);

    boolean hasPermission(final CommandSender commandSender);
}
