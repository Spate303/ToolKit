package me.anicehippo.toolkit.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {

        event.setQuitMessage(ChatColor.LIGHT_PURPLE + "Goodbye! Come back again! ");

    }
}
