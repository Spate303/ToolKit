package me.ANiceHippo.toolkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import me.ANiceHippo.toolkit.Main;
import me.ANiceHippo.toolkit.utils.Utils;


public class HealCommand implements CommandExecutor {

	private Main plugin;
	
	public HealCommand (Main plugin)
	{
		this.plugin = plugin;
		
		plugin.getCommand("heal").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		
		// Variable for max health
		int maxHealthLevel = 20;
		// If - checks for self heal - /heal
		if (args.length == 0)
		{
			//If - If user is the console, deny right to use /heal
			if(!(sender instanceof Player))
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.console_error")));
				return true;
			}
			
			//Convert sender into player
			Player p = (Player) sender;
			
			//If - Check if player has permission to use /heal or is OPed
			if(p.hasPermission("tk.heal.use") || p.isOp())
			{
				//If - Check if player is below maxhealth to heal the player
				if (p.getHealth() < maxHealthLevel)
				{
					// Heal the player
					p.setHealth(maxHealthLevel);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_complete")));
				}
				//Else if - check is user is at max health already
				else if (p.getHealth() == maxHealthLevel)
				{
					// Deny if already at max health
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_incomplete")));
				}
				// Else - confirm that it is in correct format
				else
				{
					p.sendMessage(ChatColor.GOLD + "/heal");
				}
			}
			//Else - Permissions error
			else
			{
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Error.permissions_error")));
			}
		}
		// Else if - Command = /heal <player>
		else if (args.length == 1)
		{
			//If - If user is the console, deny right to use /heal
			if (!(sender instanceof Player))
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.console_error")));
				return true;
			}
			
			// Convert sender into a player do args - 1 cause it ignores /feed as index
			Player p = Bukkit.getPlayerExact(args[0]);
			
			// If - check if player has permission to use /heal <player>
			if(sender.hasPermission("tk.heal_player.use") || sender.isOp())
			{
				//If check health level
				if(p.getHealth() < maxHealthLevel)
				{
					p.setHealth(maxHealthLevel);
					//Sends player that they have been healed.
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_target_notification").replace("<sender>", sender.getName())));
					//Sends admin that player has been healed.
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_player_complete").replace("<player>", p.getName())));
				}
				// Else if - checks to see if full already
				else if(p.getFoodLevel() == maxHealthLevel)
				{
					//Notifies sender that user is already full
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_player_incomplete").replace("<player>", p.getName())));
				}
				// Else - if format is incorrect
				else
				{
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_invalid")));
				}
			}
			//Else - permissions error
			else
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.permissions_error")));
			}
		}
		//Else - If command format is incorrect
		else
		{
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("Heal.heal_invalid")));
		}
		return false;
	}
}
