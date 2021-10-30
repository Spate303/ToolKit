package me.anicehippo.toolkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreativeShortcutCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            if(p.hasPermission("Toolkit.gmc") || p.isOp())
            {
                if(p.getGameMode() == GameMode.CREATIVE)
                {
                    p.sendMessage(ChatColor.DARK_PURPLE + "You are already in creative!");
                }
                else
                {
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatColor.GREEN + "Set gamemode to creative!");
                }

            }
            else if(!p.hasPermission("ToolKit.gmc"))
            {
                p.sendMessage(ChatColor.RED + "You do not have permission for this command.");
            }
            else
            {
                return false;
            }
        }
        else if (sender instanceof BlockCommandSender)
        {
            sender.sendMessage("Command block cannot run this command!");
        }
        else
        {
            System.out.println("Console cannot run this command!");
        }

        return true;
    }
}
