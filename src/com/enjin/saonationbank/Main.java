package com.enjin.saonationbank;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.enjin.saonationbank.commands.Commands;
import com.enjin.saonationbank.listeners.Listeners;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	public void onEnable() {
	    instance = this;
		registerCommands();
		registerEvents();
		Bukkit.getConsoleSender().sendMessage("§3[SAONation] Bank iniciando.");
	}
	
	public void registerCommands() {
		getCommand("bank").setExecutor(new Commands());
	}
	
	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
	}

	public static Main getInstance() {
		return instance;
	}
}
