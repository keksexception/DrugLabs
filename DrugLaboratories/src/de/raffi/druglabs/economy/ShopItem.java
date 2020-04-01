package de.raffi.druglabs.economy;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.main.DrugLabs;
import de.raffi.druglabs.utils.Translations;
import de.raffi.strawberry.utils.ItemBuilder;

public class ShopItem {
	
	private ItemStack item;
	private ItemStack display;
	private double sell,buy;
	private String currency;

	public ShopItem(ItemStack item, double sell, double buy, String currency) {
		this.item = item;
		ItemBuilder display = new ItemBuilder(item.getType(),(short) item.getDurability()).setName(item.getItemMeta().getDisplayName()).setLore(Translations.SHOP_ITEM_DESCRIPTION.replace("%price%", ""+buy).replace("%currency%", currency),"","",Translations.SHOP_ITEM_DESCRIPTION2);
		this.display =DrugLabs.VERSIONHANDLER.addTag(display.build(), "placeholder", true) ;
		this.sell = sell;
		this.buy = buy;
		this.currency = currency;
	}
	public ItemStack getItem() {
		return item;
	}
	public ItemStack getDisplay() {
		return display;
	}
	public double getSell() {
		return sell;
	}
	public double getBuy() {
		return buy;
	}
	public String getCurrency() {
		return currency;
	}
	public void buy(Player p) {
		if(VaultBridge.getEconomy().has(p, getBuy())) {
			DrugLabs.VERSIONHANDLER.playExperienceSound(p, 1.0f, 0.6f);
			VaultBridge.getEconomy().withdrawPlayer(p, getBuy());
			p.getInventory().addItem(item);
		} else DrugLabs.VERSIONHANDLER.playBurpSound(p, 0.5f, 2f);
	}
	public void sell(Player p) {
		
	}
	
}
