package ru.zoom4ikdan4ik.core.api.methods;

import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import javax.annotation.Nonnull;

public class PermissionsMethods implements IBase {
    public final PermissionUser getPermissionUser(@Nonnull final Player player) {
        return PermissionsEx.getPermissionManager().getUser(player);
    }

    public final PermissionGroup[] getPermissionGroups(@Nonnull final Player player) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        return user.getGroups(world.getName());
    }

    public final String getSuffix(@Nonnull final Player player) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        return user.getSuffix(world.getName());
    }

    public final String getPrefix(@Nonnull final Player player) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        return user.getPrefix(world.getName());
    }

    public final void setSuffix(@Nonnull final Player player, @Nonnull final String suffix) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        user.setSuffix(suffix, world.getName());
    }

    public final void setPrefix(@Nonnull final Player player, @Nonnull final String prefix) {
        final PermissionUser user = this.getPermissionUser(player);
        final World world = player.getWorld();

        user.setPrefix(prefix, world.getName());
    }
}
