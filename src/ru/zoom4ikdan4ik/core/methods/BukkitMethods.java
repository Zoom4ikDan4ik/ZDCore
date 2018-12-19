package ru.zoom4ikdan4ik.core.methods;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.List;

public class BukkitMethods implements IBase {

    public List<World> getWorlds() {
        return Bukkit.getWorlds();
    }

    public Player searchPlayer(String player) {
        for (Player players : this.getOnlinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players;

        for (OfflinePlayer players : Bukkit.getOfflinePlayers())
            if (players.getName().toLowerCase().startsWith(player.toLowerCase()))
                return players.getPlayer();

        return null;
    }

    public List<Player> getOnlinePlayers() {
        List<Player> playerList = new ArrayList<>();

        for (int i = 0; i < Bukkit.getOnlinePlayers().length; i++)
            playerList.add(Bukkit.getOnlinePlayers()[i]);

        return playerList;
    }

    public void useCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public void broadcast(String str) {
        Bukkit.broadcastMessage(coreMethods.color(str));
    }

    public void restartServer() {
        Bukkit.getServer().shutdown();
    }
}
