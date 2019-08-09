package ru.zoom4ikdan4ik.core.methods;

import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PermissionsMethods implements IBase {
    public PermissionUser getPermissionUser(Player player) {
        return PermissionsEx.getPermissionManager().getUser(player);
    }

    public PermissionGroup[] getPermissionGroups(Player player) {
        PermissionUser user = this.getPermissionUser(player);
        World world = player.getWorld();

        return user.getGroups(world.getName());
    }

    public String getSuffix(Player player) {
        PermissionUser user = this.getPermissionUser(player);
        World world = player.getWorld();

        return user.getSuffix(world.getName());
    }

    public String getPrefix(Player player) {
        PermissionUser user = this.getPermissionUser(player);
        World world = player.getWorld();

        return user.getPrefix(world.getName());
    }

    public void setSuffix(Player player, String suffix) {
        PermissionUser user = this.getPermissionUser(player);
        World world = player.getWorld();

        user.setSuffix(suffix, world.getName());
    }

    public void setPrefix(Player player, String prefix) {
        PermissionUser user = this.getPermissionUser(player);
        World world = player.getWorld();

        user.setPrefix(prefix, world.getName());
    }
}
