package me.ANiceHippo.toolkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import me.ANiceHippo.toolkit.Main;
import me.ANiceHippo.toolkit.utils.Utils;

public class FeedCommand implements CommandExecutor{
	
	private Main plugin;
	
	public FeedCommand (Main plugin)
	{
		this.plugin = plugin;
		
		plugin.getCommand("feed").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
		
		// Variable for food bar, 20 since it goes by halves
		int maxFoodLevel = 20;
		
		//If - Checks if command is only one word long - /feed
		if (args.length == 0)
		{
			//If - If user is the console, deny right to use /feed
			if (!(sender instanceof Player))
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.console_error")));
				return true;
			}
			// Convert sender into a player
			Player p = (Player) sender;
			//If -  Check if player has permission to use /feed or is OPed
			if (p.hasPermission("tk.feed.use") || p.isOp())
			{
				// If - If it's less than 20 bars feed the player
				if (p.getFoodLevel() < maxFoodLevel)
				{
					p.setFoodLevel(maxFoodLevel);
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_complete")));
				}
				// Else if - If it is full, deny the feed
				else if (p.getFoodLevel() == maxFoodLevel)
				{
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_incomplete")));
				}
				//Else - confirm that it was typed correctly
				else
				{
					p.sendMessage(ChatColor.GOLD + "/feed");
				}
			}
			// Else - User does not have permission
			else
			{
				p.sendMessage(Utils.chat(plugin.getConfig().getString("Error.permissions_error")));
			}
			
		}
		// Else if - checks to /feed <player>
		else if (args.length == 1)
		{
			//If - If user is the console, deny right to use /feed
			if (!(sender instanceof Player))
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.console_error")));
				return true;
			}
			
			// Convert sender into a player do args - 1 cause it ignores /feed as index
			Player p = Bukkit.getPlayerExact(args[0]);
			
			// If - check if player has permission to feed others or is OPed
			if(sender.hasPermission("tk.feed_player.use") || sender.isOp())
			{
				// If - Checks targeted players food status
				if (p.getFoodLevel() < maxFoodLevel)
				{
					p.setFoodLevel(maxFoodLevel);
					// Sends player that they have been fed.
					p.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_target_notification").replace("<sender>", sender.getName())));
					// Sends admin or command sender that player has been fed.
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_player_complete").replace("<player>", p.getName())));
				}
				// Else if - checks to see if full already
				else if (p.getFoodLevel() == maxFoodLevel)
				{
					// Notifies sender that user is already full
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_player_incomplete").replace("<player>", p.getName())));
					
				}
				// else - if format is incorrect
				else
				{
					sender.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_invalid")));
				}
			}
			//Else - Permissions error
			else
			{
				sender.sendMessage(Utils.chat(plugin.getConfig().getString("Error.permissions_error")));
			}			
		}
		// Else - If command format is incorrect
		else
		{
			sender.sendMessage(Utils.chat(plugin.getConfig().getString("Feed.feed_invalid")));
		}
		return false;
	}

}
