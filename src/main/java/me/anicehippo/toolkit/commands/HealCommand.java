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

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Plugin plugin  = ToolKit.getPlugin(ToolKit.class);

        //Player target = Bukkit.getPlayerExact(args[0])
        // /<command> arg0 arg1 arg2
        // Parameters of command go command.0.1.2.3
        // Length of command goes 0.1.2.3

        if(sender instanceof Player) // If player sends command
        {
            Player p = (Player) sender;

            int maxHealth = 20;


            if(p.hasPermission("ToolKit.heal") || p.isOp()){

                // /heal
                if(args.length == 0)
                {
                    if (p.getHealth() == maxHealth)
                    {
                        // If max health then do nothing
                        String msg = Utils.chat(plugin.getConfig().getString("HealDeny"));
                        p.sendMessage(msg);
                    }
                    else if(p.getHealth() < maxHealth) // If not fully healed then heal
                    {
                        p.setHealth(maxHealth);
                        String msg = Utils.chat(plugin.getConfig().getString("HealConfirm"));
                        p.sendMessage(msg);
                    }
                    else // Else wrong command input
                    {
                        return false;
                    }
                }
                else if(args.length == 1){
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target.getPlayer() != null)
                    {
                        if(target.getHealth() == maxHealth)
                        {
                            String msg = Utils.chat(plugin.getConfig().getString("HealTargetDenied").replace("<player>", target.getDisplayName()));
                            p.sendMessage(msg);
                        }
                        else if(target.getHealth() < maxHealth)
                        {
                            String msg = Utils.chat(plugin.getConfig().getString("HealTargetConfirmed").replace("<player>", target.getDisplayName()));
                            target.setHealth(maxHealth);
                            p.sendMessage(msg);
                            target.sendMessage(ChatColor.GREEN + "You have been healed by the gods!");
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    p.sendMessage(ChatColor.DARK_GRAY + "Incorrect format. " + ChatColor.GOLD + "/heal [player]");
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
