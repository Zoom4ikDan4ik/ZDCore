package ru.zoom4ikdan4ik.core.api.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.zoom4ikdan4ik.core.api.interfaces.ICommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.ICommands;
import ru.zoom4ikdan4ik.core.enums.CommandsEnum;
import ru.zoom4ikdan4ik.core.enums.MessagesEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommandManager implements ICommandManager, IBase {
    private final List<String> commands = new ArrayList<>();
    private final List<SubCommand> subCommands = new ArrayList<>();

    public abstract void registerCommands();

    public abstract void registerSubCommands();

    public final ISubCommandManager getSubCommand(CommandsEnum commandsEnum) {
        for (SubCommand subCommand : this.subCommands)
            if (subCommand.getSubCommand().equals(commandsEnum.getSubCommand()))
                return subCommand.getSubCommandManager();

        return null;
    }

    public final void registerCommand(String command) {
        this.commands.add(command);
    }

    public final void registerSubCommand(ICommands commands) {
        this.subCommands.add(new SubCommand(commands.getSubCommand(), commands.getSubCommandManager()));
    }

    @Override
    public final List<String> getCommands() {
        return this.commands;
    }

    @Override
    public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            String commander = args[0];
            CommandsEnum commandsEnum = CommandsEnum.valueOf(commander.toUpperCase());
            ISubCommandManager subCommandManager = this.getSubCommand(commandsEnum);

            if (subCommandManager != null) {
                if (!subCommandManager.getPermission().hasPermission(sender)) {
                    this.coreMethods.sendMessage(sender, MessagesEnum.NOT_HAVE_PERMISSIONS);

                    return true;
                }

                if (subCommandManager.getArgsLength() != args.length) {
                    this.coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS);

                    return true;
                }

                if (subCommandManager.onlyPlayer())
                    if (!(sender instanceof Player)) {
                        this.coreMethods.sendMessage(sender, MessagesEnum.CONSOLE_SENDER);

                        return true;
                    }

                subCommandManager.onCommand(sender, command, label, args);
            } else this.coreMethods.sendMessage(sender, MessagesEnum.NOT_FOUND_PARAMETERS);
        } else this.coreMethods.sendMessage(sender, MessagesEnum.NUMBER_EXCEPTIONS);

        return true;
    }

    public final class SubCommand {
        private final String subCommand;
        private final ISubCommandManager subCommandManager;

        public SubCommand(String subCommand, ISubCommandManager subCommandManager) {
            this.subCommand = subCommand;
            this.subCommandManager = subCommandManager;
        }

        public final String getSubCommand() {
            return this.subCommand;
        }

        public final ISubCommandManager getSubCommandManager() {
            return this.subCommandManager;
        }
    }
}
