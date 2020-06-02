package ru.zoom4ikdan4ik.core.api.methods;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import javax.annotation.Nonnull;
import java.util.List;

public class BukkitMethods implements IBase {
    public final List<World> getWorlds() {
        return Bukkit.getWorlds();
    }

    public final Player searchPlayer(@Nonnull final String player) {
        for (final Player players : Bukkit.getOnlinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players;

        for (final OfflinePlayer players : Bukkit.getOfflinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players.getPlayer();

        return Bukkit.getPlayer(player);
    }

    public final void useCommand(final String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public final void broadcast(final String broadcast) {
        Bukkit.broadcastMessage(this.coreMethods.color(broadcast));
    }

    public final void restartServer() {
        Bukkit.getServer().shutdown();
    }

    public final String getItemStringID(@Nonnull final ItemStack itemStack) {
        return String.format("%s:%d", itemStack.getType(), itemStack.getDurability());
    }

    public final String getBlockStringID(@Nonnull final Block block) {
        return String.format("%s:%d", block.getType(), block.getData());
    }
}
