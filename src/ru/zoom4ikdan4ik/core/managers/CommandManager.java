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
                        this.pluginsManager.reloadPlugins();

                        this.coreMethods.sendMessage(sender, "&aThe plugins was reloaded!");
                    } else this.coreMethods.sendMessage(sender, MessagesEnum.NOT_HAVE_PERMISSIONS.getMessage());
                    break;
                case "script":
                    if (args.length > 1) {
                        if (sender.hasPermission("zdcore.script")) {
                            try {
                                this.coreMethods.useScripts(args[1]);
                                this.coreMethods.sendMessage(sender, "&aStarted...");
                            } catch (IOException e) {
                                this.loggerUtils.info(this.corePlugin, e.getMessage());

                                this.coreMethods.sendMessage(sender, "&cError: " + e.getMessage());
                            }
                        } else this.coreMethods.sendMessage(sender, MessagesEnum.NOT_HAVE_PERMISSIONS.getMessage());
                    } else this.coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS.getMessage());
                    break;
                default:
                    this.coreMethods.sendMessage(sender, MessagesEnum.NOT_FOUND_PARAMETERS.getMessage());
                    break;
            }
        } else this.coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS.getMessage());

        return true;
    }
}
