package ru.zoom4ikdan4ik.core.methods;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.List;

public class BukkitMethods implements IBase {
    public List<World> getWorlds() {
        return Bukkit.getWorlds();
    }

    public Player searchPlayer(String player) {
        for (Player players : Bukkit.getOnlinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players;

        for (OfflinePlayer players : Bukkit.getOfflinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players.getPlayer();

        return null;
    }

    public void useCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public void broadcast(String str) {
        Bukkit.broadcastMessage(this.coreMethods.color(str));
    }

    public void restartServer() {
        Bukkit.getServer().shutdown();
    }
}
