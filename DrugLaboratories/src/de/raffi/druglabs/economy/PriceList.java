package de.raffi.druglabs.economy;

import de.raffi.druglabs.utils.Configurable;
import de.raffi.druglabs.utils.Files;

public class PriceList {
	
	@Configurable(Files.SHOP)
	public static String INVENTORY_TITLE_COMPUTER = "§cComputer";
	@Configurable(Files.SHOP)
	public static String INVENTORY_TITLE_DARKNET_DRUGSTORE = "§9MineRoad";
	@Configurable(Files.SHOP)
	public static String INVENTORY_TITLE_DARKNET = "§9Darknet";

	
	@Configurable(Files.SHOP)
	public static double BUY_CLAVICEPS = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_ERGOT = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_LSD = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_MDMA = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_SEPARTOR = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_SYNTHESIZER = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_WEED = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_WEED_SEEDS = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_HYDRASTIS_CANADENSIS = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_HYDRASTININ = 50000;
	@Configurable(Files.SHOP)
	public static double BUY_HYDRALYSATOR= 50000;
	
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_LSD = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_ERGOT = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_MDMA = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_WEED= true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_WEED_SEEDS = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_SEPARATOR = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_SYNTHESIZER = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_CLAVICEPS = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_HYDRASTIS_CANADENSIS = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_HYDRASTININ = true;
	@Configurable(Files.SHOP)
	public static boolean DO_SELL_HYDRALYSATOR = true;
}
