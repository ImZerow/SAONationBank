package com.enjin.saonationbank.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

import static com.enjin.saonationbank.Main.*;
import static com.enjin.saonationbank.utils.ItemManager.*;

public class Listeners implements Listener{
	
	public Economy economy = null;
	int id = 4429;
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> ecoProvider = getInstance().getServer().getServicesManager().getRegistration(Economy.class);
		if(ecoProvider != null) {
			economy = ecoProvider.getProvider();
		}
		return (economy != null);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void InventoryClick(InventoryClickEvent e) {
		setupEconomy();
		Player p = (Player)e.getWhoClicked();

		if(e.getInventory().getName().equalsIgnoreCase("§3Bank Menu")) {
			e.setCancelled(true);
			
				if(e.getRawSlot() == 11) {
					int i = 0;
					for (ItemStack is : p.getInventory().getContents()) {
						if(is != null)
					    if (is.getType() == Material.getMaterial(1) && is.getAmount() != 0) {
					         i = i + is.getAmount();
			              p.getInventory().remove(new ItemStack(Material.getMaterial(1), i));
			              economy.depositPlayer(p, 10);
					    }
					}
					if(i == 0) {
					p.sendMessage("§2[SAONationBank] §fVocê não tem nenhum dinheiro para depositar! ;(");
					p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 10, 1);
					return;
					}
					p.sendMessage("§2[SAONationBank] §fVocê depositou §2$" + i + "§f a sua conta!");
				p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10, 1);
			}
			
			
			if(e.getRawSlot() == 15) {
				Inventory inv = Bukkit.createInventory(null, 3*9, "§3Sacar Dinheiro");
				for(int i = 0; i < inv.getSize(); i++) {
				setItem(inv, "§8*", "", Material.IRON_FENCE, i);
				}
				setItem(inv, "§6Remover 1 col", "§fClique para sacar 1 col.", Material.GOLD_INGOT, 11);
				setItem(inv, "§6Remover 10 col", "§fClique para sacar 10 cols.", Material.GOLD_INGOT, 13);
				setItem(inv, "§6Remover 64 col", "§fClique para sacar 64 cols.", Material.GOLD_INGOT, 15);
				p.playSound(p.getLocation(), Sound.NOTE_PIANO, 10, 1);
				p.openInventory(inv);
			}
			
		}
		
		if(e.getInventory().getName().equalsIgnoreCase("§3Sacar Dinheiro")) {
			e.setCancelled(true);
			
			if(e.getRawSlot() == 11) {
				if(1 <= economy.getBalance(p)) {
					economy.withdrawPlayer(p, 1);
					p.getInventory().addItem(new ItemStack(Material.getMaterial(1), 1));
				}else {
					p.sendMessage("§2[SAONationBank] §fVocê precisa de pelo menos §2$1§f para fazer o saque");
				}
			}
			
			if(e.getRawSlot() == 13) {
				if(10 <= economy.getBalance(p)) {
					economy.withdrawPlayer(p, 10);
					p.getInventory().addItem(new ItemStack(Material.getMaterial(1), 10));
				}else {
					p.sendMessage("§2[SAONationBank] §fVocê precisa de pelo menos §2$10§f para fazer o saque");
				}
			}
			
			if(e.getRawSlot() == 15) {
				if(64 <= economy.getBalance(p)) {
					economy.withdrawPlayer(p, 64);
					p.getInventory().addItem(new ItemStack(Material.getMaterial(1), 64));
				}else {
					p.sendMessage("§2[SAONationBank] §fVocê precisa de pelo menos §2$64§f para fazer o saque");
				}
			}
					
		}
	}
}
