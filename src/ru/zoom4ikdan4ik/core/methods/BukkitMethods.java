package ru.zoom4ikdan4ik.core.methods;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import javax.annotation.Nonnull;
import java.util.List;

public class BukkitMethods implements IBase {
    public List<World> getWorlds() {
        return Bukkit.getWorlds();
    }

    public Player searchPlayer(@Nonnull String player) {
        for (Player players : Bukkit.getOnlinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players;

        for (OfflinePlayer players : Bukkit.getOfflinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players.getPlayer();

        return null;
    }

    public Server getBukkitServer() {
        return Bukkit.getServer();
    }

    public void useCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public void broadcast(String broadcast) {
        Bukkit.broadcastMessage(this.coreMethods.color(broadcast));
    }

    public void restartServer() {
        Bukkit.getServer().shutdown();
    }

    public String getItemStringID(@Nonnull ItemStack itemStack) {
        String id = itemStack.getType().toString();
        String data = String.valueOf(itemStack.getDurability());
        String size = String.valueOf(itemStack.getAmount());

        return id + ":" + data + " " + size;
    }
}
