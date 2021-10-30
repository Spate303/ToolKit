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

public class GodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Plugin plugin  = ToolKit.getPlugin(ToolKit.class);
        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            if(p.hasPermission("ToolKit.god") || p.isOp())
            {
                if(args.length == 0)
                {
                    if(p.isInvulnerable())
                    {
                        p.setInvulnerable(false);
                        String msg = Utils.chat(plugin.getConfig().getString("GodDisabled"));
                        p.sendMessage(msg);
                    }
                    else if(!p.isInvulnerable())
                    {
                        p.setInvulnerable(true);
                        String msg = Utils.chat(plugin.getConfig().getString("GodEnabled"));
                        p.sendMessage(msg);
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (args.length == 1)
                {
                    Player target = Bukkit.getPlayerExact(args[0]);
                    if(target.getPlayer() != null)
                    {
                        if(target.isInvulnerable())
                        {
                            target.setInvulnerable(false);
                            String msg = Utils.chat(plugin.getConfig().getString("GodTargetDisabled")).replace("<player>", target.getDisplayName());
                            p.sendMessage(msg);
                            target.sendMessage(ChatColor.RED + "A god has taken away your godlike powers! You are vulnerable!");
                        }
                        else if(!target.isInvulnerable())
                        {
                            target.setInvulnerable(true);
                            String msg = Utils.chat(plugin.getConfig().getString("GodTargetEnabled").replace("<player>", target.getDisplayName()));
                            p.sendMessage(msg);
                            target.sendMessage(ChatColor.GREEN + "A god has given you godlike powers! You are invulnerable!");
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
                    p.sendMessage(ChatColor.DARK_GRAY + "Incorrect format. " + ChatColor.GOLD + "/god [player]");
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
