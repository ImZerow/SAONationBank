package com.enjin.saonationbank.utils;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	
	public static void setItem(Inventory inv, String title, String lore, Material Mat, int position) {
		ItemStack is = new ItemStack(Mat);
		ItemMeta isMeta = is.getItemMeta();
		isMeta.setDisplayName(title);
		ArrayList<String> metaString = new ArrayList<>();
		metaString.add(lore);
		isMeta.setLore(metaString);
		is.setItemMeta(isMeta);
		inv.setItem(position, is);
	}

}
