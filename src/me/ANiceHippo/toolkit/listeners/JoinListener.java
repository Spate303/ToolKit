package me.ANiceHippo.toolkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ANiceHippo.toolkit.Main;
import me.ANiceHippo.toolkit.utils.Utils;

public class JoinListener implements Listener{

	private Main plugin;
	
	public JoinListener (Main plugin)
	{
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		
		// Receive player
		Player p = e.getPlayer();
		
		
		//If - See if player is joining for the first time
		if(!p.hasPlayedBefore())
		{
			// If - No one is on the server, do not send message to cause null
			if (Bukkit.getOnlinePlayers().size() == 0)
			{
				return;
			}
			// Else - send others a msg that it is users first time in!
			else
			{
				for (Player on : Bukkit.getOnlinePlayers())
				{
					on.sendMessage(Utils.chat(plugin.getConfig().getString("Listener.firstJoin").replace("<player>", p.getName())));
				}
			}
			p.sendMessage(Utils.chat(plugin.getConfig().getString("Listener.MOTD")));
			
		}
		// Else - player will receive rejoin message
		else
		{
			if (Bukkit.getOnlinePlayers().size() == 0)
			{
				return;
			}
			else
			{
				for (Player on : Bukkit.getOnlinePlayers()) 
				{
					on.sendMessage(Utils.chat(plugin.getConfig().getString("Listener.rejoin").replace("<player>", p.getName())));
				}
			}
			p.sendMessage(Utils.chat(plugin.getConfig().getString("Listener.MOTD")));
		}
		
	}
	
}
