package me.ANiceHippo.toolkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.ANiceHippo.toolkit.Main;
import org.bukkit.ChatColor;

public class DeathListener implements Listener{
	
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public DeathListener (Main plugin)
	{
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e)
	{
		@SuppressWarnings("unused")
		Player p = e.getEntity();
		
		Bukkit.broadcastMessage(ChatColor.BOLD +  "HAHA BOT!");
	}
}
