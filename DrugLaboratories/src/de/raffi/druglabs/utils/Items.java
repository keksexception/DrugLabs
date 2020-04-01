package de.raffi.druglabs.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.raffi.druglabs.compability.Versionhandler;
import de.raffi.strawberry.utils.ItemBuilder;
import de.raffi.strawberry.utils.SkullBuilder;

public class Items {
	
	public static ItemStack ERGOT;
	public static ItemStack LSD;
	public static ItemStack SEPARATOR;
	public static ItemStack CLAVICEPS;
	public static ItemStack SYNTHESIZER;
	public static ItemStack SEED_WEED;
	public static ItemStack GROWN_WEED;
	@Ignore
	public static ItemStack PLACE_HOLDER;
	@Ignore
	public static ItemStack SYNTH_START;
	@Ignore
	public static ItemStack SYNTH_PROGRESS;
	public static ItemStack STRAWBERRY_SEEDS;
	public static ItemStack STRAWBERRY;
	public static ItemStack MDMA;
	public static ItemStack COMPUTER;
	@Ignore
	public static ItemStack COMPUTER_BROWSER;
	@Ignore
	public static ItemStack COMPUTER_VISIT_DRUGSTORE;
	/**
	 * @since 1.1.3
	 */
	public static ItemStack HYDRASTININ;
	/**
	 * @since 1.1.3
	 */
	public static ItemStack SYNTHETIC_HYDRASTININ;
	/**
	 * @since 1.1.3
	 */
	public static ItemStack HYDRALYSATOR;
	
	/**
	 * sets the items
	 * @param v the {@link Versionhandler} that should be used
	 * @since 1.1.3
	 */
	public static void registerItems(Versionhandler v) {
		ERGOT = v.addTag(new ItemBuilder(Material.WHEAT).setName(Translations.ITEM_NAME_ERGOT).setLore(Translations.ITEM_LORE_ERGOT).build(), "ergot", true);
		LSD = v.addTag(new ItemBuilder(Material.GHAST_TEAR).setName(Translations.ITEM_NAME_1UG_LSD).build(), "lsd", true);
		SEPARATOR = v.addTag(new ItemBuilder(Material.FURNACE).setName(Translations.BLOCK_NAME_SEPARATOR).build(), "separator", true);
		CLAVICEPS = v.addTag(new ItemBuilder(Material.INK_SACK).setName(Translations.ITEM_NAME_CLAVICEPS).build(), "claviceps", true);
		SYNTHESIZER = v.addTag(new ItemBuilder(Material.BREWING_STAND_ITEM).setName(Translations.BLOCK_NAME_SYNTHESIZER).build(), "synthesizer",true);
		SEED_WEED = v.addTag(new ItemBuilder(Material.SEEDS).setName(Translations.BLOCK_NAME_SEEDS_WEED).build(), "weedseed", true);
		GROWN_WEED = v.addTag(new ItemBuilder(Material.LONG_GRASS,(short) 2).setName(Translations.ITEM_NAME_GROWN_WEED).build(), "weedgrown", true);
		PLACE_HOLDER = v.addTag(new ItemBuilder(Material.STAINED_GLASS_PANE,(short) Translations.PLACEHOLDER_COLOR).setName(" ").build(), "placeholder", true);
		SYNTH_START = v.addTag(new ItemBuilder(Material.REDSTONE).setName(Translations.ITEM_NAME_SYNTHESIZER_START).build(), "placeholder", true);
		SYNTH_PROGRESS = v.addTag(new ItemBuilder(Material.INK_SACK,(short)10).setName(Translations.ITEM_NAME_SYNTHESIZER_PROGRESS).build(), "placeholder", true);
		MDMA = v.addTag(new ItemBuilder(Material.INK_SACK,(short)13).setName(Translations.ITEM_NAME_MDMA).build(), "mdma", true);
		HYDRASTININ = v.addTag(new ItemBuilder(Material.DEAD_BUSH).setName(Translations.ITEM_NAME_HYDRASTININ).build(), "hydrastinin", true);
		SYNTHETIC_HYDRASTININ = v.addTag(new ItemBuilder(Material.SULPHUR).setName(Translations.ITEM_NAME_SYNTHETIC_HYDRASTININ).build(), "synthetichydrastinin", true);
		HYDRALYSATOR = v.addTag(new ItemBuilder(Material.FURNACE).setName(Translations.BLOCK_NAME_HYDRALYSATOR).build(), "hydralysator", true);
		
		
		STRAWBERRY_SEEDS = v.addTag(new ItemBuilder(Material.MELON_SEEDS).setName(Translations.ITEM_NAME_STRAWBERRYSEEDS).build(), "strawberryseeds", true);
		STRAWBERRY = v.addTag(new ItemBuilder(Material.REDSTONE).setName(Translations.ITEM_NAME_STRAWBERRY).build(), "strawberry", true);
		if(Translations.ECONOMY) {
			COMPUTER = v.addTag(new SkullBuilder("zasf").setName(Translations.BLOCK_NAME_COMPUTER).build(), "computer", true);
			COMPUTER_BROWSER = v.addTag(new SkullBuilder("0qt").setName(Translations.ITEM_NAME_COMPUTER_BROWSER).build(), "browser", true);
			COMPUTER_VISIT_DRUGSTORE =v.addTag(new ItemBuilder(Material.BOOK).setName(Translations.ITEM_NAME_COMPUTER_DRUGSTORE).build(), "drugstore", true);
		}
	}
	
}
