package ru.zoom4ikdan4ik.core.methods;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CoreMethods implements IBase {
    public String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public String getPathServer() {
        String[] path = this.corePlugin.getDataFolder().getAbsolutePath().replace("\\", "/").split("/");
        String serverPath = "";

        for (String str : path)
            if (str.equalsIgnoreCase("plugins"))
                break;
            else
                serverPath += "/" + str;


        return serverPath;
    }

    public World getMainWorld() {
        return this.configManager.main_world;
    }

    public String getPathServer(String server) {
        return this.getPathServer().replace(this.configManager.server, server);
    }

    public void sendMessageQuery(CommandSender sender, String start) {
        String message = null;

        for (MessagesEnum messagesEnum : MessagesEnum.values())
            if (messagesEnum.name().equals(start))
                message = messagesEnum.getMessage();

        this.sendMessage(sender, message);
    }

    public String getQuery(String start, List<String> querysList) {
        for (String querys : querysList)
            if (querys.startsWith(start))
                return querys;

        return null;
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }

    public void useScripts(String script) throws IOException {
        File file = new File(this.configManager.scripts_path + script);

        if (!file.exists())
            throw new IOException("File not found!");

        Runtime.getRuntime().exec("sh " + file.getAbsolutePath());
    }

    public File createConfigYML(String cfg, Plugin plugin) {
        File file = null;

        if (cfg != null)
            file = new File(plugin.getDataFolder(), cfg + ".yml");

        if (!file.exists()) {
            InputStream resourceAsStream = plugin.getClass().getResourceAsStream("/ru/zoom4ikdan4ik/configs/" + cfg + ".yml");
            plugin.getDataFolder().mkdirs();

            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] buff = new byte[65536];
                int n;

                while ((n = resourceAsStream.read(buff)) > 0) {
                    fos.write(buff, 0, n);
                    fos.flush();
                }

                fos.close();
            } catch (Exception error) {
                this.loggerUtils.info(plugin, error.getMessage());
            }
        }

        return file;
    }
}
