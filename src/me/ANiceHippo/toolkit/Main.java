package me.ANiceHippo.toolkit;


import org.bukkit.plugin.java.JavaPlugin;

import me.ANiceHippo.toolkit.commands.FeedCommand;
import me.ANiceHippo.toolkit.commands.FlyCommand;
import me.ANiceHippo.toolkit.commands.HealCommand;

import me.ANiceHippo.toolkit.listeners.DeathListener;
import me.ANiceHippo.toolkit.listeners.JoinListener;

import me.ANiceHippo.toolkit.listeners.QuitListener;


public class Main extends JavaPlugin {

	
	// On startup of plugin
	@Override
	public void onEnable()
	{
		registerCommands();
		registerListeners();
		saveDefaultConfig();
		
	}
	// If the plugin shuts down do this...
	@Override
	public void onDisable()
	{
		saveDefaultConfig();
	}
	
	
	public void registerCommands()
	{
		new FlyCommand(this);
		new FeedCommand(this);
		new HealCommand(this);
		
	}
	
	public void registerListeners()
	{
		new JoinListener(this);
		new QuitListener(this);
		new DeathListener(this);
		
	}
	
	public void registerManagers()
	{
		
	}
	
	
}
