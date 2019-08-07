package ru.zoom4ikdan4ik.core.enums;

import ru.zoom4ikdan4ik.core.interfaces.IBase;

public enum PermissionsEnum implements IBase {
    RELOAD(configUtils.getString(configManager.getConfig(), "Permissions.Commands.reload", "zdcore.reload")),
    SCRIPT(configUtils.getString(configManager.getConfig(), "Permissions.Commands.script", "zdcore.script")),
    UNIX(configUtils.getString(configManager.getConfig(), "Permissions.Commands.unix", "zdcore.unix")),
    ID(configUtils.getString(configManager.getConfig(), "Permissions.Commands.id", "zdcore.id"));

    private final String permission;

    PermissionsEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
