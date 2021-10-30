package me.anicehippo.toolkit.commands;

import me.anicehippo.toolkit.ToolKit;
import me.anicehippo.toolkit.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin plugin = ToolKit.getPlugin(ToolKit.class);
        Player p = (Player) sender;

        if(sender instanceof Player)
        {
            if(p.hasPermission("ToolKit.vanish") || p.isOp())
            {
                if(args.length == 0)
                {
                    if(p.isInvisible())
                    {
                        p.setInvisible(false);
                        String msg = Utils.chat(plugin.getConfig().getString("VanishDisabled"));
                        p.sendMessage(msg);
                    }
                    else if(!p.isInvisible())
                    {
                        p.setInvisible(true);
                        String msg = Utils.chat(plugin.getConfig().getString("VanishEnabled"));
                        p.sendMessage(msg);
                    }
                    else
                    {
                        return false;
                    }
                }
                else if(args.length == 1)
                {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if(target.isInvisible())
                    {
                        target.setInvisible(false);
                        String msg = Utils.chat(plugin.getConfig().getString("VanishTargetDisabled")).replace("<player>", target.getDisplayName());
                        p.sendMessage(msg);
                        target.sendMessage(ChatColor.RED + "You are now visible!");
                    }
                    else if(!target.isInvisible())
                    {
                        target.setInvisible(true);
                        String msg = Utils.chat(plugin.getConfig().getString("VanishTargetEnabled")).replace("<player>", target.getDisplayName());
                        p.sendMessage(msg);
                        target.sendMessage(ChatColor.GREEN + "You are now invisible!");
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            else
            {
                String msg = Utils.chat(plugin.getConfig().getString("NoPerm"));
                p.sendMessage(msg);
            }
        }
        else if(sender instanceof BlockCommandSender)
        {
            String msg = Utils.chat(plugin.getConfig().getString("CommandBlockError"));
            sender.sendMessage(msg);
        }
        else
        {
            String msg = Utils.chat(plugin.getConfig().getString("ConsoleError"));
            System.out.println(msg);
        }







        return true;
    }
}
