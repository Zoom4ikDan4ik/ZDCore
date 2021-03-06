package ru.zoom4ikdan4ik.core.api.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Interface for command use
 */
public interface ICommandManager extends CommandExecutor {
    List<String> getCommands();

    boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
