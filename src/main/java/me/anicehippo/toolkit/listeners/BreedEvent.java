package me.anicehippo.toolkit.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityBreedEvent;

public class BreedEvent implements Listener {
    @EventHandler
    public void onBreedEvent (EntityBreedEvent event)
    {
        Entity p = event.getBreeder();
        p.sendMessage(ChatColor.LIGHT_PURPLE + "You made baby! Yay!");
    }
}
