package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
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
                    } else coreMethods.sendMessage(sender, MessagesEnum.NOT_HAVE_PERMISSIONS.getMessage());
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
                        } else coreMethods.sendMessage(sender, MessagesEnum.NOT_HAVE_PERMISSIONS.getMessage());
                    } else coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS.getMessage());
                    break;
                default:
                    coreMethods.sendMessage(sender, MessagesEnum.NOT_FOUND_PARAMETERS.getMessage());
                    break;
            }
        } else coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS.getMessage());

        return true;
    }
}
