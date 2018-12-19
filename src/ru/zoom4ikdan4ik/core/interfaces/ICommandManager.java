package ru.zoom4ikdan4ik.core.interfaces;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Interface for command use
 * */
public interface ICommandManager extends IBase, CommandExecutor {

    boolean onCommand(CommandSender sender, Command command, String label, String[] args);
}
