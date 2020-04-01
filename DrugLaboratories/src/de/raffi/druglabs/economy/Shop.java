package de.raffi.druglabs.economy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.utils.DrugLogger;
import de.raffi.druglabs.utils.Items;
import de.raffi.druglabs.utils.Translations;

public class Shop {
	
	public static List<ShopItem> items = new ArrayList<>();
	protected static HashMap<ItemStack, ShopItem> cache = new HashMap<>();
	public static void registerItem(ShopItem i) {
		items.add(i);
	}
	/**
	 * adds all shopitems to the {@link Shop#items list}
	 */
	public static void register() {
		DrugLogger.info("Setted up VaultBridge successfully");
		DrugLogger.info("Loading shop items");
		if(PriceList.DO_SELL_CLAVICEPS) registerItem(new ShopItem(Items.CLAVICEPS, -1, PriceList.BUY_CLAVICEPS,Translations.SHOP_CURRENCY));
		try {
			if(PriceList.DO_SELL_ERGOT) registerItem(new ShopItem(Items.ERGOT, -1, PriceList.BUY_ERGOT,Translations.SHOP_CURRENCY));
		} catch (Exception e) {}
		
		if(PriceList.DO_SELL_LSD) registerItem(new ShopItem(Items.LSD, -1, PriceList.BUY_LSD,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_MDMA) registerItem(new ShopItem(Items.MDMA, -1, PriceList.BUY_MDMA,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_SEPARATOR) registerItem(new ShopItem(Items.SEPARATOR, -1, PriceList.BUY_SEPARTOR,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_SYNTHESIZER) registerItem(new ShopItem(Items.SYNTHESIZER, -1, PriceList.BUY_SYNTHESIZER,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_WEED) registerItem(new ShopItem(Items.GROWN_WEED, -1, PriceList.BUY_WEED,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_WEED_SEEDS) registerItem(new ShopItem(Items.SEED_WEED, -1, PriceList.BUY_WEED_SEEDS,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_HYDRALYSATOR) registerItem(new ShopItem(Items.HYDRALYSATOR, -1, PriceList.BUY_HYDRALYSATOR,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_HYDRASTININ) registerItem(new ShopItem(Items.SYNTHETIC_HYDRASTININ, -1, PriceList.BUY_HYDRASTININ,Translations.SHOP_CURRENCY));
		if(PriceList.DO_SELL_HYDRASTIS_CANADENSIS) registerItem(new ShopItem(Items.HYDRASTININ, -1, PriceList.BUY_HYDRASTIS_CANADENSIS,Translations.SHOP_CURRENCY));
	}
	/**
	 * 
	 * @param d display ItemStack {@link ShopItem#getDisplay()}
	 * @return the shopitem which has the ItemStack as display stack
	 */
	public static ShopItem get(ItemStack d) {
		if(cache.get(d)==null) {
			for(ShopItem e : items) {
				if(e.getDisplay().isSimilar(d)) {
					cache.put(d, e);
					return e;
				}
			}
		}else {
			return cache.get(d);
		}
		return null;
	}
	

}
