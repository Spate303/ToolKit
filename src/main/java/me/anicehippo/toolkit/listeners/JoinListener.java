package me.anicehippo.toolkit.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event)
    {
        Player p = event.getPlayer();

        if(!p.hasPlayedBefore())
        {
            // If - This is a first time player set different join message
            event.setJoinMessage(ChatColor.AQUA + "Welcome to the server " + ChatColor.YELLOW + "" + ChatColor.BOLD + p.getName() + ChatColor.AQUA + "! Do /help for more support!");
        }
        else
        {
            // Broadcast to server that player has joined back
            event.setJoinMessage(ChatColor.AQUA + "Welcome back to the server " + ChatColor.YELLOW + "" + ChatColor.BOLD + p.getDisplayName() + ChatColor.AQUA + "!");
        }
    }
}
