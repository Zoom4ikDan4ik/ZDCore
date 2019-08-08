package ru.zoom4ikdan4ik.core.enums;

import ru.zoom4ikdan4ik.core.interfaces.IBase;

public enum PermissionsEnum implements IBase {
    RELOAD(configUtils.getString(configManager.getConfig(), "Permissions.Commands." + CommandsEnum.RELOAD.getCommand(), "zd.core." + CommandsEnum.RELOAD.getCommand().toLowerCase())),
    SCRIPT(configUtils.getString(configManager.getConfig(), "Permissions.Commands." + CommandsEnum.SCRIPT.getCommand(), "zd.core." + CommandsEnum.SCRIPT.getCommand().toLowerCase())),
    UNIX(configUtils.getString(configManager.getConfig(), "Permissions.Commands." + CommandsEnum.UNIX.getCommand(), "zd.core." + CommandsEnum.UNIX.getCommand().toLowerCase())),
    ID(configUtils.getString(configManager.getConfig(), "Permissions.Commands." + CommandsEnum.ID.getCommand(), "zd.core." + CommandsEnum.ID.getCommand().toLowerCase()));

    private final String permission;

    PermissionsEnum(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
