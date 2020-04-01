package de.raffi.druglabs.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.economy.PriceList;
import de.raffi.druglabs.economy.Shop;
import de.raffi.druglabs.economy.ShopItem;
import de.raffi.druglabs.event.InventoryCreationEvent;
import de.raffi.druglabs.event.InventoryCreationEvent.Type;

public class InventoryManager {
	
	protected static File f = new File(Files.FOLDER,Files.BLOCKS);
	protected static FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	public static Inventory getInventory(String path,String title) {
		if(title ==null)title = "N/A";
		int size = cfg.getInt(path + ".size");
		if(size <= 0) size = 9*3;
		Inventory inv = Bukkit.createInventory(null, size, title);
		for(int i = 0; i < size; i++) {
			inv.setItem(i, cfg.getItemStack(path + "." + i));				
		}
		return inv;
	}
	static Inventory computer = null;
	/**
	 * calls the {@link InventoryCreationEvent}.
	 * the inventory is saved the static Field {@link InventoryManager#computer}
	 * @return
	 */
	public static Inventory getComputerInventory() {
		if(computer == null) {
			Inventory inv = Bukkit.createInventory(null, 9*3, PriceList.INVENTORY_TITLE_COMPUTER);
			inv.addItem(Items.COMPUTER_BROWSER);
			InventoryCreationEvent e = new InventoryCreationEvent(inv, Type.COMPUTER);
			Bukkit.getPluginManager().callEvent(e);
			computer = inv;
		}
		return computer;
	}
	/**
	 * calls the {@link InventoryCreationEvent}.
	 * you can add your items here using the {@link InventoryCreationEvent} 
	 * @return
	 */
	public static Inventory getDarknetInventory() {
		Inventory inv = Bukkit.createInventory(null, 9*3, PriceList.INVENTORY_TITLE_DARKNET);
		inv.addItem(Items.COMPUTER_VISIT_DRUGSTORE);
		InventoryCreationEvent e = new InventoryCreationEvent(inv, Type.DARKNET);
		Bukkit.getPluginManager().callEvent(e);
		return inv;
	}
	/**
	 * don't calls the {@link InventoryCreationEvent}.
	 * Please use the {@link Shop#registerItem(ShopItem)} method to add an item to this inventory
	 * @return the inventory
	 */
	public static Inventory getDrugStoreInventory() {
		Inventory inv = Bukkit.createInventory(null, 9*3, PriceList.INVENTORY_TITLE_DARKNET_DRUGSTORE);
		for(ShopItem i : Shop.items) {
			inv.addItem(i.getDisplay());
		}
		return inv;
	}
	/**
	 * calls the {@link InventoryCreationEvent} and adds all Items,
	 * in the {@link Items Itemsclass}, which don't have the {@link Ignore} Annotation, to the inventory
	 * you can add your items here using the {@link InventoryCreationEvent} 
	 * @return the inventory
	 */
	public static Inventory getItemInventory() {
		Inventory inv = Bukkit.createInventory(null, 9*3, "§7DrugLabs §8§l| §4Admin");
		for(Field f : Items.class.getDeclaredFields()) {
			try {
				if(!f.isAnnotationPresent(Ignore.class))
					if(f.get(null)instanceof ItemStack) {
						inv.addItem((ItemStack) f.get(null));
					}
			} catch (Exception e) {
			}
		
		}
		InventoryCreationEvent e = new InventoryCreationEvent(inv, Type.ADMIN_INVENTORY);
		Bukkit.getPluginManager().callEvent(e);
		return inv;
	}
	public static void saveInventory(String path, Inventory inv) {
		cfg.set(path + ".size", inv.getSize());
		
		for(int i = 0; i < inv.getSize(); i++) {
			cfg.set(path + "." + i, inv.getItem(i));
		}
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Inventory createSynthesizer() {
		Inventory inv = Bukkit.createInventory(null, 9*3, Translations.BLOCK_NAME_SYNTHESIZER);
		for(int i = 0; i < inv.getSize();i++) {
			inv.setItem(i, Items.PLACE_HOLDER);
		}
		inv.setItem(16, new ItemStack(Material.AIR));
		inv.setItem(10, new ItemStack(Material.AIR));
		inv.setItem(12, new ItemStack(Material.AIR));
		inv.setItem(25, Items.SYNTH_START);
		return inv;
	}
	

}
