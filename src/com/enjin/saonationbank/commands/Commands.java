package com.enjin.saonationbank.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

import static com.enjin.saonationbank.Main.*;
import static com.enjin.saonationbank.utils.ItemManager.*;

public class Commands implements CommandExecutor{
	
	public Economy economy = null;
	 
	private boolean setupEconomy() {
	 RegisteredServiceProvider<Economy> economyProvider = getInstance().getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	 if (economyProvider != null) {
	 economy = economyProvider.getProvider();
	 }
	 return (economy != null);
	 }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
		if(cmd.getName().equalsIgnoreCase("bank")) {
			setupEconomy();
			Player p = (Player)sender;
			if(!p.hasPermission("sao1.jogo")) {
				return true;
			}
			if(args.length == 0) {
				p.sendMessage("§4Argumento inválido, use /bank menu para gerenciar seu dinheiro.");
			}
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("menu")) {
					Inventory inv = Bukkit.createInventory(null, 3*9, "§3Bank Menu");
					for(int i = 0; i < inv.getSize(); i++) 
					{setItem(inv, "§8*", "", Material.IRON_FENCE, i);}
			        setItem(inv, "§6Depositar Dinheiro.", "§fClique para depositar cols.",Material.GOLD_INGOT, 11);
			        setItem(inv, "§6Ver dinheiro", "§fSeu saldo é: "+ economy.getBalance(p) ,Material.GOLD_INGOT, 13);
			        setItem(inv, "§6Sacar Dinheiro.", "§fClique para sacar cols.", Material.GOLD_INGOT, 15);
			        p.openInventory(inv);
				}
			}
		}
		}
		return false;
	}

}
