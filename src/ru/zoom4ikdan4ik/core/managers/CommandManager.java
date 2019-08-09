package ru.zoom4ikdan4ik.core.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.zoom4ikdan4ik.core.enums.CommandsEnum;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;
import ru.zoom4ikdan4ik.core.interfaces.ICommandManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandManager implements ICommandManager {

    @Override
    public List<String> getCommands() {
        List<String> commands = new ArrayList<String>();

        commands.add("zdcore");

        return commands;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String commander = args[0];
            CommandsEnum commandsEnum = CommandsEnum.valueOf(commander.toUpperCase());

            switch (commandsEnum) {
                case RELOAD:
                    if (sender.hasPermission(PermissionsEnum.RELOAD.getPermission())) {
                        this.pluginsManager.reloadPlugins();

                        this.coreMethods.sendMessage(sender, "&aThe plugins was reloaded!");
                    } else this.coreMethods.sendMessage(sender, MessagesEnum.NOT_HAVE_PERMISSIONS.getMessage());

                    break;
                case SCRIPT:
                    if (args.length > 1) {
                        if (sender.hasPermission(PermissionsEnum.SCRIPT.getPermission())) {
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
                case UNIX:
                    if (sender.hasPermission(PermissionsEnum.UNIX.getPermission()))
                        this.coreMethods.sendMessage(sender, "&aUnix time: " + this.coreMethods.getUnixTime());

                    break;
                case ID:
                    if (sender instanceof ConsoleCommandSender)
                        this.coreMethods.sendMessage(sender, MessagesEnum.CONSOLE_SENDER.getMessage());
                    else if (sender.hasPermission(PermissionsEnum.ID.getPermission())) {
                        Player player = (Player) sender;
                        ItemStack itemStack = player.getItemInHand();

                        this.coreMethods.sendMessage(sender, this.bukkitMethods.getItemStringID(itemStack));
                    }
                    break;
                default:
                    this.coreMethods.sendMessage(sender, MessagesEnum.NOT_FOUND_PARAMETERS.getMessage());

                    break;
            }
        } else this.coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS.getMessage());

        return true;
    }
}
