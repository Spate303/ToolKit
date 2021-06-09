package me.ANiceHippo.toolkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ANiceHippo.toolkit.Main;
import me.ANiceHippo.toolkit.utils.Utils;

public class FlyCommand implements CommandExecutor{

	private Main plugin;
	
	public FlyCommand (Main plugin)
	{
		this.plugin = plugin;
		
		plugin.getCommand("fly").setExecutor(this);
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		// If - checks for /fly on self
		if (args.length == 0)
		{
			//If it is console, deny right to use fly
			if (!(sender instanceof Player))
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.console_error")));
				return true;
			}
			
			// Convert sender to player
			Player p = (Player) sender;
			
			// If - check is user has permission or OPed
			if (p.hasPermission("tk.fly.use") || p.isOp())
			{
				//If check if user is already flying
				if (p.isFlying())
				{
					// Disable all flight perms and send message
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_disable")));
					return true;
				}
				//Else -  Not flying so enable it
				else
				{
					p.setAllowFlight(true);
					p.setFlying(true);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_enable")));
				}
				
			}
			// Else - No permission error
			else 
			{
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Error.permissions_error")));
			}
			
			
		}
		// Else If - /fly <player>
		else if (args.length == 1)
		{
			// If - console uses command
			if (!(sender instanceof Player))
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.console_error")));
				return true;
			}
			
			// Find player
			Player p = Bukkit.getPlayerExact(args[0]);
			// If - sender has permission or is Op
			if(sender.hasPermission("tk.fly_player.use") || sender.isOp())
			{
				if(p.isFlying())
				{
					// Disable flight for targeted player
					p.setAllowFlight(false);
					p.setFlying(false);
					// Tell player that flight has been disabled
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_target_notification")));
					// Tell admin that player has been toggled
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_player_disabled").replace("<player>", p.getName())));
					return true;
				}
				// Else - if player is not flying, enable it
				else
				{
					// enable flight for targeted player
					p.setAllowFlight(true);
					p.setFlying(true);
					// Tell player that flight has been enabled
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_target_notification")));
					// Tell admin that player has been toggled
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_player_enabled").replace("<player>", p.getName())));
				}
			}
			// Else - player does not have permission
			else
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.permissions_error")));
			}
		}
		// Else - Format error
		else
		{
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("Fly.fly_invalid")));
		}
		
		return false;
	}

}
