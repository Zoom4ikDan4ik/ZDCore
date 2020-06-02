package ru.zoom4ikdan4ik.core.api.methods;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
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

    public final String color(final String string) {
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

    public final void sendMessageQuery(final CommandSender sender, final String start) {
        String message = null;

        for (final MessagesEnum messagesEnum : MessagesEnum.values())
            if (messagesEnum.name().equals(start))
                message = messagesEnum.getMessage();

        this.sendMessage(sender, message);
    }

    public final String getQuery(final String start, final List<String> queriesList) {
        for (final String queries : queriesList)
            if (queries.startsWith(start))
                return queries;

        return null;
    }

    public final void sendMessage(final CommandSender sender, final IMessages message) {
        this.sendMessage(sender, message.getMessage());
    }

    public final void sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(this.color(message));
    }

    public final void useScripts(final String script) throws IOException {
        final File file = new File(this.configManager.scripts_path + script);

        if (!file.exists())
            throw new IOException("File not found!");

        Runtime.getRuntime().exec("sh " + file.getAbsolutePath());
    }

    public final File createConfigYML(final String config, final Plugin plugin) {
        File file = null;

        if (config != null)
            file = new File(plugin.getDataFolder(), config + ".yml");

        if (!file.exists()) {
            final InputStream resourceAsStream = plugin.getClass().getResourceAsStream("/ru/zoom4ikdan4ik/configs/" + config + ".yml");
            plugin.getDataFolder().mkdirs();

            if (resourceAsStream != null)
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
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
