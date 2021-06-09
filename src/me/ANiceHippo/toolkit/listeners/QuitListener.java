package me.ANiceHippo.toolkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.ANiceHippo.toolkit.Main;
import me.ANiceHippo.toolkit.utils.Utils;

public class QuitListener implements Listener{

	private Main plugin;
	
	public QuitListener (Main plugin)
	{
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	
	@EventHandler
	public void onQuit(PlayerQuitEvent e)
	{
		
		Player p = e.getPlayer();
		
		
		if(Bukkit.getOnlinePlayers().size() == 0)
		{
			return;
		}
		else
		{
			for (Player on : Bukkit.getOnlinePlayers())
			{
				on.sendMessage(Utils.chat(plugin.getConfig().getString("Listener.Quit").replace("<player>", p.getName())));
			}
		}
	}
}
