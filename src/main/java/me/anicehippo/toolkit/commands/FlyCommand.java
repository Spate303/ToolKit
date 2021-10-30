package me.anicehippo.toolkit.commands;

import me.anicehippo.toolkit.ToolKit;
import me.anicehippo.toolkit.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Plugin plugin  = ToolKit.getPlugin(ToolKit.class);

        if(sender instanceof Player){
            Player p = (Player) sender;

            if(p.hasPermission("ToolKit.fly") || p.isOp()){

                if(args.length == 0)
                {
                    if(p.isFlying())
                    {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        String msg = Utils.chat(plugin.getConfig().getString("FlyDisabled"));
                        p.sendMessage(msg);
                    }
                    else if (!p.isFlying()){
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        String msg = Utils.chat(plugin.getConfig().getString("FlyEnabled"));
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

                    if (target.getPlayer() != null)
                    {
                        if(target.isFlying())
                        {
                            target.setAllowFlight(false);
                            target.setFlying(false);
                            String msg = Utils.chat(plugin.getConfig().getString("FlyTargetDisabled"));
                            p.sendMessage(msg);
                            target.sendMessage(ChatColor.RED + "You can no longer fly!");
                        }
                        else if (!target.isFlying()){
                            target.setAllowFlight(true);
                            target.setFlying(true);
                            String msg = Utils.chat(plugin.getConfig().getString("FlyTargetEnabled"));
                            p.sendMessage(msg);
                            target.sendMessage(ChatColor.GREEN + "You can now fly!");
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
                    p.sendMessage(ChatColor.DARK_GRAY + "Incorrect format. " + ChatColor.GOLD + "/fly [player]");
                }
            }
            else
            {
                String msg = Utils.chat(plugin.getConfig().getString("NoPerm"));
                p.sendMessage(msg);
            }
        }
        else if (sender instanceof BlockCommandSender)
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


