package me.anicehippo.toolkit;

import me.anicehippo.toolkit.commands.*;
import me.anicehippo.toolkit.listeners.BreedEvent;
import me.anicehippo.toolkit.listeners.JoinListener;
import me.anicehippo.toolkit.listeners.LeaveListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ToolKit extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Loading up Toolkit!!");
        // Register listener
        registerListeners();
        // Register Commands
        registerCommands();

        //Config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Shutting down! Goodbye!");
    }

    public void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(), this);
        getServer().getPluginManager().registerEvents(new BreedEvent(), this);
    }

    // Register all commands in the plugin
    public void registerCommands()
    {
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("feed").setExecutor(new FeedCommand());
        getCommand("god").setExecutor(new GodCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("survival").setExecutor(new SurvivalShortcutCommand());
        getCommand("creative").setExecutor(new CreativeShortcutCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
    }


}
