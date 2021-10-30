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

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Plugin plugin  = ToolKit.getPlugin(ToolKit.class);
        if(sender instanceof Player) // If player sends command
        {
            Player p = (Player) sender;
            int maxFood = 20;

            if(p.hasPermission("ToolKit.feed") || p.isOp())
            {

                if(args.length == 0)
                {
                    if (p.getFoodLevel() == maxFood){ // If max health then do nothing
                        String msg = Utils.chat(plugin.getConfig().getString("FeedDeny"));
                        p.sendMessage(msg);
                    }
                    else if(p.getFoodLevel() < maxFood) // If not fully healed then heal
                    {
                        p.setFoodLevel(maxFood);
                        String msg = Utils.chat(plugin.getConfig().getString("FeedConfirm"));
                        p.sendMessage(msg);
                    }
                    else // Else wrong command input
                    {
                        return false;
                    }
                }
                else if(args.length == 1)
                {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target.getPlayer() != null)
                    {
                        if(target.getFoodLevel() == maxFood)
                        {
                            String msg = Utils.chat(plugin.getConfig().getString("FeedTargetDenied").replace("<player>", target.getDisplayName()));
                            p.sendMessage(msg);
                        }
                        else if(target.getFoodLevel() < maxFood)
                        {
                            String msg = Utils.chat(plugin.getConfig().getString("FeedTargetConfirm").replace("<player>", target.getDisplayName()));
                            target.setHealth(maxFood);
                            p.sendMessage(msg);
                            target.sendMessage(ChatColor.GREEN + "You have been fed by the gods!");
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                else
                {
                    p.sendMessage(ChatColor.DARK_GRAY + "Incorrect format. " + ChatColor.GOLD + "/feed [player]");
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
