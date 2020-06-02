package ru.zoom4ikdan4ik.core.api.methods;

import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class PermissionsMethods implements IBase {
    public final PermissionUser getPermissionUser(final Player player) {
        return PermissionsEx.getPermissionManager().getUser(player);
    }

    public final PermissionGroup[] getPermissionGroups(final Player player) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        return user.getGroups(world.getName());
    }

    public final String getSuffix(final Player player) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        return user.getSuffix(world.getName());
    }

    public final String getPrefix(final Player player) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        return user.getPrefix(world.getName());
    }

    public final void setSuffix(final Player player, final String suffix) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        user.setSuffix(suffix, world.getName());
    }

    public final void setPrefix(final Player player, final String prefix) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        user.setPrefix(prefix, world.getName());
    }
}
