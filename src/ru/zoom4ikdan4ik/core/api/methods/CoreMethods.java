package ru.zoom4ikdan4ik.core.api.methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IMessages;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CoreMethods implements IBase {
    public final long getSystemTime() {
        return System.currentTimeMillis();
    }

    public final long getUnixTime() {
        return this.getSystemTime() / 1000;
    }

    public final String color(@Nonnull final String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public final String getPathServer() {
        File path = this.corePlugin.getDataFolder();

        while (!path.getName().equals("plugins"))
            path = path.getParentFile();

        return path.getAbsolutePath();
    }

    public final World getMainWorld() {
        return this.configManager.main_world;
    }

    public final String getPathServer(@Nonnull final String server) {
        return this.getPathServer().replace(this.configManager.server, server);
    }

    public final void sendMessageQuery(@Nonnull final CommandSender sender, @Nonnull final String start) {
        String message = null;

        for (final MessagesEnum messagesEnum : MessagesEnum.values())
            if (messagesEnum.name().equals(start))
                message = messagesEnum.getMessage();

        this.sendMessage(sender, message);
    }

    public final String getQuery(@Nonnull final String start, @Nonnull final List<String> queriesList) {
        for (final String queries : queriesList)
            if (queries.startsWith(start))
                return queries;

        return null;
    }

    public final void sendMessage(@Nonnull final CommandSender sender, @Nonnull final IMessages message) {
        this.sendMessage(sender, message.getMessage());
    }

    public final void sendMessage(@Nonnull final CommandSender sender, @Nonnull final String message) {
        sender.sendMessage(this.color(message));
    }

    public final void sendMessageAllNearby(@Nonnull final IMessages message, @Nonnull final Player player, @Nonnull final double distance) {
        this.sendMessageAllNearby(message.getMessage(), player, distance);
    }

    public final void sendMessageAllNearby(@Nonnull final String message, @Nonnull final Player player, @Nonnull final double distance) {
        this.sendMessageAllNearby(message, player.getLocation(), distance);
    }

    public final void sendMessageAllNearby(@Nonnull final IMessages message, @Nonnull final Location location, @Nonnull final double distance) {
        this.sendMessageAllNearby(message.getMessage(), location, distance);
    }

    public final void sendMessageAllNearby(@Nonnull final String message, @Nonnull final Location location, @Nonnull final double distance) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final Location playerLocation = player.getLocation();
            final double distanceToLocation = playerLocation.distanceSquared(location);

            if (distanceToLocation <= distance)
                this.sendMessage(player, message);
        }
    }

    public final void useScripts(@Nonnull final String script) throws IOException {
        final File file = new File(this.configManager.scripts_path + script);

        if (!file.exists())
            throw new IOException("File not found!");

        Runtime.getRuntime().exec("sh " + file.getAbsolutePath());
    }

    public final File createConfigYML(@Nonnull final String config, @Nonnull final Plugin plugin) {
        File file = null;

        if (config != null)
            file = new File(plugin.getDataFolder(), config + ".yml");

        if (!file.exists()) {
            final InputStream resourceAsStream = plugin.getClass().getResourceAsStream("/ru/zoom4ikdan4ik/configs/" + config + ".yml");
            plugin.getDataFolder().mkdirs();

            if (resourceAsStream != null)
                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[65536];
                    int n;

                    while ((n = resourceAsStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, n);
                        fileOutputStream.flush();
                    }

                    fileOutputStream.close();
                } catch (Exception error) {
                    this.loggerUtils.info(plugin, error.getMessage());
                }
        }

        return file;
    }
}
