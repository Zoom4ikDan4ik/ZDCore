package ru.zoom4ikdan4ik.core.api.methods;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
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
    public long getSystemTime() {
        return System.currentTimeMillis();
    }

    public long getUnixTime() {
        return this.getSystemTime() / 1000;
    }

    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public String getPathServer() {
        File path = this.corePlugin.getDataFolder();

        while (!path.getName().equals("plugins"))
            path = path.getParentFile();

        return path.getAbsolutePath();
    }

    public World getMainWorld() {
        return this.configManager.main_world;
    }

    public String getPathServer(@Nonnull String server) {
        return this.getPathServer().replace(this.configManager.server, server);
    }

    public void sendMessageQuery(CommandSender sender, String start) {
        String message = null;

        for (MessagesEnum messagesEnum : MessagesEnum.values())
            if (messagesEnum.name().equals(start))
                message = messagesEnum.getMessage();

        this.sendMessage(sender, message);
    }

    public String getQuery(String start, List<String> queriesList) {
        for (String queries : queriesList)
            if (queries.startsWith(start))
                return queries;

        return null;
    }

    public void sendMessage(CommandSender sender, IMessages message) {
        this.sendMessage(sender, message.getMessage());
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(this.color(message));
    }

    public void useScripts(String script) throws IOException {
        File file = new File(this.configManager.scripts_path + script);

        if (!file.exists())
            throw new IOException("File not found!");

        Runtime.getRuntime().exec("sh " + file.getAbsolutePath());
    }

    public FileConfiguration createFileConfiguration(String config, Plugin plugin) {
        return YamlConfiguration.loadConfiguration(this.createConfigYML(config, plugin));
    }

    public File createConfigYML(String cfg, Plugin plugin) {
        File file = null;

        if (cfg != null)
            file = new File(plugin.getDataFolder(), cfg + ".yml");

        if (!file.exists()) {
            InputStream resourceAsStream = plugin.getClass().getResourceAsStream("/ru/zoom4ikdan4ik/configs/" + cfg + ".yml");
            plugin.getDataFolder().mkdirs();

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