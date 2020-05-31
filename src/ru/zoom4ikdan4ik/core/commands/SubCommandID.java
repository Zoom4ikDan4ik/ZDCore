package ru.zoom4ikdan4ik.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.zoom4ikdan4ik.core.api.interfaces.ISubCommandManager;
import ru.zoom4ikdan4ik.core.api.interfaces.enums.IPermissions;
import ru.zoom4ikdan4ik.core.enums.PermissionsEnum;
import ru.zoom4ikdan4ik.core.interfaces.IBase;

public class SubCommandID implements ISubCommandManager, IBase {
    @Override
    public boolean onlyPlayer() {
        return true;
    }

    @Override
    public IPermissions getPermission() {
        return PermissionsEnum.ID;
    }

    @Override
    public int getArgsLength() {
        return 0;
    }

    @Override
    public void onCommand(CommandSender commandSender, Command command, String string, String[] strings) {
        Player player = (Player) commandSender;
        ItemStack itemStack = player.getItemInHand();

        this.coreMethods.sendMessage(commandSender, this.bukkitMethods.getItemStringID(itemStack));
    }
}
