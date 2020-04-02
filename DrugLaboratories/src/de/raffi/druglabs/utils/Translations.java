package de.raffi.druglabs.utils;

import java.io.File;
import java.lang.reflect.Field;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Translations {
	
	
	@Configurable(Files.CONFIG)
	public static boolean LOGGER_INFO =true;
	@Configurable(Files.CONFIG)
	public static boolean LOGGER_WARN = true;
	@Configurable(Files.CONFIG)
	public static boolean LOGGER_ERROR = true;
	@Configurable
	public static String ITEM_NAME_ERGOT = "§7Ergot";
	@Configurable
	public static String ITEM_LORE_ERGOT = "";
	@Configurable
	public static String ITEM_NAME_1UG_LSD = "Lysergsurediethylamid"; 
	@Configurable
	public static String BLOCK_NAME_SEPARATOR = "§eSeparator";
	@Configurable
	public static String ITEM_NAME_CLAVICEPS  = "§fClaviceps purpurea";
	@Configurable
	public static String BLOCK_NAME_SYNTHESIZER = "§cSynthesizer";
	@Configurable
	public static String MESSAGE_BLOCK_PLACED = "§eYou placed %";
	@Configurable
	public static String MESSAGE_BLOCK_REMOVED = "§eYou removed %";
	@Configurable
	public static String MESSAGE_CONFIG_RELOAD = "§eReloading translations and config";
	@Configurable
	public static String MESSAGE_CONFIG_RELOAD_COMPLETE = "§eaReloading complete.";
	@Configurable
	public static String MESSAGE_NOPERMISSION= "§cInsufficient permission";
	@Configurable
	public static String BLOCK_NAME_SEEDS_WEED  = "§fMarijuana seeds";
	@Configurable
	public static String ITEM_NAME_GROWN_WEED  = "§fMarijuana";
	@Configurable
	public static String ITEM_NAME_MDMA  = "§dMDMA";
	@Configurable
	public static String ITEM_NAME_SYNTHESIZER_START  = "§fStart synthetation";
	@Configurable
	public static String ITEM_NAME_SYNTHESIZER_PROGRESS  = "§aProgressing synthetation ...";
	@Configurable
	public static String ITEM_NAME_STRAWBERRY  = "§cStrawberry";
	@Configurable
	public static String ITEM_NAME_STRAWBERRYSEEDS  = "§fStrawberry seeds";
	@Configurable
	public static String ITEM_NAME_HYDRASTININ  = "§fHydrastis canadensis";
	@Configurable
	public static String ITEM_NAME_SYNTHETIC_HYDRASTININ  = "§f2-Methyl-6,7-methylendioxy-1,2,3,4-tetrahydro-1-isochinolinol";
	@Configurable
	public static String BLOCK_NAME_HYDRALYSATOR  = "§aHydralysator";
	@Configurable
	public static String BLOCK_NAME_COMPUTER  = "§2Computer";
	@Configurable
	public static String ITEM_NAME_COMPUTER_BROWSER  = "§aMinecraft Diafox";
	@Configurable
	public static String ITEM_NAME_COMPUTER_DRUGSTORE  = "§7visit MineRoad.onion";
	@Configurable
	public static String SHOP_CURRENCY  = "$";
	@Configurable
	public static String SHOP_ITEM_DESCRIPTION  = "§7Buy this Item for %price%%currency%";
	@Configurable
	public static String SHOP_ITEM_DESCRIPTION2  = "§7§oSHIFT+Rightclick to buy";
	@Configurable(Files.CONFIG)
	public static int LSD_TRIP_UPDATETICKS_NICE = 60;
	@Configurable(Files.CONFIG)
	public static int LSD_TRIP_UPDATETICKS_BAD = 60;
	@Configurable(Files.CONFIG)
	public static int LSD_TRIP_DURATION_SECONDS_NICE = 60;	
	@Configurable(Files.CONFIG)
	public static int LSD_TRIP_DURATION_SECONDS_BAD = 60;
	@Configurable(Files.CONFIG)
	public static int WEED_TRIP_DURATION_SECONDS_NICE = 60;
	@Configurable(Files.CONFIG)
	public static int WEED_TRIP_UPDATETICKS_NICE = 60;
	@Configurable(Files.CONFIG)
	public static int MDMA_TRIP_DURATION_SECONDS = 60;
	@Configurable(Files.CONFIG)
	public static int MDMA_TRIP_UPDATETICKS = 60;
	@Configurable(Files.CONFIG)
	public static double MDMA_TRIP_ENTITYSEARCHRADIUS = 30;
	@Configurable(Files.CONFIG)
	public static int MUSHROOM_TRIP_UPDATETICKS = 60;
	@Configurable(Files.CONFIG)
	public static int MUSHROOM_TRIP_DURATION_SECONDS = 60;
	@Configurable(Files.CONFIG)
	public static int PLACEHOLDER_COLOR = 8;
	@Configurable(Files.CONFIG)
	public static boolean RUN_WEEDLOOP = true;
	@Configurable(Files.CONFIG)
	public static int WEEDLOOP_SECONDS = 120;
	@Configurable(Files.CONFIG)
	public static int DROPCHANCE_SEEDS_WEED = 20;
	@Configurable(Files.CONFIG)
	public static int DROPCHANCE_ERGOT = 5;
	@Configurable(Files.CONFIG)
	public static int DROPCHANCE_SEEDS_STRAWBERRY = 10;
	@Configurable(Files.CONFIG)
	public static int DROPCHANCE_HYDRASTIS_CANADENSIS = 30;
	@Configurable(Files.CONFIG)
	public static String COMMAND_DRUGLABS_PERMISSION = "druglabs.admin";
	@Configurable(Files.CONFIG)
	public static boolean ECONOMY = false;

	/**
	 * loads all Fields with the {@link Configurable} Annotatation
	 * @param cl The class the Fields should be loaded from
	 */
	public static void load(Class<?> cl) {
		for(Field f : cl.getDeclaredFields()) {
			if(f.isAnnotationPresent(Configurable.class)) {
				f.setAccessible(true);
				Configurable configurable = f.getAnnotation(Configurable.class);
				File save = new File(Files.FOLDER,configurable.value());
				FileConfiguration cfg=YamlConfiguration.loadConfiguration(save);

				if(cfg.isSet(f.getName())) {			 //check if the config already contains the field name	
					try {
						boolean b = f.get(null) instanceof String;
						if(b)
							f.set(null, ((String)cfg.get(f.getName())).replace("&", "§")); //replace & to §
						else f.set(null, cfg.get(f.getName()));
							
						DrugLogger.info("§e[Config] SET "+f.get(null).getClass().getSimpleName().toUpperCase()+" '§7" + f.getName() + "§e' VALUE = '§7"+f.get(null)+"§e' @" + configurable.value());
					} catch (Exception e) {
						e.printStackTrace();
						DrugLogger.error("Error occurred whilst reading configurable value");
					}
				} else { //sets the field value to the config if the config does not contain the field name
					try {
						Object set = f.get(null);
						if(set instanceof String) {
							cfg.set(f.getName(), ((String)set).replace("§", "&")); //replace § to &
						}else
							cfg.set(f.getName(), f.get(null));
						DrugLogger.info("[Config] register '" + f.getName() + "' value = '" + f.get(null)+"' @" + configurable.value());
						cfg.save(save); //save the file
					} catch (Exception e) {
						e.printStackTrace();
						DrugLogger.error("Error occurred whilst registering configurable value");
					}
				}
			}
		}
	}

}
