package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.interfaces.ICommandManager;

import java.io.IOException;

public class CommandManager implements ICommandManager {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String commander = args[0];

            switch (commander) {
                case "reload":
                    if (sender.hasPermission("zdcore.reload")) {
                        pluginsManager.reloadPlugins();

                        coreMethods.sendMessage(sender, "&aПлагины были перезагружены!");
                    } else coreMethods.sendMessage(sender, configManager.Messages.get(3).split(":::")[1]);
                    break;
                case "script":
                    if (args.length > 1) {
                        if (sender.hasPermission("zdcore.script")) {
                            try {
                                coreMethods.useScripts(args[1]);
                                coreMethods.sendMessage(sender, "&aЗапущено...");
                            } catch (IOException e) {
                                loggerUtils.info(corePlugin, e.getMessage());

                                coreMethods.sendMessage(sender, "&cОшибка: " + e.getMessage());
                            }
                        } else coreMethods.sendMessage(sender, configManager.Messages.get(3).split(":::")[1]);
                    } else coreMethods.sendMessage(sender, configManager.Messages.get(0).split(":::")[1]);
                    break;
                default:
                    coreMethods.sendMessage(sender, configManager.Messages.get(2).split(":::")[1]);
                    break;
            }
        } else coreMethods.sendMessage(sender, configManager.Messages.get(0).split(":::")[1]);

        return true;
    }
}
